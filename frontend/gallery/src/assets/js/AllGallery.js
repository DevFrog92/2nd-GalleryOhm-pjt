// import  router from '../../router'
const init =()=>{
  const itemList = document.querySelector('.gallery_poster-lists');

  function clickHandler(e) {
    if(e.target.classList.contains('gallery__poster')){
      console.log(e.target)
      console.log(e.target);
      // router.push({name:'ItemDetailPage',params:{work_id:e.target.dataset.value}});
    }
  }


  itemList.addEventListener('click',clickHandler);
}

export default {init};