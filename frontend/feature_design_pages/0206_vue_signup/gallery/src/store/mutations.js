const login = (state,payload) => { 
  // console.log(payload,'mutatins');
  changeInfo(state,payload);
}

// const join =(state,payload) => {
  // console.log(state,payload,'mutations');
// }

const modifyUser = (state,payload) =>{
  changeInfo(state,payload);
}


const leaveUser = (state) =>{
  clearInfo(state);
  localStorage.clear();
  // console.log('user info clear');
}

// const FINDPW =(state,payload) => {
  // console.log(state,payload,'mutations');
// }

// const FINDID =(state,payload) => {
//  console.log(state,payload,'mutations');
// }

const changeInfo = (state,payload) => {
  state.state.user_type = payload['user_type'];
  state.state.user_id = payload['user_id'];
  state.state.auth_token = payload['auth_token'];
  state.state.user_nickName = payload['user_nickName'];
  // console.log('changeInfo',state);
  
  localStorage.user_type = payload['user_type']
  localStorage.user_id = payload['user_id']
  localStorage.auth_token = payload['auth_token'];
}

const clearInfo = (state) => {
  state.state.user_type =  "";
  state.state.user_id =  "";
  state.state.auth_token =  "";
  state.state.user_nickName = "";
}


const makeUserNickName = (state,payload) => {
  // console.log('바꿀거야',payload)  ;
  state.state.user_nickName = payload;
  localStorage.setItem('user_nickName',payload);
  // console.log(state);
}

const checkPassword = (state) => {
  state.state.checkPasswordState = true;
  localStorage.setItem('checkPassword_state',true);
  // console.log(state.state.checkPasswordState);
}
export default {login,modifyUser,leaveUser,clearInfo,makeUserNickName,checkPassword};
//join,FINDPW,FINDID 다시 정의 필요