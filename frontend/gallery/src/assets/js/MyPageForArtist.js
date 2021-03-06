import router from '../../router'
import http from '../../api/http'
const init = () => {

  const profileItem = document.querySelector(".profile_menu");
  const topBtn = document.querySelector(".move_to_top");
  // console.log('top Btn',topBtn);
  const gallery1 = document.querySelector(".Artist__gallery__entrance_1");
  const gallery2 = document.querySelector(".Artist__gallery__entrance_2");

  const options = document.querySelector('.options')
  let activeOption = document.querySelector('.active')
  if(activeOption === null){
    activeOption = document.querySelectorAll('.option')[0]
    activeOption.classList.add('active')
    
  }
  

  options.addEventListener('click',function(e){
      if(e.target.classList.contains('option')){
      if(e.target.dataset.value !== activeOption.dataset.value ){
        activeOption.classList.remove('active');
        e.target.classList.add('active');
        activeOption = e.target;
      }
      }
    })
  
    if(gallery1){
      gallery1.addEventListener('click',function(e){
        // console.log('click event',e.target.dataset.value);
        if(e.target.parentNode.dataset.value){
          router.push({'name':'GalleryPage',params:{props_id:e.target.parentNode.dataset.value}})
        }
      })
    }
      
  if(gallery2){

    gallery2.addEventListener('click',function(e){
      // console.log('click event',e.target.dataset.value);
      if(e.target.parentNode.dataset.value){
        router.push({'name':'GalleryPage',params:{props_id:e.target.parentNode.dataset.value}})
      }
    })
  }
  const pintModal = document.querySelector('.third__section')
  // const seasonModal = document.querySelector('.pinter__side .photo-count')
  const exitSide = document.querySelector('.exit_side')
  // const exitSeason = document.querySelector('.exit_season')
  pintModal.addEventListener('click',function(e){
    if(e.target.classList.contains('show__my__pint')){
      gotoDetail()
      const pintSide = document.querySelector('.pinter__side');
      pintSide.classList.add('active_side');
    }
    })
  exitSide.addEventListener('click',function(){
    // console.log('click')
      const pintSide = document.querySelector('.pinter__side');
      pintSide.classList.remove('active_side');
    })

function gotoDetail(){
  // console.log('????????????.')
  const imgList = document.querySelector('.pinter_item-lists')
  imgList.addEventListener('click',function(e){
    if(e.target.classList.contains('pinter_grid__body')){
      const work_id = e.target.dataset.value
      // console.log(work_id)
      router.push({name:'ItemDetailPage',params:{work_id:work_id}})
      
    }
  })

}

function manuclickHandler(e){
  const targetItem = e.target;
  if(targetItem.classList.contains("profile_menu_item")){
    let goToElem;
    if(targetItem.dataset.value === "1"){
      // console.log('click')
      goToElem = document.querySelector('.second__section');
    }else if(targetItem.dataset.value === "2"){
      goToElem = document.querySelector('.third__section');
    }else if(targetItem.dataset.value === "3"){
      goToElem = document.querySelector('.forth__section');
    }else if(targetItem.dataset.value === "4"){
      return;
    }
    scrollIt(goToElem)
  }
}

function scrollIt(ele){
  // console.log(ele)
  topBtn.classList.add('move_to_top_show');
  window.scrollTo({
    'behavior':'smooth',
    'left':0,
    'top':ele.offsetTop
  });
}

function topclickHandler(){
  const first = document.querySelector(".artist_profile_wrapper");
  scrollIt(first);
}

profileItem.addEventListener('click',manuclickHandler)


topBtn.addEventListener('click',topclickHandler)


window.addEventListener('scroll',function(){
  let position = document.querySelector('.second__section');
  if(position){
  let position_2 = position.getBoundingClientRect().top;
    if(position_2 <= 0 ){
      topBtn.classList.add('move_to_top_show');
    }
    if(position_2 === window.innerHeight){
  
      topBtn.classList.remove('move_to_top_show');
    }
  }
})

function moveToArtistPage(artist_id){
  if(artist_id === localStorage.getItem('user_id')){
      router.push('/mypage');
  }else{
    router.push({name:'UserProfile',params:{props_id:artist_id}});
  }
  }

  const followers = document.querySelectorAll('.followers');
  const followings = document.querySelectorAll('.followings');

  followers.forEach(follower =>{
    follower.addEventListener('click',function(e){
      moveToArtistPage(e.target.innerText)
    })
  })
  followings.forEach(following =>{
    following.addEventListener('click',function(e){
      moveToArtistPage(e.target.innerText)
      
    })
  })
  
  // const scrollWrapper = document.querySelector('.scroll-wrapper');
  // const scrollWrapper_items = document.querySelectorAll('.scroll-wrapper img');
  // scrollWrapper.style.width = scrollWrapper_items.length*(30*16+72)+'px'


}


