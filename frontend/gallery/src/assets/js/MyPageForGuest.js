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
      console.log('click')
      goToElem = document.querySelector('.second__section');
    }else if(targetItem.dataset.value === "2"){
      goToElem = document.querySelector('.third__section');
    }else if(targetItem.dataset.value === "3"){
      goToElem = document.querySelector('.forth__section__guest');
      console.log(goToElem)
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
    console.log('저장한다. props_type값',localStorage.getItem('props_type'));
  })
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
      getUserInfo();
      setTimeout(()=>{
        console.log(' 시작 텀을 준다.')
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
      console.log('이동',localStorage.getItem('props_type'));
      notificationCard.classList.add( 'archive' );
      moveToArtistPage(localStorage.getItem('props_type'));
    } else if( clickBtn.classList.contains( 'delete' ) ){
      // tounfollow();
      notificationCard.classList.add( 'delete' );
    }
  }

}

const moveToArtistPage = (user_type)=>{
  console.log('현재 user type이야',user_type);
  if(localStorage.getItem('props_id') === localStorage.getItem('user_id')){
    if(localStorage.getItem('user_type') == 1){
      console.log('guest 이동합니다.');
      router.push('/guestmypage');
    }else{
      router.push('/mypage');
    }
  }else if(user_type=='2'){
    console.log('이동할게 아티스트로',router.history.current.name);
    if(router.history.current.name ==='ArtistMyPage'){
      router.go();
    }else{
      router.push({name:'ArtistMyPage',params:{props_id:localStorage.getItem('props_id')}})
    }
  }else if(user_type=='1'){
    if(router.history.current.name === 'GuestMyPage'){
      router.go();
    }else{
      console.log('이동할게 이제',localStorage.getItem('props_id'),typeof localStorage.getItem('props_id'));
      router.push({name:'GuestMyPage',params:{props_id: localStorage.getItem('props_id')}});
    }
  }



  




}

const DMModal =() =>{
  const DMList = document.querySelector('.Guest__dm__list');

  const dmOption = document.querySelectorAll('.Guest__DM__item__option')

  dmOption.forEach(btn => {
    btn.addEventListener('click',function(e){
      console.log('click to delete',e.target)
      const DMLIST = document.querySelectorAll('.Guest__DM__item')
      for(let item of DMLIST){
        if(e.target.dataset.value === item.dataset.name){
          item.classList.add('delete');
          http.post('/message/deleteMessage',Number(e.target.dataset.value))
          .then(response => {
           console.log('remove message',response.data);    
          })
        }
      }
    })
  })

  console.log('DM__list',DMList);
  DMList.addEventListener('click',function(e){
    if(e.target.classList.contains('Guest__DM__item'))
    {
      console.log('읽음 처리 합니다.')
      console.log(JSON.parse(e.target.dataset.value));
      const response_data = JSON.parse(e.target.dataset.value);
      const messages = document.querySelectorAll('.Guest__DM__item')
      for(let item of messages){
        if(item.classList.contains('message__selected')){
          item.classList.remove('message__selected')
        }
      }
      e.target.classList.add('message__selected');



      
      message_read(response_data.message_id,e.target);
      // e.target.classList.add('Mypage__DM__item__read')
      console.log(response_data)
      const sender_name = document.querySelector('.Guest__message__name__cover .sender_name');
      const sender_title = document.querySelector('.Guest__message__title__cover .sender_title');
      const sender_content = document.querySelector('.Guest__message__content__cover .sender_content');
      const sendersender_time_name = document.querySelector('.Guest__message__date__tiem .sender_time');
      const sendersender_date_name = document.querySelector('.Guest__message__date__tiem .sender_date');
      console.log('sender_name',sender_name,response_data.message_senderId)
      console.log('sender_title',sender_title ,response_data.message_title)
      console.log('sender_content',sender_content,response_data.message_content)
      sender_name.innerText = response_data.message_senderId;
      sender_title.innerText ="제목. " + response_data.message_title;
      sender_content.innerText = response_data.message_content;
      sendersender_time_name.innerText = response_data.message_sendDate.slice(11);
      sendersender_date_name.innerText = response_data.message_sendDate.slice(0,11);
      // 
      // const showDmContent = document.querySelector('.Mypage__showDmContent')
      // showDmContent.classList.add('MypageshowDmContentActive');
    }
  })
}

const message_read = (message_id,target)=>{
  http.post('/message/checkMessage',Number(message_id))
  .then(response => {
    console.log('read message',response.data);
    target.classList.add('Guest__DM__item__read');
  })
}



// const message_read = (message_id)=>{
//   const formData = new FormData()
//   formData.append('message_ids',Array(message_id))
//   http.post('/message/checkMessage/',formData)
//   .then(response => {
//     console.log('read message',response.data);
//   })
// }


// const DMModal =() =>{
//   const DMList = document.querySelector('.dm__list_props');
// }


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
export default {init,follow_modal,DMModal};
