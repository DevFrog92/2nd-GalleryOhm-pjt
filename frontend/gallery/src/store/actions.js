import http from "../api/http.js";
import router from "../router";

const login = (context, userInfo) => {
  http
    .post(
      `/login/login?user_id=${userInfo.user_id}&user_password=${userInfo.user_password}`
    )
    .then((response) => {
      context.commit("login", response.data);
      http.defaults.headers.common[
        "auth-token"
      ] = `${response.data["auth_token"]}`;
      localStorage.setItem("user_nickName", response.data.user_nickName);
      router.push("/");
    })
    .catch(() => {
      alert("아이디 혹은 비밀번호를 확인하세요.");
    });
};

const leaveUser = (context, user_id) => {
  http.get(`/user/leaveUser?user_id=${user_id}`).then(() => {
    context.commit("leaveUser");
    alert("이용해 주셔서 감사합니다.");
    router.push("/");
  });
};

const findPw = (context, userInfo) => {
  context.commit("findpw", userInfo);
};

const findId = (context, userInfo) => {
  context.commit("findid", userInfo);
};

const addWork = (context, info) => {
  var formData = new FormData();
  formData.append("work_artistId", info.work_artistId);
  formData.append("work_title", info.work_title);
  formData.append("work_desc", info.work_desc);
  formData.append("work_piece", info.work_piece);
  formData.append("work_rating", info.work_rating);
  formData.append("work_tool", info.work_tool);
  formData.append("hashTags", info.hashTags);

  // response 다시 확인 할 것
  return http
    .post(`/work/addWork`, formData, {
      headers: { "Content-Type": "multipart/form-data" },
    })
    .then(() => {
      const user_id = localStorage.getItem("user_id");
      alert(`작품이 등록되었습니다. ${user_id}작가님!`);
      router.push("/work");
    })
    .catch(() => {});
};

const modifyWork = (context, info) => {
  var formData = new FormData();
  formData.append("work_artistId", info.work_artistId);
  formData.append("work_id", info.work_id);
  formData.append("work_title", info.work_title);
  formData.append("work_desc", info.work_desc);
  formData.append("work_piece", info.work_piece);
  formData.append("work_rating", info.work_rating);
  formData.append("work_tool", info.work_tool);
  formData.append("hashTags", info.hashTags);

  // response 다시 확인 할 것
  return http
    .post(`/work/modifyWork`, formData, {
      headers: { "Content-Type": "multipart/form-data" },
    })
    .then(() => {
      const user_id = localStorage.getItem("user_id");

      alert(`작품이 수정되었습니다. ${user_id}작가님!`);
      router.push("/work");
    })
    .catch(() => {
      // console.log(error);
    });
};

const modifyUser = (context, modifyUserInfo) => {
  let formData = new FormData();

  formData.append("user_birth", modifyUserInfo.user_birth);
  formData.append("user_email", modifyUserInfo.user_email);
  formData.append("user_id", modifyUserInfo.user_id);
  formData.append("user_name", modifyUserInfo.user_name);
  formData.append("user_nickName", modifyUserInfo.user_nickName);
  formData.append("user_password", modifyUserInfo.user_password);
  formData.append("user_profile", modifyUserInfo.user_profile);
  formData.append("user_tel", modifyUserInfo.user_tel);

  http
    .post("/user/modifyUser", formData, {
      headers: { "Content-Type": "multipart/form-data" },
    })
    .then(() => {
      alert("회원 정보가 성공적으로 수정되었습니다!");
      context.commit("modifyUser", modifyUserInfo);
      localStorage.setItem("user_nickName", modifyUserInfo.user_nickName);
      router.go(router.currentRoute);
    })
    .catch((err) => {
      console.log(err);
    });
};

const logout = (context) => {
  context.commit("clearInfo");
};

const checkPassword = (context, payload) => {
  http
    .post(
      `/user/checkPassword?input_password=${payload.password_for_mod}&user_id=${payload.userInfo.user_id}`
    )
    .then((response) => {
      if (response.data.state === "succ") {
        localStorage.setItem("temp_check", true);

        context.commit("checkPassword");
      } else {
        //  alert('아이디 혹은 비밀번호를 확인해 주세요.');
      }
    })
    .catch((err) => {
      alert(err);
    });
};

const makeUserNickName = (context, payload) => {
  http.post(`/user/makeUserNickName?user_id=${payload}`).then((response) => {
    context.commit("makeUserNickName", response.data);
  });
};

const deleteWork = (context, payload) => {
  http.get(`/work/deleteWork/${payload}`).then(() => {});
};

const BookMark = (context, payload) => {
  http.get("/work/scrapWork/", { params: payload }).then(() => {});
};
const UnBookMark = (context, payload) => {
  http.get("/work/clearToWorkScrap/", { params: payload }).then(() => {});
};
export default {
  login,
  findPw,
  findId,
  modifyUser,
  leaveUser,
  addWork,
  logout,
  checkPassword,
  makeUserNickName,
  modifyWork,
  deleteWork,
  BookMark,
  UnBookMark,
};
