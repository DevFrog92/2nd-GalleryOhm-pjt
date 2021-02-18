const state = {
  user_type: localStorage.getItem("user_tyupe"),
  user_id: localStorage.getItem("user_id"),
  auth_token: localStorage.getItem("auth_token"),
  user_nickName: localStorage.getItem("user_nickName"),
  user_profile: localStorage.getItem("user_profile"),
  checkPasswordState: false,
  user_birth: localStorage.getItem("user_birth"),
};

export default { state };
