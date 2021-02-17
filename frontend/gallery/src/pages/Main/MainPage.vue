<template>
  <div class="Main_container">
    <!-- FADE UP -->
    <div class="main_top_wrapper">
      <div class="banner">
        <div class="main_logo"></div>
      </div>
      <svg id="svg-sprite">
        <symbol id="paw" viewBox="0 0 249 209.32">
          <path
            d="M 154.382812 14.972656 C 143.152344 2.601562 131.777344 0 124.207031 0 C 111.628906 0 99.5 6.84375 90.042969 19.28125 C 81.277344 30.820312 75.738281 46 74.457031 62.03125 C 73.167969 78.128906 75.96875 88.464844 79.847656 102.757812 C 83.308594 115.527344 87.617188 131.417969 91.265625 158.777344 L 92.234375 166.050781 L 165.328125 166.050781 L 166.914062 159.703125 C 167.175781 158.667969 173.273438 134.039062 175.042969 104.183594 C 177.5 62.816406 170.546875 32.800781 154.382812 14.972656 Z M 154.382812 14.972656 "
          />
          <path
            d="M 92.351562 191.015625 C 92.371094 206.261719 95.570312 220.410156 101.355469 230.851562 C 108.074219 242.980469 117.890625 249.664062 128.988281 249.664062 C 150.203125 249.664062 165.035156 225.546875 165.074219 191.015625 L 165.078125 182.625 L 92.347656 182.625 Z M 92.351562 191.015625 "
          />
        </symbol>
      </svg>
      <div class="ajax-loader">
        <div class="paw">
          <svg class="icon"><use xlink:href="#paw" /></svg>
        </div>
        <div class="paw">
          <svg class="icon"><use xlink:href="#paw" /></svg>
        </div>
        <div class="paw">
          <svg class="icon"><use xlink:href="#paw" /></svg>
        </div>
        <div class="paw">
          <svg class="icon"><use xlink:href="#paw" /></svg>
        </div>
        <div class="paw">
          <svg class="icon"><use xlink:href="#paw" /></svg>
        </div>
        <!-- <div class="paw">
          <svg class="icon"><use xlink:href="#paw" /></svg>
        </div> -->
      </div>

      <div class="DescriptionText"></div>
      <div class="downArrow" @click="downMain(); playAudio()">
        <a><span></span><p>Scroll</p></a>
      </div>
    </div>

    <!----------------------------- 메인 화면 --------------------------->
    <div
      class="main_center_wrapper"
      v-if="
        mainGalleryList.length &&
        mainWorkList.length == mainGalleryList.length &&
        mainWorkList.length == artistsInfo.length
      "
    >
      <hooper class="hooper" :settings="hooperSettings">
        <slide v-for="(work, i) in mainWorkList" :key="i" :index="i">
          <div class="main_img">
            <img
              class="main_crown"
              src="../../assets/images/Main/medal_leaf1.png"
              alt="1등"
              v-if="i == 0"
            />
            <img
              class="main_crown"
              src="../../assets/images/Main/medal_leaf2.png"
              alt="2등"
              v-if="i == 1"
            />
            <img
              class="main_crown"
              src="../../assets/images/Main/medal_leaf3.png"
              alt="3등"
              v-if="i == 2"
            />
            <p class="number" v-if="i > 2">{{ i + 1 }}</p>

            <router-link
              :to="{
                name: 'GalleryPage',
                params: { props_id: mainGalleryList[i].gallery_id },
              }"
            >
              <div class="main_gallery__poster">
                <div class="main_poster_nickname">
                  <p>{{ artistsInfo[i].artist_nickName }}</p>
                </div>
                <div class="poster__header">
                  {{ mainGalleryList[i].gallery_name }}
                </div>
                <div class="poster__writer">
                  {{ mainGalleryList[i].gallery_artistId }}
                </div>
                <div class="poster__date">
                  {{ mainGalleryList[i].gallery_writeTime }} ~
                </div>
                <div
                  v-if="mainWorkList.length > 0"
                  class="main_poster__section"
                  :style="{
                    backgroundImage: 'url(' + mainWorkList[i].work_piece + ')',
                  }"
                ></div>
              </div>
            </router-link>
          </div>
        </slide>
        <hooper-navigation slot="hooper-addons"></hooper-navigation>
      </hooper>
    </div>
    <div class="main_footer">
      <div class="main_foot">
        <p>ⓒ 2021. 갤러리옴. All rights reserved.</p>
        <button class="unit__main unit__btn__main move_to_top" type="button" @click="upMain"><img src="../../assets/images/up-arrow.png"
          alt=""></button>
        <!-- <button class="unit__audio unit__btn__audios" id="main_bgm" type="button" @click="muteAudio"><img src="../../assets/images/Main/audio.png" alt=""
              class='all__audios'></button>
              <button class="unit__audio unit__btn__audios" id="main_bgm2" type="button" @click="playAudio"><img src="../../assets/images/Main/audio_mute.png" alt=""
              class='all__audios'></button>
        <audio
          autoplay
          controls
          id="sound"
          loop
          preload="auto"
          src="../../assets/images/Main/audio4_slow.mp3"
        ></audio> -->
      </div>
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
            itemsToShow: 3,
          },
          1600: {
            itemsToShow: 3,
          },
          1500: {
            itemsToShow: 3,
          },
          1400: {
            itemsToShow: 3,
          },
          1100: {
            itemsToShow: 2,
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
  components: {
    Hooper: window.Hooper.Hooper,
    Slide: window.Hooper.Slide,
    HooperNavigation: window.Hooper.Navigation,
  },
  created() {
    this.getAllMainGallery();
  },
  mounted() {
  },
  updated() {},
  methods: {
    downMain() {
      window.scrollTo({
        behavior: "smooth",
        left: 0,
        top: document.querySelector(".main_center_wrapper").offsetTop,
      });
    },
    upMain() {
      window.scrollTo({
        behavior: "smooth",
        left: 0,
        top: 0,
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
  },
};
</script>

<style scoped>
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);
@import url(//fonts.googleapis.com/css2?family=Do+Hyeon&family=Jua&family=Nanum+Gothic&display=swap);
@font-face {
  font-family: "S-CoreDream-8Heavy";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-8Heavy.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
.Main_container {
  background-color:#f5f7fa;
  width: 100%;
  height: 100%;
}
/*************** 첫 화면 *****************/
/* @keyframes showTopText {
  0% { transform: translate3d(0, 100%, 0); }
  40%, 60% { transform: translate3d(0, 50%, 0); }
  100% { transform: translate3d(0, 0, 0); }
} */
.main_top_wrapper {
  width: 100%;
  height: 101.2vh;
  background-size: cover;
  padding: 1%;
   background-color: #f5f7fa;
}

.main_top_wrapper .banner {
  display: flex;
  justify-content: center;
  width: 50%;
  height: 55%;
  margin: 5.5vh auto;
  padding: auto;

  /* overflow: hidden;
  position: absolute;
  border-bottom: 1vmin solid #494949;
  top: 0;
  margin-left: 20%; */
}
.main_top_wrapper .main_logo {
  font-size: 12vw;
  font-weight: bold;
  display: flex;
  justify-content: center;
  width: 100%;
  height: 100%;
  margin: auto;
  /* background-color: #f5f7fa; */
  /* font-family: "Hanna"; */
  background-image: url("../../assets/images/Main/gallery_logo.png");
  background-size: 100%;
  background-repeat: no-repeat;

  /* position:absolute;
  animation: showTopText 1s;
  animation-delay: 0.5s;
  animation-fill-mode: forwards;
  transform: translate(0, 100%); */
}
/*************** scroll ***************/
.main_top_wrapper .downArrow {
  position: absolute;
  height: 4rem;
  width: 4rem;
  bottom: 0vh;
  left: 46.5%;
  font-size: 4em;
  animation: toggle;
  animation-duration: 1s;
  animation-iteration-count: infinite;
  cursor: pointer;
}
.downArrow a {
  padding-top: 60px;
  position: absolute;
  bottom: 20px;
  left: 50%;
  z-index: 2;
  display: inline-block;
  -webkit-transform: translate(0, -50%);
  transform: translate(0, -50%);
  font: normal 400 20px/1 "S-CoreDream-8Heavy", sans-serif;
  letter-spacing: 0.1em;
  text-decoration: none;
  transition: opacity 0.3s;
}
.downArrow a p{
  color: #EFB730;
}
.downArrow a:hover {
  opacity: 0.5;
}
.downArrow a span {
  position: absolute;
  top: 0;
  left: 50%;
  width: 30px;
  height: 50px;
  margin-left: -15px;
  border: 3px solid #EFB730;
  border-radius: 50px;
  box-sizing: border-box;
}

/***** 8번  *****/
/* .downArrow a span::before {
  position: absolute;
  top: 10px;
  left: 50%;
  content: '';
  width: 6px;
  height: 6px;
  margin-left: -3px;
  background-color: #494949;
  border-radius: 100%;
  box-sizing: border-box;
} */

.downArrow a span::before {
  position: absolute;
  top: 10px;
  left: 50%;
  content: "";
  width: 6px;
  height: 6px;
  margin-left: -3px;
  background-color: #EFB730;
  border-radius: 100%;
  -webkit-animation: sdb10 2s infinite;
  animation: sdb10 2s infinite;
  box-sizing: border-box;
}
@-webkit-keyframes sdb10 {
  0% {
    -webkit-transform: translate(0, 0);
    opacity: 0;
  }
  40% {
    opacity: 1;
  }
  80% {
    -webkit-transform: translate(0, 20px);
    opacity: 0;
  }
  100% {
    opacity: 0;
  }
}
@keyframes sdb10 {
  0% {
    transform: translate(0, 0);
    opacity: 0;
  }
  40% {
    opacity: 1;
  }
  80% {
    transform: translate(0, 20px);
    opacity: 0;
  }
  100% {
    opacity: 0;
  }
}

/****************  Main 두번째 화면 ***************/
.main_center_wrapper {
  width: 100%;
  height: 100vh;
  padding-top: 3.5%;
  margin-right: 3%;
  text-align: center;
  position: relative;
  font-family: "S-CoreDream-8Heavy", sans-serif;
  background-image: url("../../assets/images/Main/paper_bg.jpg");
  background-size: 100%;
  background-repeat: no-repeat;
}
.main_center_wrapper .hooper {
  height: 90vh;
  width: 100vw;
}
.main_center_wrapper .main_img {
  position: relative;
  height: 50em;
  background-size: cover;
  overflow: visible;
}
.main_center_wrapper .main_poster_nickname {
  width: 150%;
  height: 50%;
  -webkit-text-stroke: 0.3rem #EFB730;
  -webkit-text-fill-color: transparent;
  /* -webkit-text-fill-color: transparent; */
  font-size: 4.9vw;
  font-family: "S-CoreDream-8Heavy", sans-serif;
  z-index: 2;
  position: absolute;
  float: center;
  padding: auto;
  margin-left: -28%;
  margin-top: 25%;
  margin-bottom: auto;
  word-break: keep-all; /*단어 기준 줄바꿈*/
  opacity: 0;
  transition-duration: 1.5s;
  justify-content: center; /* 안에 내용들 가운데 정렬 */
  align-items: center;
  display: flex;
}

/****** HOVER 효과 ******/
.main_gallery__poster:hover {
  opacity: 1;
  transform: scale(1.05);
  transition: all 1.3s;
  transition-duration: 1.5s;
}
.main_gallery__poster:hover .main_poster_nickname {
  opacity: 1;
  /* 크게 */
  transform: scale(1.2);
  transition: all 1.3s;
  transition-duration: 2s;

  /* 심박 */
  /* animation-name: pulse-shrink;
		animation-duration:  0.8s;
		animation-timing-function: linear;
		animation-iteration-count: infinite;
    animation-direction: alternate; */
  /* 위아래로 흔들 */
  /* animation-name: wobble-vertical;
		animation-duration: 1s;
		animation-timing-function: ease-in-out;
    animation-iteration-count: 1; */
  /* 옆으로 흔들 */
  /* animation-name: wobble-horizontal;
		animation-duration: 1s;
		animation-timing-function: ease-in-out;
    animation-iteration-count: 1; */
  /* 위쪽 흔들 */
  /* animation-name: wobble-top;
		animation-duration: 1.5s;
		animation-timing-function: ease-in-out;
    animation-iteration-count: 1; */
}
/* Wobble Vertical */
@keyframes wobble-vertical {
  16.65% {
    transform: translateY(8px);
  }

  33.3% {
    transform: translateY(-6px);
  }

  49.95% {
    transform: translateY(4px);
  }

  66.6% {
    transform: translateY(-2px);
  }

  83.25% {
    transform: translateY(1px);
  }

  100% {
    transform: translateY(0);
  }
}

/* Wobble Horizontal */
@keyframes wobble-horizontal {
  16.65% {
    transform: translateX(8px);
  }

  33.3% {
    transform: translateX(-6px);
  }

  49.95% {
    transform: translateX(4px);
  }

  66.6% {
    transform: translateX(-2px);
  }

  83.25% {
    transform: translateX(1px);
  }

  100% {
    transform: translateX(0);
  }
}

/* Wobble Top */
@keyframes wobble-top {
  16.65% {
    transform: skew(-12deg);
  }

  33.3% {
    transform: skew(10deg);
  }

  49.95% {
    transform: skew(-6deg);
  }

  66.6% {
    transform: skew(4deg);
  }

  83.25% {
    transform: skew(-2deg);
  }

  100% {
    transform: skew(0);
  }
}
/* Pulse Shrink */
@keyframes pulse-shrink {
  to {
    transform: scale(0.9);
  }
}

/* 번호 */
.main_center_wrapper .number {
  padding-top: 1%;
  margin-bottom: -1%;
  font-size: 2vw;
  margin-left: 13%;
  height: 9vh;
  width: 5vw;
}
/* 왕관 스타일 */
.main_crown {
  margin-bottom: -1%;
  margin-left: -58%;
  height: 9vh;
  width: 5vw;
}

/****** 갤러리 POSTER ******/
.main_center_wrapper .main_gallery__poster {
  position: relative;
  font-size: 3rem;
  margin-top: 3%;
  margin-left: 15%;
  z-index: 1;
  background-image: url("../../assets/images/Main/poster.png");
  background-size: cover;
  background-repeat: no-repeat;
  box-shadow: 0px 16px 25px 2px #353535,
    0px 22px 35px -30px rgba(155, 155, 155, 0.14), 0px 39px 35px 12px #ffffff;
  overflow: visible;
  border: 2px solid #444;
  color: #494949;
  border-width: 0.1rem;
  width: 23vw;
  height: 65vh;
  padding: 2.5rem 7.5rem 2rem 0.5rem;
  text-align: center;
  transition-duration: 1.5s;
  border: 0.3rem solid white;
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
  position: relative;
  margin-left: -11.5vw;
  z-index: 100;
  transform: rotate(90deg);
  top: 6.4rem;
  font-size: 0.9rem;
  /*  font-weight: bold*/
  text-decoration: none;
  color:black;
}
.main_center_wrapper .poster__writer {
  text-decoration: none;
  color:black;
}

.main_center_wrapper .poster__date {
  position: absolute;
  z-index: 100;
  transform: rotate(90deg);
  top: 8.5rem;
  left: -3.5rem;
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
}

/*******  발자국 *******/
.ajax-loader {
  position: absolute;
  top: 41.5%;
  left: 11.5%;
  transform: rotate(62.5deg) translate(0%, 0%);
  font-size: 5vw;
  width: 1em;
  height: 7em;
  color: #494949;
}

.ajax-loader .paw {
  width: 1em;
  height: 1.3em;
  animation: 7050ms pawAnimation ease-in-out infinite;
  opacity: 0;
}

.ajax-loader .paw svg {
  width: 100%;
  height: 100%;
}

.ajax-loader .paw .icon {
  fill: currentColor;
}

.ajax-loader .paw:nth-child(odd) {
  transform: rotate(-10deg);
}

.ajax-loader .paw:nth-child(even) {
  transform: rotate(10deg) translate(125%, 0);
}

.ajax-loader .paw:nth-child(0) {
  animation-delay: 5.4s;
}

.ajax-loader .paw:nth-child(1) {
  animation-delay: 4.5s;
}

.ajax-loader .paw:nth-child(2) {
  animation-delay: 3.6s;
}

.ajax-loader .paw:nth-child(3) {
  animation-delay: 2.7s;
}

.ajax-loader .paw:nth-child(4) {
  animation-delay: 1.8s;
}

.ajax-loader .paw:nth-child(5) {
  animation-delay: 0.9s;
}
.no-cssanimations .ajax-loader .paw {
  opacity: 1;
}

@keyframes pawAnimation {
  0% {
    opacity: 1;
  }
  50% {
    opacity: 0;
  }
  100% {
    opacity: 0;
  }
}

/* Footer */
.main_footer {
  display: block;
  background-color: #f4f5f9;
  margin-top: -4.5vh;
  height: 4vh;
  height: auto;
  text-align: center;
  padding-left: 90vw;
  overflow: visible;
  background-color: cornflowerblue;
  width: 100%;
  font-family: "S-CoreDream-8Heavy", sans-serif;
  color: #494949;
}
.main_footer .main_foot {
  height: auto;
  width: auto;
}
.main_footer .main_foot p {
  width: 20vw;
  height: 2vh;
  opacity: 1;
  margin-top: 0.5vh;
  margin-left: -12vw;
  position: absolute;
}
/* .main_footer .main_foot #main_bgm {
  z-index: 1;
  position: absolute;
}
.main_footer .main_foot #main_bgm2 {
  z-index: 1;
  position: absolute;
  display: none;
}

.main_footer .main_foot audio {
  display: none;
} */

/* 버튼 모양 */
.main_footer .move_to_top{
  z-index: 1;
  position: absolute;
  margin-left: 1.5vw;
  margin-top:-0.3vh;
}
.unit__btn__main {
	border: 0;
	outline: 0;
	font-size: 16px;
	border-radius: 8px;
	padding: 0.35rem;
	background-color: #EBECF0;
	text-shadow: 1px 1px 0 #FFF;
  z-index: 99;
	width: 35px;
	height: 35px;
  font-size: 19.2px;
}

.unit__btn__main {
	color: #61677C;
	font-weight: bold;
	box-shadow: -5px -5px 20px #FFF, 5px 5px 20px #BABECC;
	transition: all 0.2s ease-in-out;
	cursor: pointer;
}
.unit__btn__main:hover {
	box-shadow: -2px -2px 5px #FFF, 2px 2px 5px #BABECC;
}

.unit__btn__main:active {
	box-shadow: inset 1px 1px 2px #BABECC, inset -1px -1px 2px #FFF;
}
.unit__btn__main img {
  width: 100%;
  height: 100%;
}
/* .all__audios{
width: 100%;
height: 100%;
} */
</style>
