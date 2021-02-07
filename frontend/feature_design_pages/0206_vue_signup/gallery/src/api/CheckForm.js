// import http from './http'
// import { mapActions } from 'vuex'


const checkFrom = (userInfo) => {
  const email = checkEmail(userInfo.user_email);
  const password = checkPassword(userInfo.user_id,userInfo.user_password);
  const tel = checkTel(userInfo.user_tel)
  if(email && password &&tel){
    return {showModal:true,email,password,tel};
  }else{
    return {email,password,tel};
  }
}

const checkPassword = (id,password)=>{
  if(!/^[a-zA-Z0-9]{6,10}$/.test(password)){

  alert('숫자와 영문자 조합으로 6~10자리를 사용해야 합니다.');
  return false;
  }

  var checkNumber = password.search(/[0-9]/g);
  var checkEnglish = password.search(/[a-z]/ig);

  if(checkNumber <0 || checkEnglish <0){ alert("숫자와 영문자를 혼용하여야 합니다."); 
    return false; 
  }
  if(/(\w)\1\1\1/.test(password)){ alert('같은 문자를 4번 이상 사용하실 수 없습니다.'); 
    return false; 
  }
  if(password.search(id)> -1){

    alert("비밀번호에 아이디가 포함되었습니다.");
    return false;
    }
    return true;
}


const checkEmail = (email) => {
  let emailConfirm = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
  if(!emailConfirm.test(email)){
    alert('이메일 양식이 올바르지 않습니다.');
    return false;
  }
  return true;
}

const checkTel = (tel) => {
  let telNum = tel.replace(/-/g,'');
  if(telNum.length !==11){
    alert('휴대전화번호를 올바르게 입력해주세요.');
  return false;
  }
  return true;
}

export default {checkFrom,checkEmail,checkPassword,checkTel}