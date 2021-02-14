<template>
  <div
    v-if="mainGalleryList.length && mainWorkList.length==mainGalleryList.length && mainWorkList.length==artistsInfo.length"
    class="Main_container"
  >
    <!-- FADE UP -->
    <div class="main_top_wrapper">
      <div class="banner">
      <div class="main_logo"></div>
      </div>
      <div class="DescriptionText">
        <!-- <vue-typed-js
          :strings="[
            '',
            'SSAATCH 전시관에 오신것을 환영합니다!',
            '저희는 도슨트 오져쓰입니다.',
            'SSAATCH 전시관의 멋진 작품들을 감상하세요!',
          ]"
        >
          <h1><span class="typing"></span></h1>
        </vue-typed-js> -->
      </div>
      <div class="downArrow" @click="downMain">
        <a href=".main_center_wrapper"></a>
      </div>
    </div>
    <!-- 메인 화면 -->
    <div class="main_center_wrapper">
      <hooper
        class="hooper"
        :settings="hooperSettings"
      >
        <slide v-for="(work, i) in mainWorkList" :key="i" :index="i">
          <!-- <router-link to="/test/GalleryJ/DetailPage"> -->
          <div class="main_img">
             <p class="number" v-if="i < 9">0{{ i + 1 }}</p>
            <p class="number" v-else-if="i >= 9">{{ i + 1 }}</p>
            <div class="main_poster_nickname">{{artistsInfo[i].artist_nickName}}</div>
          <div
          class="main_gallery__poster"
          @mouseover="hoverPoster(i)"
          @mouseleave="cancelhoverPoster(i)"
        >
          <div class="poster__header">
            {{ mainGalleryList[i].gallery_name }}
          </div>
          <div class="poster__writer">
            {{ mainGalleryList[i].gallery_artistId }}
          </div>
          <div class="poster__date">
            {{ mainGalleryList[i].gallery_writeTime }} ~
          </div>
          <div v-if="mainWorkList.length>0"
            class="main_poster__section"
            :style="{
              backgroundImage: 'url(' + mainWorkList[i].work_piece + ')',
            }"
          ></div>
          </div>
        </div>

          <!-- </router-link> -->
        </slide>
        <hooper-navigation slot="hooper-addons"></hooper-navigation>
      </hooper>
    </div>
  </div>
</template>

<script>
import http from "../../api/http";
// npm install --save vue-typed-js
// import Vue from "vue";
// import VueTypedJs from "vue-typed-js";
// Vue.use(VueTypedJs);

export default {
  data() {
    return {
      hooperSettings: {
        infiniteScroll: true,
        breakpoints: {
          2900: {
            itemsToShow: 9,
          },
          2600: {
            itemsToShow: 7,
          },
          1800: {
            itemsToShow: 3,
          },
          1700: {
            itemsToShow: 4,
          },
          1600: {
            itemsToShow: 4,
          },
          1500: {
            itemsToShow: 3,
          },
          1400: {
            itemsToShow: 3,
          },
          1100: {
            itemsToShow: 3,
          },
          900: {
            itemsToShow: 2,
          },
          500: {
            itemsToShow: 2,
          },
          0: {
            itemsToShow: 1,
          },
        },
      },
      mainGalleryList: {},
      mainWorkList: [],
      mainWorkImages: [],
      errored: false,
      errored2: false,
      artistsInfo: [],
    };
  },
  components: {Hooper: window.Hooper.Hooper,
    Slide: window.Hooper.Slide,
    HooperNavigation: window.Hooper.Navigation,},
  created() {
    this.getAllMainGallery();
  },
  mounted() {},
  updated() {},
  methods: {
    downMain() {
      window.scrollTo({
        behavior: "smooth",
        left: 0,
        top: document.querySelector(".main_center_wrapper").offsetTop,
      });
    },
    getAllMainGallery() {
      // 메인 갤러리 조회
      http
        .get("/gallery/getAllMainGallery")
        .then((response) => {
          var galleryDatas = response.data;
          for (var i = 0; i < galleryDatas.length; i++) {
            var workId = galleryDatas[i].gallery_mainWorkId;
            var artistId = galleryDatas[i].gallery_artistId;
            // 메인 갤러리의 메인 작품 조회
            this.getWork(workId);
            // 메인 갤러리의 작가 조회
            this.getArtistInfo(artistId);
            var yyyyMMdd = String(galleryDatas[i].gallery_writeTime);
            var year = yyyyMMdd.substring(0, 4);
            var month = yyyyMMdd.substring(5, 7);
            var day = yyyyMMdd.substring(8, 10);
            galleryDatas[i].gallery_writeTime =
              year + ". " + month + ". " + day + ".";
          }
          this.mainGalleryList = galleryDatas;
        })
        .catch(() => {
          this.errored = true;
        });
    },
    /* 작품 정보 */
    getWork(workId) {
      http
        .get("/work/getWork/" + workId)
        .then((response) => {
          var workData = response.data;
          workData.work_piece = "data:image/jpeg;base64," + workData.work_piece;
          this.mainWorkImages.push(workData.work_piece);
          this.mainWorkList.push(response.data);
        })
        .catch(() => {
          this.errored2 = true;
        });
    },
    /* 작가 정보 */
    getArtistInfo(artistId) {
      http
        .get(`/artist/getArtistInfo?artist_id=${artistId}`)
        .then((response) => {
          var artistData = response.data;
          this.artistsInfo.push(artistData);
        });
    },
    hoverPoster(rank){
      var nicknameDiv = document.getElementsByClassName("main_poster_nickname")[rank];
      console.log(rank);
      console.log(nicknameDiv);
      nicknameDiv.style.opacity="1";
    },
    cancelhoverPoster(rank){
      var nicknameDiv = document.getElementsByClassName("main_poster_nickname")[rank];
      console.log(rank);
      console.log(nicknameDiv);
      nicknameDiv.style.opacity="0";
    }
  },
};
</script>

