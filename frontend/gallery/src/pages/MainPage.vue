<template>
  <header style="margin-top:10rem;">
    <h3>{{nickName}}</h3>
  <div class="stage">
   <div class="test">
    <div class="carousel">
      <div class="panel"><img src="../assets/images/1.png" alt=""></div> 
      <div class="panel"><img src="../assets/images/2.png" alt=""></div>
      <div class="panel"><img src="../assets/images/3.png" alt=""></div>
      <div class="panel"><img src="../assets/images/4.png" alt=""></div>
      <div class="panel"><img src="../assets/images/5.png" alt=""></div>
      <div class="panel"><img src="../assets/images/1.png" alt=""></div>
      <div class="panel"><img src="../assets/images/1.png" alt=""></div> 
      <div class="panel"><img src="../assets/images/2.png" alt=""></div>
      <div class="panel"><img src="../assets/images/3.png" alt=""></div>
      <div class="panel"><img src="../assets/images/4.png" alt=""></div>
      <div class="panel"><img src="../assets/images/5.png" alt=""></div>
      <div class="panel"><img src="../assets/images/1.png" alt=""></div>
      <div class="panel"><img src="../assets/images/1.png" alt=""></div> 
      <div class="panel"><img src="../assets/images/2.png" alt=""></div>
      <div class="panel"><img src="../assets/images/3.png" alt=""></div>
      <div class="panel"><img src="../assets/images/4.png" alt=""></div>
      <div class="panel"><img src="../assets/images/5.png" alt=""></div>
      <div class="panel"><img src="../assets/images/1.png" alt=""></div>
      <div class="panel"><img src="../assets/images/1.png" alt=""></div> 
      <div class="panel"><img src="../assets/images/2.png" alt=""></div>
      <div class="panel"><img src="../assets/images/3.png" alt=""></div>
      <div class="panel"><img src="../assets/images/4.png" alt=""></div>
      <div class="panel"><img src="../assets/images/5.png" alt=""></div>
      <div class="panel"><img src="../assets/images/1.png" alt=""></div>
      <div class="panel"><img src="../assets/images/1.png" alt=""></div> 
      <div class="panel"><img src="../assets/images/2.png" alt=""></div>
      <div class="panel"><img src="../assets/images/3.png" alt=""></div>
      <div class="panel"><img src="../assets/images/4.png" alt=""></div>
      <div class="panel"><img src="../assets/images/5.png" alt=""></div>
      <div class="panel"><img src="../assets/images/1.png" alt=""></div>
      <div class="panel"><img src="../assets/images/1.png" alt=""></div> 
      <div class="panel"><img src="../assets/images/2.png" alt=""></div>
      <div class="panel"><img src="../assets/images/3.png" alt=""></div>
      <div class="panel"><img src="../assets/images/4.png" alt=""></div>
      <div class="panel"><img src="../assets/images/5.png" alt=""></div>
      <div class="panel"><img src="../assets/images/1.png" alt=""></div>
    </div>
   </div>
  </div>
    <!-- <audio class="player" controls ref="player"> <source src="" ref="source"> </audio>
    <button type="button" @click="tts">오디오 할당</button> -->
  </header>
</template>

<script>
import '../assets/css/MainPage.css'
// import http from '../api/http'
export default {
  data(){
    return {
      // nickName: "Anonymous"
    }
  },
  computed: {
    nickName(){
      return this.$store.state.state.user_nickName;
    }
  },
  created(){
  },
  methods:{
    // tts: function() {
    //   return http
    //     .post(
    //       `/kakao/makeVoice_byteArr`,
    //       {
    //         content:
    //           "이 작품은 빈센트 반 고흐의 별이 빛나는 밤에라는 작품을 모작하여 그린 작품입니다. 즐거운 감상시간 되세요.",
    //       },
    //       { responseType: "blob" }
    //     )
    //     .then(
    //       (response) => {
    //         var blob = new Blob([response.data], { type: "audio/mp3" });
    //         var url = window.URL.createObjectURL(blob);
    //         this.audioSrc = url;

    //         this.$refs.source.src = url; 
    //         this.$refs.player.load(); //업로드완료 후 파일로딩
    //       },
    //       (error) => {
    //         console.log(error);
    //       }
    //     );
    // }
  },
  mounted(){
    this.nickName = localStorage.getItem('user_nickName');
  }
}
</script>

<style>

</style>