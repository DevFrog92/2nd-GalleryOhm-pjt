import axios from 'axios'
const instance = axios.create({
  baseURL:"http://localhost:7080/artGallery/api",
  headers: {
    "Content-type":"application/json",
    'Access-Control-Allow-Origin':"*",
    withCredential:true
  }
});

export default instance;