<style scoped>
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);
@import url(//fonts.googleapis.com/css2?family=Do+Hyeon&family=Jua&family=Nanum+Gothic&display=swap);
.Main_container {
  background-color:#f4f5f9;
  width: 100%;
  height: 100%;
  font-family: "Hanna", sans-serif;
}
/*************** 첫 화면 *****************/
.main_top_wrapper {
  width: 100%;
  height: 95vh;
  background-size: cover;
  padding: 1%;
  background-color: #f4f5f9;
}

.main_top_wrapper .banner {
  display: flex;
  justify-content: center;
  width: 50%;
  height: 50%;
  margin:5vh auto;
}
.main_top_wrapper .main_logo{
  display: flex;
  justify-content: center;
  width: 100%;
  height: 100%;
  margin: auto;
  background-image: url('../../assets/images/Main/gallery_logo.png');
  background-size: 100%;
}
.main_top_wrapper .DescriptionText h1 {
  clear: left;
  margin: auto;
  height: 10%;
  width: 80%;
  text-align: center;
  font-size: 1.5em;
  color: #52575d;
  /* color: 		#8f1010; */
  /* font-family: "Jua", sans-serif; */
  font-family: "Hanna", sans-serif;
}
.main_top_wrapper .downArrow {
  position: absolute;
  height: 4rem;
  width: 4rem;
  bottom: 10vh;
  left: 48.5%;
  font-size: 4em;
  color: white;
  animation: toggle;
  animation-duration: 1s;
  animation-iteration-count: infinite;
  cursor: pointer;
  background-image: url("../../assets/images/Main/down1.png");
}

.main_top_wrapper .downArrow:hover {
  color: rgba(0, 0, 255, 0.9);
}
/****************  Main 두번째 화면 ***************/
.main_center_wrapper {
  width: 100%;
  height: 100vh;
  padding-top:5%;
  margin-right: 3%;
  text-align: center;
  position: relative;
  color: black;
  font-family: "Hanna", sans-serif; 
}
.main_center_wrapper .hooper {
  height: 78vh;
  width: 100vw;
}
.main_center_wrapper .main_img {
  position: relative;
  height: 40em;
  background-size: cover;
  
}
.main_center_wrapper .main_poster_nickname{
  width:100%;
  opacity: 0;
  -webkit-text-stroke: 3px #494949;
  -webkit-text-fill-color: #494949;
  font-size:6vw;
  font-family: "Hanna", sans-serif;
  transform:  rotate(14deg);
  z-index: 2;
  position: absolute;
  overflow: hidden;
  margin-top: 20vh;
  
}

.main_gallery__poster:hover .main_poster_nickname{
  opacity: 1;
  transform: scale(2.3);
  transition: all 1.2s;
}

/****** 갤러리 POSTER ******/
.main_center_wrapper .main_gallery__poster {
  position: relative;
  font-size: 3rem;
  margin-top: 5%;
  margin-left: 12%;
  z-index: 1;
  background-image: url("../../assets/images/Main/poster.png");
  background-size: cover;
  background-repeat: no-repeat;
  box-shadow: 0px 16px 25px 2px #353535,
    0px 22px 35px -30px rgba(155, 155, 155, 0.14), 0px 39px 35px 12px #ffffff;
  overflow: hidden;
  border: 2px solid #444;
  border-width: 0.1rem;
  width: 25vw;
  height: 66vh;
  padding: 4rem 6.5rem 2rem 1rem;
  text-align: center;
}

.main_center_wrapper .poster__header {
  width: 100%;
  position: absolute;
  top: 0.9rem;
  text-align: left;
  font-size: 2rem;
  z-index: 100;
  left: 1.5rem;
  font-weight: bold;
}

.main_center_wrapper .poster__writer {
  position: absolute;
  z-index: 100;
  transform: rotate(90deg);
  top: 6.4rem;
  left: 1rem;
  font-size: 0.9rem;
  /*  font-weight: bold*/
}
.main_center_wrapper .poster__date {
  position: absolute;
  z-index: 100;
  transform: rotate(90deg);
  top: 7.5rem;
  left: -3rem;
  font-size: 1.2rem;
  letter-spacing: 1.2px;
}

.main_center_wrapper .main_poster__section {
  width: 100%;
  height: 100%;
  margin-left: 3vw;
  background-position: 50% 50%;
  background-size: 100%;
  background-repeat: no-repeat;
  transition: all 1s;
}

</style>
