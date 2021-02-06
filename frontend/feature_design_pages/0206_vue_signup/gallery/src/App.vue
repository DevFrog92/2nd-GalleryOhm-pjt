<template>
  <div id="app">
    <div v-if="headState">
    <HeaderPage></HeaderPage>
    </div>
    <router-view :key="$route.fullPath"/>
  </div>
</template>


<script>
import '../src/assets/css/ResetCss.css'
import HeaderPage from './pages/HeaderPage'
export default {
  data(){
    return {
      headState:true,
    }
  },
  components:{
    HeaderPage
  },
  watch:{
    $route(to){
      if(to.path === ''){this.headState=false;}
      else{this.headState=true;}
    }
  }
}
</script>

<style>
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
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
