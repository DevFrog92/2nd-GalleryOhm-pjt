<template>
  <header id='headerpage-header'>
    <div class="header-container">
      <a class="logo" href="/">
        <img src="../assets/images/ssaatchi.png" alt="">
      </a>
      <div>
        <!-- <h3>{{nickName}}</h3> -->
      </div>
      <div class="nav">
      <router-link class="nav-item" to="/">|  메인  |</router-link>
      <div v-if="loginState">
      <router-link class="nav-item" to="/listitem">작품 갤러리  |</router-link>
      <!-- <router-link class="nav-item" to="/detail">Detail</router-link> -->
      <router-link class="nav-item" to="/galleryrender">작가 갤러리  |</router-link>
      <router-link class="nav-item" to="/adult">레드 갤러리관  |</router-link>
      <router-link class="nav-item" to="/workupload">작품 등록  |</router-link>
      <router-link class="nav-item" to="/creategallery">전시관 등록  |</router-link>
      <router-link class="nav-item" to="/mypage">마이페이지 |</router-link>
      <router-link class="nav-item" to="/settings">설정  |</router-link>
      <a href="#" @click.prevent="logout">로그아웃  |</a>
      </div>
      <div v-else>
      <router-link class="nav-item" to="/loginsignup">Login & Signup</router-link>
      </div>
      </div>
    </div>
  </header>
</template>

<script>
import '../assets/css/HeaderPage.css'
export default {
  name : 'HeaderPage',
  data() {
    return {
    }
  },
  computed:{
    loginState(){
      return this.$store.state.state.auth_token;
    }
  },
  created(){
  },
  methods :{
    logout(){
      const logout_check = confirm('로그아웃하시겠습니까?');
      if(logout_check){
      localStorage.clear();
      this.$store.dispatch('logout');
      // console.log(this.$router.currentRoute);
      if(this.$router.currentRoute.name === 'MainPage'){
        this.$router.go(this.$router.currentRoute);
      }else{
        this.$router.push('/');
      }
      }
    },
  }
}
</script>

<style>

</style>