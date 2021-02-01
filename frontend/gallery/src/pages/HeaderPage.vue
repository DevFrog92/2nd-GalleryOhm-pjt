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
      <router-link class="nav-item" to="/">Main</router-link>
      <router-link class="nav-item" to="/listitem">List</router-link>
      <!-- <router-link class="nav-item" to="/detail">Detail</router-link> -->
      <!-- <router-link class="nav-item" to="/mypage">Mypage</router-link> -->
      <!-- <router-link class="nav-item" to="/galleryrender">GalleryRender</router-link> -->
      <div v-if="loginState">
      <router-link class="nav-item" to="/workupload">WorkUpLoad</router-link>
      <router-link class="nav-item" to="/settings">Settings</router-link>
      <a href="#" @click.prevent="logout">Logout</a>
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