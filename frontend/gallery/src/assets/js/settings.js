import store from '../../store/store'

const design = () => {
  const modMainBtn = document.querySelector(".modmain");
  const modMainIcon = modMainBtn.firstChild;
  const modUser = document.querySelector(".moduser");
  // const modMypage = document.querySelector(".modpage");
  const unscribe = document.querySelector(".unscribe");
  const modUserIcon = modUser.firstChild;
  // const modMypageIcon = modMypage.firstChild;
  const unscribeIcon = unscribe.firstChild;
  const line = document.querySelector("#line");
  const first = document.querySelector("#first");
  const second = document.querySelector("#second");
  const third = document.querySelector("#third");
  const fourth = document.querySelector("#fourth");




  modMainBtn.addEventListener("click", () => {
    modMainBtn.classList.add("active");
    modMainIcon.classList.add("active");
    modUser.classList.remove("active");
    // modMypage.classList.remove("active");
    unscribe.classList.remove("active");
    modUserIcon.classList.remove("active");
    // modMypageIcon.classList.remove("active");
    unscribeIcon.classList.remove("active");
    line.classList.add("one");
    line.classList.remove("two");
    line.classList.remove("three");
    line.classList.remove("four");
  });

  modUser.addEventListener("click", () => {
    modUser.classList.add("active");
    modUserIcon.classList.add("active");
    modMainBtn.classList.remove("active");
    // modMypage.classList.remove("active");
    unscribe.classList.remove("active");
    modMainIcon.classList.remove("active");
    // modMypageIcon.classList.remove("active");
    unscribeIcon.classList.remove("active");
    line.classList.add("two");
    line.classList.remove("one");
    line.classList.remove("three");
    line.classList.remove("four");
  });

  // modMypage.addEventListener("click", () => {
  //   modMypage.classList.add("active");
  //   modMypageIcon.classList.add("active");
  //   modMainBtn.classList.remove("active");
  //   modUser.classList.remove("active");
  //   unscribe.classList.remove("active");
  //   modMainIcon.classList.remove("active");
  //   modUserIcon.classList.remove("active");
  //   unscribeIcon.classList.remove("active");
  //   line.classList.add("three");
  //   line.classList.remove("two");
  //   line.classList.remove("one");
  //   line.classList.remove("four");
  // });

  unscribe.addEventListener("click", () => {
    unscribe.classList.add("active");
    unscribeIcon.classList.add("active");
    modMainBtn.classList.remove("active");
    // modMypage.classList.remove("active");
    modUser.classList.remove("active");
    modMainIcon.classList.remove("active");
    // modMypageIcon.classList.remove("active");
    modUserIcon.classList.remove("active");
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

  // modMypage.addEventListener("click", () => {
  //   first.classList.remove("active");
  //   second.classList.remove("active");
  //   third.classList.add("active");
  //   fourth.classList.remove("active");
  // });

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