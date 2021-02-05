<template>
 <div class="gallery__render">
   <div id="lamp">
    <input type="radio" name="switch" value="on" checked="checked" />
    <input type="radio" name="switch" value="off"  />
    <div class="lamp">
      <div class="gonna-give-light"></div>
    </div>
  </div>
  <div class="close__btn" @click="goBack">
    <i class="icon-back"></i>
  </div>
  <!-- <div class="qurating" @click="qurating=!qurating">
    <i class="icon-mic-1" v-if="!qurating"></i>
    <i class="icon-mic-off" v-else></i>
    <audio v-show="qurating" class="player" controls ref="player"> <source src="" ref="source"> </audio>
  </div> -->
  <div class="main__carousel">
    <div class="carousel__body">
      <div class="carousel__prev"><i class="icon-left-open"></i></div>
      <div class="carousel__next"><i class="icon-right-open"></i></div>
      <div class="carousel__slider">

              <div class="carousel__slider__item"  v-for="(item,index) of work_list" :key="index">
                <div class="item__3d-frame">
                  <div class="item__3d-frame__box item__3d-frame__box--front">
                    <img class="gallery__image" :data-value="item.work_id" :data-index="index" :src="item.work_piece" alt="">
                  </div>
                  <div class="item__3d-frame__box item__3d-frame__box--left"></div>
                  <div class="item__3d-frame__box item__3d-frame__box--right"> </div>
                </div>
                <div class="item__title">
                <h1>{{item.work_title}}</h1>            
              </div>

        </div>
      </div>
    </div>
  </div>

</div>
</template>

<script>
import '../assets/css/GalleryRenderPage.css'
import init from '../assets/js/GalleryRenderPage.js'
import '../assets/fontello/css/fontello.css'
import http from '../api/http'
export default {
  data (){
    return {
      work_list:[],
      gallery_desc:'',
      qurating:false,
      gallery_id:'',
    }
  },
  mounted(){
    setTimeout(()=>{
      init.init();
    },1000 
    ),
    setTimeout(()=>{
      this.tts();
    },1000)
  },
  created(){
    return http.get(`/gallery/getMyGallery/${localStorage.getItem('user_id')}`)
    .then((response)=>{
      this.gallery_id = response.data[response.data.length-1].gallery_id
        http.get(`/gallery/getArtistGallery/${this.gallery_id}`)
            .then((response)=>{
              const workList = response.data;

              for(let i=0;i<workList.length;i++){
                workList[i].work_piece = "data:image/jpeg;base64," + workList[i].work_piece;
                console.log(workList[i].work_id);
              }
              this.work_list = workList;
            })
            .then(()=>{
              http.get(`/gallery/getGallery/${this.gallery_id}`)
              .then(response=>{
                this.gallery_desc = response.data.gallery_desc;
              })
            })
            .catch((err)=>{
              console.log(err);
            })
    })
    
    
  },
  methods:{
    tts: function() {
      console.log(this.gallery_desc);
      return http
        .post(
          `/kakao/makeVoice_byteArr`,
          {
            content:
              this.gallery_desc,
          },
          { responseType: "blob" }
        )
        .then(
          (response) => {
            var blob = new Blob([response.data], { type: "audio/mp3" });
            var url = window.URL.createObjectURL(blob);
            this.audioSrc = url;

            this.$refs.source.src = url; 
            this.$refs.player.load(); //업로드완료 후 파일로딩
          },
          (error) => {
            console.log(error);
          }
        );
    },
    goBack(){
      this.$router.go(-1);
    }
  }

}
</script>

<style>

</style>