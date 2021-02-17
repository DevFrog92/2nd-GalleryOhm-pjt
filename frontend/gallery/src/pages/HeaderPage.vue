<template>
  <header id='headerpage-header'>
    <div class="nav__left">
      <router-link to="/"> <div class="nav__logo"></div></router-link>
      <button class="unit__audio unit__btn__audios" id="main_bgm" type="button" @click="muteAudio"><img src="../assets/images/Main/audio.png" alt=""
              class='all__audios'></button>
              <button class="unit__audio unit__btn__audios" id="main_bgm2" type="button" @click="playAudio"><img src="../assets/images/Main/audio_mute.png" alt=""
              class='all__audios'></button>
        <audio
          controls
          id="sound"
          loop
          preload="auto"
          src="../assets/images/Main/bgm.mp3"
        ></audio>
    </div>
      <nav class="nav" >
    <input type="checkbox" class="nav__cb" id="menu-cb" />
    <div class="nav__content">
      <ul class="nav__items"  v-if="loginState">
        <li class="nav__item">
          <span class="nav__item-text">
           <router-link  class="router" to="/exhibition">전시회</router-link>
          </span>
        </li>
        <li class="nav__item">
          <span class="nav__item-text">
           <router-link  class="router" to="/work">작품전</router-link>
          </span>
        </li>
        <li class="nav__item">
          <span class="nav__item-text">
            <router-link  class="router" to="/redgallery">레드관</router-link>
          </span>
        </li>
        <li class="nav__item">
          <span class="nav__item-text">
            <a class="router" @click="moveToMypage">내정보</a>
          </span>
        </li>
         <li class="nav__item" v-if="user_type===0">
          <span class="nav__item-text">
            <router-link  class="router" to="/AdminPage1">관장실</router-link>
          </span>
        </li>
        <li class="nav__item">
          <span class="nav__item-text">
            <router-link  class="router" to="/about">옴스토리</router-link>
          </span>
        </li>
          <li class="nav__item">
          <span class="nav__item-text">
            <a  href="#" @click="logout">로그아웃</a>
          </span>
        </li>
      </ul>
      <ul class="nav__items" v-else>
         <li class="nav__item">
          <span class="nav__item-text">
          </span>
        </li>
         <li class="nav__item">
          <span class="nav__item-text">
          </span>
        </li>
         <li class="nav__item">
          <span class="nav__item-text">
          </span>
        </li>
         <li class="nav__item">
          <span class="nav__item-text">
          </span>
        </li>
         <li class="nav__item">
          <span class="nav__item-text">
      <router-link class="nav-item" to="/loginsignup">로그인</router-link>
          </span>
        </li>
      </ul>
      
    </div>
    <label class="nav__btn" for="menu-cb"></label>
  </nav>
  </header>
</template>

<script>
  import '../assets/css/HeaderPage.css'
  import init from '../assets/js/Navbar'
  export default {
    name: 'HeaderPage',
    data() {
      return {
        user_type:localStorage.getItem('user_type'),
        user_id:localStorage.getItem('user_id'),
      }
    },
    computed: {
      loginState() {
        return this.$store.state.state.auth_token;
      }
    },
    mounted() {
      init.init();
    },
    created() {
    },
    methods: {
      logout() {
        const logout_check = confirm('로그아웃하시겠습니까?');
        if (logout_check) {
          localStorage.clear();
          this.user_id = '';
          this.user_type = '',
          this.$store.dispatch('logout');
          // console.log(this.$router.currentRoute);
          if (this.$router.currentRoute.name === 'MainPage') {
            this.$router.go(this.$router.currentRoute);
          } else {
            this.$router.push('/');
          }
        }
      },
      moveToMypage(){
        if(this.$router.history.current.name ==='MyPage' || this.$router.history.current.name ==='GuestMyPage'){
          this.$router.go();
          return
        }
        this.user_type = localStorage.getItem('user_type');
        this.user_id = localStorage.getItem('user_id');
        if(this.user_type === '1' || this.user_type === '0'){
          localStorage.setItem('props_id',this.user_id)
          this.$router.push('/guestmypage');
        }else if(this.user_type === '2'){
          this.$router.push('/mypage')
        }
      },
      muteAudio() {
      var audioBtn = document.querySelector(".nav__left #main_bgm");
      var audioMuteBtn = document.querySelector(".nav__left #main_bgm2");
      audioBtn.style.display = "none";
      audioMuteBtn.style.display = "block";

     var sound = document.querySelector(".nav__left #sound");
      sound.pause();
    },
    playAudio() {
      var audioBtn = document.querySelector(".nav__left #main_bgm");
      var audioMuteBtn = document.querySelector(".nav__left #main_bgm2");
      audioBtn.style.display = "block";
      audioMuteBtn.style.display = "none";

      var sound = document.querySelector(".nav__left #sound");
      sound.play();
    },
    }
  }
</script>

<style>

</style>