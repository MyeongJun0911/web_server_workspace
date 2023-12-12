<%--
    EL 내장객체
    - context 객체속성(맵) (생략가능, 생략시 page-request-session-application순으로 조회)
        - requestScope
        - sessionScope
        - applicationScope
    -사용자 입력값
        - param
        - paramValues
    - header 정보
        - header
        - headerValue
    - 쿠기 cookie
    - pageContext객체 직접 접근 (포인터) : ${pageContext.request.contextPath}

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>El</h1>
    <h2>context내장객체 속성</h2>
    <ul>
        <li>이름 : ${name} ${requestScope.name} ${sessionScope.name}</li>
        <li>숫자 : ${requestScope.num}</li>
        <li>취미 : ${requestScope.bobbies}</li>
        <ul>
            <li>${hobbies[0]}</li>
            <li>${hobbies[1]}</li>
            <li>${hobbies[2]}</li>
            <li>${hobbies[3]}</li>
            <li>${hobbies[4]}</li>
        </ul>
        </li>
        <li>책/가격 : ${bookMap}
            <ui>
                <li>${bookMap.MyJava}</li>
                <li>${bookMap['정신이 나가기전']}</li>
                <li>${bookMap["Dr.Zang\'s office"]}</li>
            </ui>
        </li>
    </ul>

    <h2>사용자입력값</h2>
    <ul>
        <li>name : ${paramValues.option[0]}</li>
        <li>option1 : ${paramValues.option[0]}</li>
        <li>option2 : ${paramValues.option[1]}</li>
    </ul>

    <h2>헤더값</h2>
    <ul>
        <li>User-Agent : ${header['user-agent']}</li>
    </ul>

</body>
</html>
