import router from '../../router'
import http from '../../api/http'
const init = () => {

  const profileItem = document.querySelector(".profile_menu");
  const topBtn = document.querySelector(".move_to_top");

function manuclickHandler(e){
  const targetItem = e.target;
  if(targetItem.classList.contains("profile_menu_item")){
    let goToElem;
    if(targetItem.dataset.value === "1"){
      goToElem = document.querySelector('.second__section');
    }else if(targetItem.dataset.value === "2"){
      goToElem = document.querySelector('.third__section');
    }else if(targetItem.dataset.value === "3"){
      goToElem = document.querySelector('.forth__section__guest');
    }
    scrollIt(goToElem)
  }
}

function scrollIt(ele){
  topBtn.classList.add('move_to_top_show');
  window.scrollTo({
    'behavior':'smooth',
    'left':0,
    'top':ele.offsetTop
  });
}

function topclickHandler(){
  const first = document.querySelector(".guest_profile_wrapper");
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
  http.get('/user/getUserInfo', {
    params: {
      user_id: localStorage.getItem('props_id')
    }
  })
  .then(response=>{
    localStorage.setItem('props_type',response.data.user_type)
  })
}


const follow_modal = ()=>{
  /*
  * Get all the buttons actions
  */
  const optionBtns = document.querySelectorAll( '.js-option' );
  for(var i = 0; i < optionBtns.length; i++ ) {

    /*
    * When click to a button
    */
    optionBtns[i].addEventListener( 'click', function (e ){
      localStorage.setItem('props_id',e.target.dataset.name)
      getUserInfo();
      setTimeout(()=>{
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
      notificationCard.classList.add( 'archive' );
      moveToArtistPage(localStorage.getItem('props_type'));
    } else if( clickBtn.classList.contains( 'delete' ) ){
      // tounfollow();
      notificationCard.classList.add( 'delete' );
    }
  }

}

const moveToArtistPage = (user_type)=>{
  if(localStorage.getItem('props_id') === localStorage.getItem('user_id')){
    if(localStorage.getItem('user_type') == 1){
      router.push('/guestmypage');
    }else{
      router.push('/mypage');
    }
  }else if(user_type=='2'){
    if(router.history.current.name ==='ArtistMyPage'){
      router.go();
    }else{
      router.push({name:'ArtistMyPage',params:{props_id:localStorage.getItem('props_id')}})
    }
  }else if(user_type=='1'){
    if(router.history.current.name === 'GuestMyPage'){
      router.go();
    }else{
      router.push({name:'GuestMyPage',params:{props_id: localStorage.getItem('props_id')}});
    }
  }



  




}

const DMModal =() =>{
  const DMList = document.querySelector('.Guest__dm__list');

  const dmOption = document.querySelectorAll('.Guest__DM__item__option')

  dmOption.forEach(btn => {
    btn.addEventListener('click',function(e){
      const DMLIST = document.querySelectorAll('.Guest__DM__item')
      for(let item of DMLIST){
        if(e.target.dataset.value === item.dataset.name){
          item.classList.add('delete');
          http.post('/message/deleteMessage',Number(e.target.dataset.value))
          .then(() => {
          })
        }
      }
    })
  })

  DMList.addEventListener('click',function(e){
    if(e.target.classList.contains('Guest__DM__item'))
    {
      const response_data = JSON.parse(e.target.dataset.value);
      const messages = document.querySelectorAll('.Guest__DM__item')
      for(let item of messages){
        if(item.classList.contains('message__selected')){
          item.classList.remove('message__selected')
        }
      }
      e.target.classList.add('message__selected');



      
      message_read(response_data.message_id,e.target);

      const sender_name = document.querySelector('.Guest__message__name__cover .sender_name');
      const sender_title = document.querySelector('.Guest__message__title__cover .sender_title');
      const sender_content = document.querySelector('.Guest__message__content__cover .sender_content');
      const sendersender_time_name = document.querySelector('.Guest__message__date__tiem .sender_time');
      const sendersender_date_name = document.querySelector('.Guest__message__date__tiem .sender_date');

      sender_name.innerText = response_data.message_senderId;
      sender_title.innerText ="제목. " + response_data.message_title;
      sender_content.innerText = response_data.message_content;
      sendersender_time_name.innerText = response_data.message_sendDate.slice(11);
      sendersender_date_name.innerText = response_data.message_sendDate.slice(0,11);
    }
  })
}

const message_read = (message_id,target)=>{
  http.post('/message/checkMessage',Number(message_id))
  .then(() => {
    target.classList.add('Guest__DM__item__read');
  })
}

export default {init,follow_modal,DMModal};
