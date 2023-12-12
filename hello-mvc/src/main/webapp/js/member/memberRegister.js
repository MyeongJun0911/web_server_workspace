const hobbyEtc = document.querySelector("#hobby-etc");
hobbyEtc.addEventListener('keyUp', (e)=> {
    if(e.keyCode === 13){
        //자동으로 생겨난 <br><br>을 제거. blur처리
        console.log(e.target.innerHTML); // 값<br><br>
        e.target.innerHTML = e.target.innerHTML.replace(/<br>/g, '');
        e.target.blur();
    }
});
hobbyEtc.addEventListener('blur',(e)=> {
    const value = e.target.innerHTML;
    const html = '';



    // 특정요소기준으로 새요소 추가
    // - beforebegin 시작태그앞. 이전형제요소로 추가
    // - afterbegin 시작태그뒤. 첫 자식요소로 추가
    // - beforeend 종료태그앞. 마지막 자식요소로 추가
    // - afterend 종료태그뒤. 다음 형제요소로 추가
    e.target.parentElement.insertAdjacentHTML('beforebegin', html);
    e.target.innerHTML = '직접입력';


})

// /**
//  * 회원가입 유효성검사
//  */
// document.memberRegisterFrm.addEventListener('submit', (e) => {
//     const frm = e.target;
//     const id = frm.id;
//     const password = frm.password;
//     const confirmPassword : Element = document.querySelector("#confirm-password");
//     const name = frm.name;
//     const email = frm.email;
//
//     // 아이디 - 영문자/숫자 4글자이상
//     if(!/^\w{4, }$/.test(id.value)){
//         alert('아이디는 영문자/숫자 4글자 이상');
//         e.preventDefault();
//         return;
//     }
//
//     const regExps : RegExp[{}] = [/[A-Za-z]/, /\d/, /[!@#$%]/, /^.{4, }$/]
//     if(!/ /.test(password.value()){``
//
//     }
//         {
//         re: /[A-Za-z]/,
//         msg: '비밀번호는 영문자를 하나이상 포함해주세요'
//     },
//     {
//         re:/\d/,
//         msg: '비밀번호는 숫자를 하나이상 포함해주세요'
//     },
//     {
//         re: /[!@$#$%]/,
//         msg: '비밀번호는 특수문자!@#$% 중에 하나를 포함해주세요'
//     },
//     {
//         re: /^.{4,}$/,
//         msg: '비밀번호는 4글자 이상 작성해주세요.'
//     }
//     ];
//     for(let i:number = 0; i < regExps.length; i++){
//         const {re: RegExp, msg: string} = regExps[i];
//         if(!re.test(password.value()){
//             alert(msg);
//             e.preventDefault();
//             return;
//     }
//
//
//         [/[A-Za-z]/, /\d/, /[!@#$%]/, /^.{4,}$/};
//     // 비밀번호 - 영문자/숫자/특수문자!@#$% 4글자이상
//     if(!//.test(id.value)){
//         alert('두 비밀번호가 다릅니다.');
//         e.preventDefault();
//         return;
//     }
//     // 비밀번호 확인
//     if(password.value !== con){
//         e.preventDefault();
//         return;
//     }
//
//     // 이름 한글2글자 이상
//     if(!/ /.test(name.value)){
//         e.preventDefault();
//         return;
//     }
//
//     // 이메일 형식
//     if(!/^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/.test(email.value)){
//         alert('유효한 이메일을 작성하세요');
//         e.preventDefault();
//         return;
//     }
// });