import store from '../../store/store'
const design = () =>{ 
  const divs = document.querySelectorAll('.setting_menu div');
const lis = document.querySelectorAll('.setting_con li');

divs.forEach(target => {
  target.addEventListener('click',function(e){
    checkDivIndex(divs,e.target.dataset.value);
    checkLiIndex(lis,e.target.dataset.value);
  })
})


const checkDivIndex = (divs,index) =>{
  for(let item of divs ){
    if(item.classList.contains('active')){
        item.classList.remove('active');
        divs[index-1].classList.add('active');
    }
  }
}

const checkLiIndex = (lis,index) =>{
  for(let item of lis ){
    if(item.classList.contains('active')){
        item.classList.remove('active');
        lis[index-1].classList.add('active');
    }
  }
}

  // const unsubBtn = document.querySelector('.unscribe_button');
  // unsubBtn.addEventListener('click', e => {
  //   e.target.classList.add('delete')
  //   setTimeout(() => 
  //     e.target.classList.remove('delete')
  //     // leaveUser(localStorage.getItem('user_id'))
  //     , 3200);
  // e.preventDefault();
  // });


  ////
  if(!localStorage.getItem('checkPassword_state'))
  {
  var password = document.querySelector('.setting_pw_check');
  var error = document.querySelector('.pw_error_message');
  var padlock = document.querySelector('.padlock');
  var arrow = document.querySelector('.pw_check_arrow');
  var input = document.querySelector('.pw_check_icon');
  password.addEventListener('keypress', function(e){
  var key = e.which || e.keyCode;
  if(key === 13){
    const userInfo ={
      "user_id":localStorage.getItem('user_id')
    }
    checkPassword(userInfo,password.value);
    setTimeout(() => {
      const pass = localStorage.getItem('temp_check');
      if(password.value != ""){
        if(!pass){
          console.log('err');
          error.classList.add('show');
          padlock.classList.remove('unlock');
        }else{
          error.classList.remove('show');
          unlock();
        }
      }      
    }, 100);
  }
  });
  password.addEventListener('keyup', function(){
  if(password.value == ""){
    error.classList.remove('show');
    padlock.classList.remove('unlock');
    arrow.classList.remove('show');
  }else{
    arrow.classList.add('show');
  }
  });


  arrow.addEventListener('click', function(){
    const userInfo ={
      "user_id":localStorage.getItem('user_id')
    }
    checkPassword(userInfo,password.value);
    setTimeout(() => {
      const pass = localStorage.getItem('temp_check');
      console.log(pass);
      if(!pass){
        error.classList.add('show');
        padlock.classList.remove('unlock');
      }else{
        error.classList.remove('show');
        unlock();
      }
    }, 100);
  },false);
  }


  function unlock(){
    padlock.classList.add('unlock');
    input.classList.add('unlock');
    setTimeout(function(){
      padlock.classList.add('hide');
      setTimeout(function(){
        // If the password is correct
        window.location.href = '';
        localStorage.setItem('checkPassword_state',true);
        localStorage.removeItem('temp_check');
      }, 700);
    }, 500);
  }
  ///

  function onClick() {
    document.querySelector('.rank_modal_wrap').style.display ='block';
}   
function offClick() {
    document.querySelector('.rank_modal_wrap').style.display ='none';
}

document.getElementById('rank_modal_btn').addEventListener('click', onClick);
document.querySelector('.rank_modal_close').addEventListener('click', offClick);

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