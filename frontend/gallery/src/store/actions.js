import http from '../api/http.js'
import router from '../router'

const login = (context,userInfo) => {
  // console.log(context,userInfo.user_id,userInfo.user_password,'login');
  http.post(`/login/login?user_id=${userInfo.user_id}&user_password=${userInfo.user_password}`).then(response => {
    console.log('log in',response.data);
    context.commit('login',response.data);
    http.defaults.headers.common[
      'auth-token'
    ] = `${response.data['auth_token']}`;
    localStorage.setItem("user_nickName",response.data.user_nickName);
    router.push('/');
    
  }).catch(() => {
    alert('아이디 혹은 비밀번호를 확인하세요.');
  })
}

// const join = (context,userInfo) => {
//   // console.log(context,userInfo,'signUp');
//   http.post('/user/joinUser',userInfo).then(response => {
//     alert('회원가입을 축하합니다. 모달 띄우기');
//     context.commit('join',response.data);
//   })
// }



const leaveUser = (context,user_id) =>{
  // console.log('actions');
  setTimeout(()=>{
    http.get(`/user/leaveUser?user_id=${user_id}`).then(()=>{
      context.commit("leaveUser");
      alert('이용해 주셔서 감사합니다.');
      router.push('/');
    })
  },3200);
}

const findPw = (context,userInfo) => {
  // console.log(context,userInfo,'actions');
  context.commit('FINDPW',userInfo,);
}

const findId = (context,userInfo) => {
  // console.log(context,userInfo,'actions');
  context.commit('FINDID',userInfo);

}

const addWork = (context, info) => {
  // console.log(info.work_artistId);
  // console.log(info.work_piece);

  var formData = new FormData();
  formData.append("work_artistId", info.work_artistId);
  formData.append("work_title", info.work_title);
  formData.append("work_desc", info.work_desc);
  formData.append("work_piece", info.work_piece);
  formData.append("work_rating", info.work_rating);
  formData.append("work_tool", info.work_tool);

  for(let key of formData.entries()){
    console.log(`${key}`);
  }
  // response 다시 확인 할 것
  return http.post(`/work/addWork`, formData,{ headers :
                      {'Content-Type' : 'multipart/form-data'}
                    }).then((response) => {
                      const user_id = localStorage.getItem('user_id')
                      console.log(response.data);
                      alert(`작품이 등록되었습니다. ${user_id}작가님!`)
                      router.push('/listitem');
                      // console.log(response);
                    }).catch(() => {
                      // console.log(error);
                    });
}

const modifyWork = (context, info) => {
  // console.log(info.work_artistId);
  // console.log(info.work_piece);

  var formData = new FormData();
  formData.append("work_artistId", info.work_artistId);
  formData.append("work_id", info.work_id);
  formData.append("work_title", info.work_title);
  formData.append("work_desc", info.work_desc);
  formData.append("work_piece", info.work_piece);
  formData.append("work_rating", info.work_rating);
  // formData.append("work_tool", info.work_tool);

  for(let key of formData.entries()){
    console.log(`${key}`);
  }
  // response 다시 확인 할 것
  return http.post(`/work/modifyWork`, formData,{ headers :
                      {'Content-Type' : 'multipart/form-data'}
                    }).then((response) => {
                      const user_id = localStorage.getItem('user_id')
                      console.log(response.data);
                      alert(`작품이 수정되었습니다. ${user_id}작가님!`)
                      router.push('/listitem');
                      // console.log(response);
                    }).catch(() => {
                      // console.log(error);
                    });
}

const modifyUser = (context,modifyUserInfo) => {
  // console.log('modify',modifyUserInfo);

  let formData = new FormData();

  formData.append('user_birth', modifyUserInfo.user_birth);
  formData.append('user_email',modifyUserInfo.user_email);
  formData.append('user_id',modifyUserInfo.user_id);
  formData.append('user_name',modifyUserInfo.user_name);
  formData.append('user_nickName',modifyUserInfo.user_nickName);
  formData.append('user_password',modifyUserInfo.user_password);
  formData.append('user_profile',modifyUserInfo.user_profile);
  formData.append('user_tel',modifyUserInfo.user_tel);
  
  http.post('/user/modifyUser',formData,{headers :
  {'Content-Type':'multipart/form-data'}})
  .then(()=>{
    alert('회원 정보가 성공적으로 수정되었습니다!')
    context.commit('modifyUser',modifyUserInfo);
    localStorage.setItem('user_nickName',modifyUserInfo.user_nickName);
    router.go(router.currentRoute);

  }).catch((err) =>{
    console.log(err);
  })
}

const logout = (context) => {
  context.commit('clearInfo');
}

const checkPassword = (context,payload) =>{
  console.log('actions',payload)
  http.post(`/user/checkPassword?input_password=${payload.password_for_mod}&user_id=${payload.userInfo.user_id}`)
 .then(response => {
   if (response.data.state === 'succ') {
    //  alert('인증되었습니다.');
     localStorage.setItem('temp_check',true);
      // router.go(router.currentRoute);
     context.commit('checkPassword');
   }else{
    //  alert('아이디 혹은 비밀번호를 확인해 주세요.');
   }
 }).catch(err => {
   alert(err);
 })
}

const makeUserNickName = (context,payload) =>{
  http.post(`/user/makeUserNickName?user_id=${payload}`)
      .then(response =>{
        // console.log(response.data);
        context.commit('makeUserNickName',response.data);
      })
}
export default {login,findPw,findId,modifyUser,leaveUser,addWork,logout,checkPassword,makeUserNickName,modifyWork};