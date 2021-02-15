import axios from 'axios'
const instance = axios.create({
  // Local
  baseURL:"http://localhost:7080/artGallery/api", 
  // Server
  //baseURL:"http://i4b106.p.ssafy.io:7080/artGallery/api",
  headers: {
    "Content-type":"application/json",
    'Access-Control-Allow-Origin':"*",
    withCredential:true
  }
});

export default instance;