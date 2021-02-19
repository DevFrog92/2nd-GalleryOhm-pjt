import store from '../../store/store'

const design = () => {
  const modMainBtn = document.querySelector(".modmain");
  // const modMainIcon = modMainBtn.firstChild;
  const modUser = document.querySelector(".moduser");
  const modMypage = document.querySelector(".modpage");
  const unscribe = document.querySelector(".unscribe");
  // const modUserIcon = modUser.firstChild;
  // const modMypageIcon = modMypage.firstChild;
  // const unscribeIcon = unscribe.firstChild;
  const line = document.querySelector("#line");
  const first = document.querySelector("#first");
  const second = document.querySelector("#second");
  const third = document.querySelector("#third");
  const fourth = document.querySelector("#fourth");




  modMainBtn.addEventListener("click", () => {
    modMainBtn.classList.add("active");
    // modMainIcon.classList.add("active");
    modUser.classList.remove("active");
    modMypage.classList.remove("active");
    unscribe.classList.remove("active");
    // modUserIcon.classList.remove("active");
    // modMypageIcon.classList.remove("active");
    // unscribeIcon.classList.remove("active");
    line.classList.add("one");
    line.classList.remove("two");
    line.classList.remove("three");
    line.classList.remove("four");
  });

  modUser.addEventListener("click", () => {
    modUser.classList.add("active");
    // modUserIcon.classList.add("active");
    modMainBtn.classList.remove("active");
    modMypage.classList.remove("active");
    unscribe.classList.remove("active");
    // modMainIcon.classList.remove("active");
    // modMypageIcon.classList.remove("active");
    // unscribeIcon.classList.remove("active");
    line.classList.add("two");
    line.classList.remove("one");
    line.classList.remove("three");
    line.classList.remove("four");
  });

  modMypage.addEventListener("click", () => {
    modMypage.classList.add("active");
    // modMypageIcon.classList.add("active");
    modMainBtn.classList.remove("active");
    modUser.classList.remove("active");
    unscribe.classList.remove("active");
    // modMainIcon.classList.remove("active");
    // modUserIcon.classList.remove("active");
    // unscribeIcon.classList.remove("active");
    line.classList.add("three");
    line.classList.remove("two");
    line.classList.remove("one");
    line.classList.remove("four");
  });

  unscribe.addEventListener("click", () => {
    unscribe.classList.add("active");
    // unscribeIcon.classList.add("active");
    modMainBtn.classList.remove("active");
    modMypage.classList.remove("active");
    modUser.classList.remove("active");
    // modMainIcon.classList.remove("active");
    // modMypageIcon.classList.remove("active");
    // modUserIcon.classList.remove("active");
    line.classList.add("four");
    line.classList.remove("one");
    line.classList.remove("three");
    line.classList.remove("two");
  });

  modMainBtn.addEventListener("click", () => {
    first.classList.add("active");
    second.classList.remove("active");
    third.classList.remove("active");
    fourth.classList.remove("active");
  });

  modUser.addEventListener("click", () => {
    first.classList.remove("active");
    second.classList.add("active");
    third.classList.remove("active");
    fourth.classList.remove("active");
  });

  modMypage.addEventListener("click", () => {
    first.classList.remove("active");
    second.classList.remove("active");
    third.classList.add("active");
    fourth.classList.remove("active");
  });

  unscribe.addEventListener("click", () => {
    first.classList.remove("active");
    second.classList.remove("active");
    third.classList.remove("active");
    fourth.classList.add("active");
  });
}

const modifyUser = (userInfo) => {
  userInfo.user_password = 'qwer1234';
  store.dispatch("modifyUser",userInfo);
}


const checkPassword = (userInfo,password_for_mod) =>{
  store.dispatch("checkPassword",{userInfo,password_for_mod});
}

const makeUserNickName = (user_id) => {
  store.dispatch('makeUserNickName',user_id);
}

const leaveUser = (user_id) => {
  store.dispatch('leaveUser',user_id);

}
export default {design,modifyUser,checkPassword,makeUserNickName,leaveUser};




// 이거 추가해주세요!!!!!!!!!!!!!!!!!!!
// document.querySelectorAll('.button').forEach(button => button.addEventListener('click', e => {
//   if(!button.classList.contains('delete')) {
//       button.classList.add('delete');
//       setTimeout(() => button.classList.remove('delete'), 3200);
//   }
//   e.preventDefault();
// }));







// var password = document.querySelector('.setting_pw_check');
// var error = document.querySelector('.pw_error_message');
// var padlock = document.querySelector('.padlock');
// var arrow = document.querySelector('.pw_check_arrow');
// var input = document.querySelector('.pw_check_icon');
// var pass = "dupa";
// password.addEventListener('keypress', function(e){
// var key = e.which || e.keyCode;
// if(key === 13){
//   if(password.value != ""){
//     if(password.value != pass){
//       error.classList.add('show');
//       padlock.classList.remove('unlock');
//     }else{
//       error.classList.remove('show');
//       unlock();
//     }
//   }
// }
// });
// password.addEventListener('keyup', function(){
// if(password.value == ""){
//   error.classList.remove('show');
//   padlock.classList.remove('unlock');
//   arrow.classList.remove('show');
// }else{
//   arrow.classList.add('show');
// }
// });
// arrow.addEventListener('click', function(){
//   if(password.value != pass){
//     error.classList.add('show');
//     padlock.classList.remove('unlock');
//   }else{
//     error.classList.remove('show');
//     unlock();
//   }
// },false);
// function unlock(){
//   padlock.classList.add('unlock');
//   input.classList.add('unlock');
//   setTimeout(function(){
//     padlock.classList.add('hide');
//     setTimeout(function(){
//       // If the password is correct
//       window.location.href = '';
//     }, 900);
//   }, 600);
// }