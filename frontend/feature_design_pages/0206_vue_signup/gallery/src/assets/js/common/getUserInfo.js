import http from '../../../api/http'

const getUserInfo = (user_id) => {
    http.get('/user/getUserInfo',{params:{user_id}})
    .then(reponse => {
      return reponse.data;
  })
}

export default {getUserInfo};