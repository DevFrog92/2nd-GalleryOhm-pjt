const objectCelar = (userInfo) => {
  for(let key in userInfo){
    userInfo[key] = '';
  }
}

export default {objectCelar};