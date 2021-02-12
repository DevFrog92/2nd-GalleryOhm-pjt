<template>
  <header id='headerpage-header'>
      <nav id="nav"><button class="nav-icon" id="nav-icon"><span></span></button>
        <ul class="nav_ul" v-if="loginState">
          <li><router-link  class="router" to="/listitem">Works</router-link></li>
          <li><router-link  class="router" to="/gallerycategory">Gallery</router-link></li>
          <li><router-link  class="router" to="/mypage">Mypage</router-link></li>
          <li><router-link  class="router" to="/about">About</router-link></li>
          <li><a  href="#" @click="logout">Logout</a></li>
        </ul>
        <ul class="nav_ul" v-else>
          <li><router-link class="nav-item" to="/loginsignup">Login & Signup</router-link></li>
        </ul>
      </nav>
  </header>
</template>

<script>
  import '../assets/css/HeaderPage.css'
  import init from '../assets/js/Navbar'
  export default {
    name: 'HeaderPage',
    data() {
      return {}
    },
    computed: {
      loginState() {
        return this.$store.state.state.auth_token;
      }
    },
    mounted() {
      init.init();
    },
    created() {},
    methods: {
      logout() {
        const logout_check = confirm('로그아웃하시겠습니까?');
        if (logout_check) {
          localStorage.clear();
          this.$store.dispatch('logout');
          // console.log(this.$router.currentRoute);
          if (this.$router.currentRoute.name === 'MainPage') {
            this.$router.go(this.$router.currentRoute);
          } else {
            this.$router.push('/');
          }
        }
      },
    }
  }
</script>

<style>

</style>