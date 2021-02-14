import router from '../../router'
import http from '../../api/http'
const init = () => {

  const profileItem = document.querySelector(".profile_menu");
  const topBtn = document.querySelector(".move_to_top");
  const gallery1 = document.querySelector(".poster_card1");
  const gallery2 = document.querySelector(".poster_card2");

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


  const scrap = document.querySelector('.scroll-wrapper')
  scrap.addEventListener('click',function(e){
    if(e.target.classList.contains("scrap__image")){
      const work_id = e.target.dataset.value;
      router.push({name:'ItemDetailPage',params:{work_id:work_id}})
    }
  })

  
  

  gallery1.addEventListener('click',function(e){
    console.log('click event',e.target.dataset.value);
    if(e.target.dataset.value){
    router.push({'name':'GalleryRenderPage',params:{props_id:e.target.dataset.value}})
    }
  })


  gallery2.addEventListener('click',function(e){
    console.log('click event',e.target.dataset.value);
    if(e.target.dataset.value){
      router.push({'name':'GalleryRenderPage',params:{props_id:e.target.dataset.value}})
    }
  })
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
    console.log('click')
      const pintSide = document.querySelector('.pinter__side');
      pintSide.classList.remove('active_side');
    })
  // exitSeason.addEventListener('click',function(){
  //   const season = document.querySelector('.show_season_works')
  //   season.classList.remove('active_season');
  // })
  // seasonModal.addEventListener('click',function(){
  //   console.log('season click')
  //   const season = document.querySelector('.show_season_works')
  //   season.classList.add('active_season');
  // })
function gotoDetail(){
  console.log('실행한다.')
  const imgList = document.querySelector('.pinter_item-lists')
  imgList.addEventListener('click',function(e){
    if(e.target.classList.contains('pinter_grid__body')){
      const work_id = e.target.dataset.value
      console.log(work_id)
      router.push({name:'ItemDetailPage',params:{work_id:work_id}})
    }
  })

}

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
      goToElem = document.querySelector('.forth__section');
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

  // const followers = document.querySelectorAll('.followers');
  // const followings = document.querySelectorAll('.followings');

  // followers.forEach(follower =>{
  //   follower.addEventListener('click',function(e){
  //     moveToArtistPage(e.target.innerText)
  //   })
  // })
  // followings.forEach(following =>{
  //   following.addEventListener('click',function(e){
  //     moveToArtistPage(e.target.innerText)
      
  //   })
  // })
  
  const scrollWrapper = document.querySelector('.scroll-wrapper');
  const scrollWrapper_items = document.querySelectorAll('.scroll-wrapper img');
  scrollWrapper.style.width = scrollWrapper_items.length*(30*16+72)+'px'


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
    moveToArtistPage(localStorage.getItem('props_type'),localStorage.getItem('props_id'));
  } else if( clickBtn.classList.contains( 'delete' ) ){
    // tounfollow();
    notificationCard.classList.add( 'delete' );
  }
}

}

const moveToArtistPage = (user_type,user_id)=>{
  console.log('현재 user type이야',user_type);
  if(user_id === localStorage.getItem('user_id')){
    if(localStorage.getItem('user_type') == 1){
      console.log('guest 이동합니다.');
      router.push('/guestpage');
    }else{
      router.push('/mypage');
    }
  }else if(user_type=='2'){
    console.log('이동할게 아티스트로',router.history.current.name);
    if(router.history.current.name ==='UserProfile'){
      router.go();
    }else{
      router.push({name:'UserProfile',params:{props_id:user_id}})
    }
  }else if(user_type=='1'){
    console.log('이동할게 이제',localStorage.getItem('props_id'),typeof localStorage.getItem('props_id'));
    router.push({name:'GuestProfile',params:{props_id: user_id}});
  }
}

const message_read = (message_id)=>{
  const formData = new FormData()
  formData.append('message_ids',Array(message_id))
  http.post('/message/checkMessage/',formData)
  .then(response => {
    console.log('read message',response.data);
  })
}


const DMModal =() =>{
  const DMList = document.querySelector('.dm__list');

  const dmOption = document.querySelectorAll('.dm-option')

  dmOption.forEach(btn => {
    btn.addEventListener('click',function(e){
      console.log('click to delete',e.target)
      const DMLIST = document.querySelectorAll('.DM__item')
      for(let item of DMLIST){
        if(e.target.dataset.value === item.dataset.name){
          item.classList.add('delete');
          // const formData = {
          //   message_ids: Array(e.target.dataset.value)
          // }
          // http.post('/message/deleteMessage',formData)
          // .then(response => {
          //  console.log('remove message',response.data);    
          // })
        }
      }
    })
  })

  console.log('DM__list',DMList);
  DMList.addEventListener('click',function(e){
    if(e.target.classList.contains('DM__item'))
    {
      console.log('읽음 처리 합니다.')
      console.log(JSON.parse(e.target.dataset.value));
      const response_data = JSON.parse(e.target.dataset.value);
      
      message_read(response_data.message_id);
      e.target.classList.add('DM__item__read')
      const sender_name = document.querySelector('.sender_name');
      const sender_title = document.querySelector('.sender_title');
      const sender_content = document.querySelector('.sender_content');
      const sendersender_time_name = document.querySelector('.sender_time');
      const sendersender_date_name = document.querySelector('.sender_date');
      sender_name.innerText = response_data.message_senderId;
      sender_title.innerText ="Title. " + response_data.message_title;
      sender_content.innerText = response_data.message_content;
      sendersender_time_name.innerText = response_data.message_sendDate.slice(11);
      sendersender_date_name.innerText = response_data.message_sendDate.slice(0,11);
      // 
      const showDmContent = document.querySelector('.showDmContent')
      showDmContent.classList.add('showDmContentActive');
    }
  })
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
export default {init,follow_modal,DMModal};
