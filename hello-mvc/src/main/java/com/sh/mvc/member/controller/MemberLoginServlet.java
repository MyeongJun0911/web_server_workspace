package com.sh.mvc.member.controller;

import com.sh.mvc.member.model.entity.Member;
import com.sh.mvc.member.model.service.MemberService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *  Http통신은 stateless하다.
 *  - 상태를 관리하지 않는다.
 *  - 매요청은 독립적.
 *  - 요청/응답 후에는 연결이 끊긴다.
 *  - 사용자의 상태도 관리하지 못한다. (로그인유지 시켜줄수 없다.)
 *  
 *  Session - Cookie를 이용한 사용자 상태관리
 *  - session 정보를 server(tomcat)측에서 관리. 로그인사용자정보
 *  - cookie 정보를 client(chrome)측에서 관리. session id
 *  http는 stateless하지만 기술자체는 statefull
 *  session id의 발급기준은 browser
 *
 *  1. client의 첫 접속시 session은 session id를 발급, 응답헤더에 추가한다.
 *    - 응답헤어 Set-Cookie 확인
 *  2. Set-Cookie 응답을 받은 client는 브라우져에 cookie항목에 session id를 저장한다.
 *    - Applicaion - Cookie 확인
 *  3. 다음 매 요청마다 client는 Cookie항목을 session id를 함께 전송한다.
 *    - 요청헤더 Cookie 확인
 *  4. 요청헤더의 Cookie를 확인한 Server는 업무로직 수행시 해당 session객체를 사용
 *    - sessiom id가 유효하지 않다면 새로 session객체를 생성하고 id를 발급해서 1번을 다시 반복한다.
 */

@WebServlet("/member/memberLogin")
public class MemberLoginServlet extends HttpServlet {

    private MemberService memberService = new MemberService();
    
    /*
    로그인 폼페이지
    C S D는 src/main/java
    V는 src/main/webapp/WEB-INF/member
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/member/memberLogin.jsp");
        requestDispatcher.forward(req, resp);
    }

    /*
    로그인 처리
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자입력값 인코딩처리
        req.setCharacterEncoding("utf-8");

        // 2. 사용자입력값 가져오기
        //input태그의 name
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        System.out.println(id + ", " + password);

        // 3. 업무로직 (이번요청에 처리할 작업) -> 로그인(인증)
        // id/password - db에서 읽어온 데이터(member객체) 비교
        // 로그인 성공 (id/password 모두 일치)
        // 로그인 실패 (존재하지 않는 id | password가 틀린 경우)
        Member member = memberService.findById(id);
        System.out.println(member);

        HttpSession session = req.getSession();
        if(member != null && password.equals(member.getPassword())){
            // 로그인 성공
            // pageContext, request, session, application 컨텍스트 객체중에 login처리에 적합한 것은 session
            // session객체는 사용자가 서버첫접속부터 세션해제시까지 유효
            session.setAttribute("loginMember", member);

        }
        else{
            // 로그인 실패
            session.setAttribute("msg", "아이디가 존재하지 않거나, 비밀번호가 틀립니다. 🍕");
        }

        // 4. view단처리 (forwarding) | redirect처리(주소이전 | url변경시)
        // DML요청(POST), 로그인요청등은 '반드시' redirect로 처리해서 url을 변경해야 한다.
        // req.getRequestDispatcher("/index.jsp").forward(req, resp);
        resp.sendRedirect(req.getContextPath());
    }
}
