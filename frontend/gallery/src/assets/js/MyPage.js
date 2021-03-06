import router from '../../router'
import http from '../../api/http'
const init = () => {

  const profileItem = document.querySelector(".profile_menu");
  const topBtn = document.querySelector(".move_to_top");
  const gallery1 = document.querySelector(".Mypage__gallery__entrance_1");
  const gallery2 = document.querySelector(".Mypage__gallery__entrance_2");

  const options = document.querySelector('.Mypage__options')
  
  let activeOption = document.querySelector('.active')
  if(activeOption === null){
    activeOption = document.querySelectorAll('.Mypage__option')[0]
    activeOption.classList.add('active')
    
  }
  

 
  options.addEventListener('click',function(e){
    if(e.target.classList.contains('Mypage__option')){
    if(e.target.dataset.value !== activeOption.dataset.value ){
      activeOption.classList.remove('active');
      e.target.classList.add('active');
      activeOption = e.target;
    }
    }
  })


  const scrap = document.querySelector('.scroll-wrapper')
  if(scrap){
    scrap.addEventListener('click',function(e){
      if(e.target.classList.contains("scrap__image")){
        const work_id = e.target.dataset.value;
        router.push({name:'WorkDetailFirstPage',params:{work_id:work_id}})
      }
    }) 
  }
  
  
  if(gallery1){
    gallery1.addEventListener('click',function(e){
      // console.log('click event',e.target.parentNode.dataset.value);
      if(e.target.parentNode.dataset.value){
      router.push({'name':'GalleryPage',params:{props_id:e.target.parentNode.dataset.value}})
      }
    })
  }

  if(gallery2){
  gallery2.addEventListener('click',function(e){
    // console.log('click event',e.target.parentNode.dataset.value);
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
      router.push({name:'WorkDetailFirstPage',params:{work_id:work_id}})
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
  if(ele!=undefined){
  window.scrollTo({
    'behavior':'smooth',
    'left':0,
    'top':ele.offsetTop
  });}
}

function topclickHandler(){
  // console.log('click move to top')
  const first = document.querySelector("#headerpage-header");
  scrollIt(first);
  // window.scrollTo(0,0);
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
    if(position_2 >= window.innerHeight){
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
  if(scrollWrapper){
    scrollWrapper.style.width = scrollWrapper_items.length*(30*16+72)+'px'
  }


}

const getUserInfo = ()=>{
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
  // 
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
    // console.log('??????',localStorage.getItem('props_type'));
    notificationCard.classList.add( 'archive' );
    moveToArtistPage(localStorage.getItem('props_type'),localStorage.getItem('props_id'));
  } else if( clickBtn.classList.contains( 'delete' ) ){
    tounfollow();
    notificationCard.classList.add( 'delete' );
  }
}

}

const moveToArtistPage = (user_type,user_id)=>{
  // console.log('?????? user type??????',user_type);
  if(user_id === localStorage.getItem('user_id')){
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
      router.push({name:'ArtistMyPage',params:{props_id:user_id}})
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




const DMModal =() =>{
  const DMList = document.querySelector('.Mypage__dm__list');

  const dmOption = document.querySelectorAll('.Mypage__dm-option')

  dmOption.forEach(btn => {
    btn.addEventListener('click',function(e){
      // console.log('click to delete',e.target)
      const DMLIST = document.querySelectorAll('.Mypage__DM__item')
      for(let item of DMLIST){
        if(e.target.dataset.value === item.dataset.name){
          item.classList.add('delete');       
          http.post('/message/deleteMessage',Number(e.target.dataset.value))
          .then(response => {
           console.log('remove message',response.data);
           const sender_name = document.querySelector('.sender_name');
            const sender_title = document.querySelector('.sender_title');
            const sender_content = document.querySelector('.sender_content');
            const sendersender_time_name = document.querySelector('.sender_time');
            const sendersender_date_name = document.querySelector('.sender_date');
            sender_name.innerText = '';
            sender_title.innerText ="??????. ";
            sender_content.innerText = '';
            sendersender_time_name.innerText ='';
            sendersender_date_name.innerText = '';
          })
        }
      }
    })
  })

  // console.log('DM__list',DMList);
  DMList.addEventListener('click',function(e){
    if(e.target.classList.contains('Mypage__DM__item'))
    {
      // console.log('?????? ?????? ?????????.')
      // console.log(JSON.parse(e.target.dataset.value));
      const response_data = JSON.parse(e.target.dataset.value);
      const messages = document.querySelectorAll('.Mypage__DM__item')
      for(let item of messages){
        if(item.classList.contains('message__selected')){
          item.classList.remove('message__selected')
        }
      }
      e.target.classList.add('message__selected');



      
      message_read(response_data.message_id,e.target);
      // e.target.classList.add('Mypage__DM__item__read')
      const sender_name = document.querySelector('.sender_name');
      const sender_title = document.querySelector('.sender_title');
      const sender_content = document.querySelector('.sender_content');
      const sendersender_time_name = document.querySelector('.sender_time');
      const sendersender_date_name = document.querySelector('.sender_date');
      sender_name.innerText = response_data.message_senderId;
      sender_title.innerText ="??????. " + response_data.message_title;
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
const tounfollow=()=>{
  const formData = {
    follow_artistId: localStorage.getItem('props_id'),
    follow_userId:localStorage.getItem('user_id')
  }
  http.post('/follow/cancelFollow',formData)
  .then(
    // console.log(response.data,'unfollow');
  )
}
export default {init,follow_modal,DMModal};
