import router from '../../router'
const init = () => {
  // var el = document.getElementById("all_gallery_wrapper");
 const galleryPoster = document.querySelector('.gallery_poster-lists')
  galleryPoster.addEventListener('click',function(e){
    if(e.target.classList.contains('gallery__poster')){
      console.log('target',e.target.dataset.value)
    }
  })

  const gallertLsit = document.querySelector('.gallery_poster-lists')
  gallertLsit.addEventListener('click',function(e){
    if(e.target.classList.contains('gallery__poster')){
      const gallery_id = e.target.dataset.value;
      console.log('gallery_id',gallery_id)
      router.push({name:'GalleryRenderPage',params:{props_id:gallery_id}})
    }
  })
}

export default {init};