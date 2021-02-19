const init = ()=>{

  const menuBtn = document.querySelector('.nav__items');
  
  menuBtn.addEventListener('click',function(){
    const radioBtn = document.querySelector('.nav__cb')
    if(radioBtn.checked){
    radioBtn.checked = false;
      
    }else{
    radioBtn.checked = true;
      
    }
  })
  }
  
  export default {init};