

const init = ()=>{


  


  const ROOT = document.documentElement;
  const MIN = 10;
  const THRESHOLD = innerHeight * (1.2 - 0.225);
  const update = () => {
    const scroll = Math.floor(scrollY / innerHeight * 100);
    if (scrollY > THRESHOLD) {
      const progress = Math.floor((scrollY - THRESHOLD) / (document.body.scrollHeight - innerHeight - THRESHOLD) * 100);
      ROOT.style.setProperty('--content', progress);
    }
    ROOT.style.setProperty('--scroll', Math.max(0, Math.min(scroll, 100 - MIN)));
  };
  
  window.addEventListener('scroll', update);
  window.addEventListener('scroll',function(){
    console.log('scoll',window.scrollY);
    const img = document.querySelector('.artist__work__detail');
    if(img){

      if( window.scrollY >= 800){
        img.style.zIndex = 100;
        console.log('붙혔다')
      }else{
        img.style.zIndex = 1;
      }
    }
    })
   const imgSacleUp = document.querySelector('.artist__work__detail');
   imgSacleUp.addEventListener('click',function(){
     const scaleUpModal = document.querySelector('.imgSacleUp')
     scaleUpModal.classList.add('imgSacleUpshow');
   })
   const closeModal = document.querySelector('.scaleUp');
   closeModal.addEventListener('click',function(){
     const scaleUpModal = document.querySelector('.imgSacleUp')
     scaleUpModal.classList.remove('imgSacleUpshow');
   })
  }
  
  export default {init};