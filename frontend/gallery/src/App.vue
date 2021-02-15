<template>
  <div id="app">
    <HeaderPage></HeaderPage>
    <router-view :key="$route.fullPath" />
  </div>
</template>


<script>
  import '../src/assets/css/ResetCss.css'
  import HeaderPage from './pages/HeaderPage'
  export default {
    data() {
      return {}
    },
    components: {
      HeaderPage
    },
  }
</script>

<style>
@font-face {
  font-family: 'S-CoreDream-8Heavy';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-8Heavy.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}
@import url(//fonts.googleapis.com/earlyaccess/notosanskr.css);
  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;

  }

  #nav {
    padding: 30px;
  }

  #nav a {
    font-weight: bold;
    color: #42329f;
  }

  #nav a.router-link-exact-active {
    color: #42329f;
  }
</style>