const getUserInfo = ()=>{
  // console.log('user_info_request',localStorage.getItem('props_id'));
  http.get('/user/getUserInfo', {
    params: {
      user_id: localStorage.getItem('props_id')
    }
  })
  .then(response=>{
    localStorage.setItem('props_type',response.data.user_type)
    // console.log('????????????. props_type???',localStorage.getItem('props_type'));

  })
}


const follow_modal = ()=>{
  // console.log('???????????????.')
  const notificationItem = document.querySelector('.notifications');
  notificationItem.addEventListener('click',function(e){
    if(e.target.classList.contains('notifications__item')){
      localStorage.setItem('props_id',e.target.dataset.name)
      getUserInfo();
      setTimeout(()=>{
        moveToArtistPage(localStorage.getItem('props_type'),localStorage.getItem('props_id'));
      },400);
      
    }
  })


  /*
  * Get all the buttons actions
  */
  const optionBtns = document.querySelectorAll( '.js-option' );
  // console.log(optionBtns);
  for(var i = 0; i < optionBtns.length; i++ ) {

    /*
    * When click to a button
    */
    optionBtns[i].addEventListener( 'click', function (e ){
      // console.log('click here',e.target.dataset.name);
      localStorage.setItem('props_id',e.target.dataset.name)
      getUserInfo();
      setTimeout(()=>{
        // console.log(' ?????? ?????? ??????.')
        // notification item
        var notificationCard = this.parentNode.parentNode;
        var clickBtn = this;
        /*
        * Execute the delete or Archive animation
        */
        requestAnimationFrame( function(){ 
  
          archiveOrDelete( clickBtn, notificationCard );
  
          /*
          * Add transition
          * That smoothly remove the blank space
          * Leaves by the deleted notification card
          */
          window.setTimeout( function( ){
            requestAnimationFrame( function() {
              notificationCard.style.transition = 'all .4s ease';
              notificationCard.style.height = 0;
              notificationCard.style.margin = 0;
              notificationCard.style.padding = 0;
            });
  
          //   /*
          //   * Delete definitely the animation card
          //   */
            window.setTimeout( function( ){
              notificationCard.parentNode.removeChild( notificationCard );
            }, 1500 );
          }, 1500 );
        });
      },500)

      });
  }

  /*
  * Function that adds
  * delete or archive class
  * To a notification card
  */
  var archiveOrDelete = function( clickBtn, notificationCard ){
    if( clickBtn.classList.contains( 'archive' ) ){
      // console.log('??????');
      notificationCard.classList.add( 'archive' );
      moveToArtistPage(localStorage.getItem('props_type'));

    } else if( clickBtn.classList.contains( 'delete' ) ){
      // tounfollow();
      notificationCard.classList.add( 'delete' );
    }
  }

}

const moveToArtistPage = (user_type)=>{
  // console.log('?????? user type??????',user_type);
  if(localStorage.getItem('props_id') === localStorage.getItem('user_id')){
    if(localStorage.getItem('user_type') == 1){
      // console.log('guest ???????????????.');
      router.push('/guestmypage');
    }else{
      router.push('/mypage');
    }
  }else if(user_type=='2'){
    // console.log('???????????? ???????????????',router.history.current.name);
    if(router.history.current.name ==='ArtistMyPage'){
      router.go();
    }else{
      router.push({name:'ArtistMyPage',params:{props_id:localStorage.getItem('props_id')}})
    }
  }else if(user_type=='1'){
    if(router.history.current.name === 'GuestMyPage'){
      router.go();
    }else{
      // console.log('???????????? ??????',localStorage.getItem('props_id'),typeof localStorage.getItem('props_id'));
      router.push({name:'GuestMyPage',params:{props_id: localStorage.getItem('props_id')}});
    }
  }
}

export default {init,follow_modal};
