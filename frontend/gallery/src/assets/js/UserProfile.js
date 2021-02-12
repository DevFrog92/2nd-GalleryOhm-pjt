import router from '../../router'
// import http from '../../api/http'
const init = () => {

  const profileItem = document.querySelector(".profile_menu");
const topBtn = document.querySelector(".move_to_top")



function manuclickHandler(e){
  const targetItem = e.target;
  if(targetItem.classList.contains("profile_menu_item")){
    let goToElem;
    if(targetItem.dataset.value === "1"){
      goToElem = document.querySelector('.second__section');
    }else if(targetItem.dataset.value === "2"){
      goToElem = document.querySelector('.third__section');
    }else if(targetItem.dataset.value === "3"){
      goToElem = document.querySelector('.forth__section');
    }else if(targetItem.dataset.value === "4"){
      goToElem = document.querySelector('.fifth__section');
    }
    scrollIt(goToElem)
  }
}

function scrollIt(ele){
  console.log(ele)
  topBtn.classList.add('move_to_top_show');
  window.scrollTo({
    'behavior':'smooth',
    'left':0,
    'top':ele.offsetTop
  });
}

function topclickHandler(){
  const first = document.querySelector(".profile_wrapper");
  scrollIt(first);
}

profileItem.addEventListener('click',manuclickHandler)


topBtn.addEventListener('click',topclickHandler)


window.addEventListener('scroll',function(){
  let position_2 = document.querySelector('.second__section').getBoundingClientRect().top;
  if(position_2 <= 0 ){
    topBtn.classList.add('move_to_top_show');
  }
  if(position_2 === window.innerHeight){

    topBtn.classList.remove('move_to_top_show');
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


const follow_modal = ()=>{
  console.log('실행되었다.')
  /*
  * Get all the buttons actions
  */
  const optionBtns = document.querySelectorAll( '.js-option' );
  console.log(optionBtns);
  for(var i = 0; i < optionBtns.length; i++ ) {

    /*
    * When click to a button
    */
    optionBtns[i].addEventListener( 'click', function (e ){
      console.log('click here',e.target.dataset.name);
      localStorage.setItem('props_id',e.target.dataset.name)
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
    })
  }

  /*
  * Function that adds
  * delete or archive class
  * To a notification card
  */
  var archiveOrDelete = function( clickBtn, notificationCard ){
    if( clickBtn.classList.contains( 'archive' ) ){
      console.log('이동');
      notificationCard.classList.add( 'archive' );
      moveToArtistPage();
    } else if( clickBtn.classList.contains( 'delete' ) ){
      // tounfollow();
      notificationCard.classList.add( 'delete' );
    }
  }

}


const moveToArtistPage = ()=>{
  if(localStorage.getItem('props_id') === localStorage.getItem('user_id')){
      router.push('/mypage');
  }else{
    console.log('이동할게 이제',localStorage.getItem('props_id'));
    router.push({name:'UserProfile',params:{props_id: localStorage.getItem('props_id')}});
  }
}


// const tounfollow=()=>{
//   const formData = {
//     follow_artistId: localStorage.getItem('props_id'),
//     follow_userId:localStorage.getItem('user_id')
//   }
//   http.post('/follow/cancelFollow',formData)
//   .then(response=>{
//     console.log(response.data,'unfollow');
//   })
// }
export default {init,follow_modal};
