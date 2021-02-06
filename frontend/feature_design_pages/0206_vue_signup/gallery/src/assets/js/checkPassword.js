import http from '../../api/http'

const checkPassword = (input_password,user_id) =>{
  http.post(`/user/checkPassword?input_password=${input_password}&user_id=${user_id}`)
  .then(response => {
    // console.log(response,'function')
    return response.data
  }).catch(err => {
    alert(err);
  })
}
export default {checkPassword}