<template>
  <header id='headerpage-header'>
      <nav class="nav" >
    <input type="checkbox" class="nav__cb" id="menu-cb" />
    <div class="nav__content">
      <ul class="nav__items"  v-if="loginState">
        <li class="nav__item">
          <span class="nav__item-text">
           <router-link  class="router" to="/listitem">Works</router-link>
          </span>
        </li>
        <li class="nav__item">
          <span class="nav__item-text">
            <router-link  class="router" to="/gallerycategory">Gallery</router-link>
          </span>
        </li>
        <li class="nav__item">
          <span class="nav__item-text">
            <a class="router" @click="moveToMypage">Mypage</a>
          </span>
        </li>
        <li class="nav__item">
          <span class="nav__item-text">
            <router-link  class="router" to="/about">About</router-link>
          </span>
        </li>
          <li class="nav__item">
          <span class="nav__item-text">
            <a  href="#" @click="logout">Logout</a>
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
      console.log('user_type',this.user_type);
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
        this.user_type = localStorage.getItem('user_type');
        this.user_id = localStorage.getItem('user_id');
        console.log('누른다 ',typeof this.user_type)
        if(this.user_type === '1'){
          console.log('Guest')
          localStorage.setItem('props_id',this.user_id)
          this.$router.push('/guestpage');
        }else if(this.user_type === '2'){
          console.log('Artist')
          this.$router.push('/mypage')
        }
      }
    }
  }
</script>

<style>

</style>