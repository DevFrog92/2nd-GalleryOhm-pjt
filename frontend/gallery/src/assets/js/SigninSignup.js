import check from '../../api/CheckForm'
import store from '../../store/store'
import common from '../../assets/js/common/objectCelar'
import http from '../../api/http'

const join = (userInfo) =>{
  // console.log(userInfo,"js");
  if(userInfo.user_password === userInfo.user_passwordConfirm){
    const err = check.checkFrom(userInfo);
    if (!err.email) {userInfo.user_email = "";}
    if (!err.password) {userInfo.user_password = ""; userInfo.user_passwordConfirm="";}
    if (!err.tel) {userInfo.user_tel = "";}
    if (err.showModal){
    http.post('/user/joinUser',userInfo).then(() => {
        // alert('회원가입을 축하합니다.');
        activeBtn();
        common.objectCelar(userInfo)
      })
    }
  }else{
    alert("비밀번호가 일치하지 않습니다.");
    userInfo.user_password='';
    userInfo.user_passwordConfirm = '';
    return false;
  }
}

const login = (userInfo) => {
  store.dispatch('login',userInfo);
  // common.objectCelar(userInfo);
}

const activeBtn = () => {
  const container = document.querySelector("#singup_container");
  container.classList.toggle("right-panel-active");        
}
const toggleModalId = () => {
  // console.log('modal show');
  const modal = document.querySelector(".find_modal");
  modal.classList.toggle("show-modal");
}
const toggleModalPw = () => {
  const modalPw = document.querySelector(".find_modal_pw");
  modalPw.classList.toggle('show_modal_pw');
}

const findPassword = (userInfo) => {
  const email = check.checkEmail(userInfo.user_email);
  const tel = check.checkTel(userInfo.user_tel);
  if (!email){userInfo.user_email = ''; return;}
  if (!tel){userInfo.user_tel = ''; return;}
  store.dispatch('findPw',userInfo).then(()=>{
    common.objectCelar();
  })
}
const findId = (userInfo) => {
  const email = check.checkEmail(userInfo.user_email);
  if (!email){userInfo.user_email = ''; return;}
  store.dispatch('findId',userInfo).then(()=>{
    common.objectCelar();
  });
}

const checkId = (user_id) => {
  // console.log(user_id);
  const formData = new FormData();
  formData.append('input_id',user_id)
  http.post('/user/checkId',formData)
  .then(response => {
    return response.data;
  }).catch(err => {
    console.log(err);
  })
}


const toggleModalInit = ()=>{


const termsModal = document.querySelector(".terms_modal");
const triggerTerms = document.querySelector(".triggerTerms");
const closeButtonTerms = document.querySelector(".close_button_terms");

triggerTerms.addEventListener("click", toggleModalTerms);
closeButtonTerms.addEventListener("click", toggleModalTerms);
window.addEventListener("click", windowOnClickTerms);
function toggleModalTerms(event){
  event.preventDefault();
  termsModal.classList.toggle("show_modal_terms");
}
function windowOnClickTerms(event){
  if (event.target === termsModal) {
    toggleModalTerms();
}
}



}

export default {join,login,activeBtn,toggleModalId,toggleModalPw,findPassword,findId,checkId,toggleModalInit};