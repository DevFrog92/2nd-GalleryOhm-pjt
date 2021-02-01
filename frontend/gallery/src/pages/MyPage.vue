<template>
   <div class="my-page">
    <header id="mypage-header">

      <div class="profile">
        <span class="avatar">
          <img src="../assets/images/avatar.jpg" alt="avatar img">
        </span>
        <div class="about-artist">
          <h1>Lorem ipsum dolor sit amet.</h1>
          <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Autem dolore earum delectus voluptatibus ullam explicabo, et fuga ducimus blanditiis labore.</p>
        </div>
      </div>
      <div class="btns">
        <button class="btn js-btn" v-for='(menu,index) in menus' :key="index" @click='MoveToMenu(index)'>{{index+1}}.{{menu}}</button>
        <!-- <button class="btn js-btn">Section 2</button> -->
        <!-- <button class="btn js-btn">Section 3</button> -->
        <!-- <button class="btn js-btn">Section 4</button> -->
      </div>
      <div class="goToTop">
        <button class="btn js-btn">Top</button>
      </div>
    </header>


    <div class="section js-section1" > scroll-behavior: smooth - Section 1</div>
    <div class="section js-section2">scroll-behavior: smooth - Section 2</div>
    <div class="section js-section3">
      <ItemListPage></ItemListPage>
      </div>
    <div class="section js-section4">scroll-behavior: smooth - Section 4</div>
  </div>
</template>

<script>
import '../assets/css/MyPage.css'
import ItemListPage from '../pages/ItemLisetPage'
export default {
  components:{
    ItemListPage
  },
  data(){
    return {
      menus : ['Gallery','My Works','Favorites','About'],
    }
  },
  methods:{
    scrollIt(element){
      window.scrollTo({
        'behavior': 'smooth',
        'left': 0,
        'top': element.offsetTop
      });
    },
    MoveToMenu(index){
      const jsSection = document.querySelector(`.js-section${index+1}`);
      this.scrollIt(jsSection);
    },
    hasScrolled(){
      // console.log('scroll');
    },
    checkScroll(){
    var lastScrollTop = 0;
    var delta = 5;
    var fixBox = document.querySelector('.btns');
    var fixBoxTop = document.querySelector('.goToTop');
    // var fixBoxHeight = fixBox.offsetHeight;
    var didScroll = true;
    // console.log(fixBoxHeight);


  
    // 0.25초 마다 스크롤 여부를 체크하여 스크롤 둥이면 hasScrolled호출
    setInterval(function() {
    if (didScroll) {
      hasScrolled();
      didScroll = false;
    }
    }, 250);

    fixBoxTop.addEventListener('click', function() {
    window.scroll({
    top: 0,
    left: 0,
    behavior: 'smooth'
    });

    })
    function hasScrolled(){

      // console.log(document.querySelector('#headerpage-header').offsetHeight);
    
    var nowScrollTop = window.scrollY;
      if (Math.abs(lastScrollTop - nowScrollTop) <= delta) {
        return;
      }
      if (nowScrollTop >= document.querySelector('#mypage-header').offsetHeight+document.querySelector('#headerpage-header').offsetHeight-2) {
        // console.log('돌파');
        fixBox.classList.add('show');
        fixBoxTop.classList.add('show');

      } else {
        fixBox.classList.remove('show');
        fixBoxTop.classList.remove('show');
      }
      lastScrollTop = nowScrollTop;
    }
    }
  },
  created(){
    window.addEventListener('scroll',this.checkScroll);
  },
  destroyed(){
    window.addEventListener('scroll',this.checkScroll);
  }

}
</script>

<style>

</style>