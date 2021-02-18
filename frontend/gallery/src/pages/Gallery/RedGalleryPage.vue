<template>
  <div class="subgallery" v-if="imgList.length != 0" ondragstart="return false">
    <!-- 문열림 -->
    <div class="welcome">
      <div class="dark">
        <div class="door-open"></div>
        <div class="door"></div>
      </div>
    </div>
    <div class="content">
      <div class="button">
        <div class="nav_slide nav--magool">
          <button
            class="nav__item"
            v-for="(img, i) in imgList"
            :key="i"
            :id="'Item' + i"
            :value="'Item' + i"
            @click="page_move(i)"
          ></button>
        </div>
      </div>
      <div class="side">
        <p class="galleryType">레드 갤러리</p>
        <div class="page">{{ nowPage }} / {{ imgList.length }}</div>
      </div>
      <div class="list">
        <div v-for="(img, i) in imgList" :key="i" :id="'image' + i">
          <div class="image slide-in-bck-bottom" @mouseover="changePage(i + 1)">
            <img
              class="img"
              :src="img.work_piece"
              :alt="img.work_title"
              :data-value="img.work_id"
            />
            <div class="content">
              <h1 class="text">{{ img.work_title }}</h1>
            </div>

            <p class="info" style="padding-top: 3%">
              By {{ img.work_artistId }}, {{ img.work_uploadDate }}
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Vue from "vue";
import http from "../../api/http.js";
import router from "../../router";
import VueScrollTo from "vue-scrollto";
Vue.use(VueScrollTo);

export default {
  data() {
    return {
      imgList: [],
      nowPage: 1,
    };
  },
  components: {},
  created() {
    http.get(`/gallery/getAdultGallery`).then(
      // http.get(`/gallery/getAllSubGallery`).then(
      (response) => {
        var data = response.data;
        for (var i = 0; i < data.length; i++) {
          data[i].work_piece = "data:image/jpeg;base64," + data[i].work_piece;
        }
        this.imgList = data;

        if(this.imgList.length == 0){
          alert("전시관 준비가 아직 되지 않았습니다.");
          history.go(-1);
        }
      },
      (error) => {
        console.log(error);
      }
    );

    this.nowPage = 1;
    localStorage.setItem("redState", true);

    let birth = localStorage.getItem('user_birth');
    let today = new Date();

    if(birth == 'null'){
      alert("생년월일 정보를 확인하세요.");
      history.go(-1);
    }else if(today.getFullYear() - Number(birth.slice(0,4)) < 20){
      alert("접근이 불가합니다.");
      history.go(-1);
    }
  },
  mounted() {
    setTimeout(() => {
      const welcome = document.querySelector(".welcome");
      welcome.remove();
    }, 4500);

    setTimeout(() => {
      window.scrollTo(0, 0);

      const list = document.querySelector(".list");
      list.addEventListener("click", this.clickHandler);

      const first = document.querySelector("#Item0");
      first.classList.add("nav__item--current");

      [].slice
        .call(document.querySelectorAll(".nav_slide"))
        .forEach(function(nav) {
          var navItems = [].slice.call(nav.querySelectorAll(".nav__item")),
            setCurrent = function(item) {
              // return if already current
              if (item.classList.contains("nav__item--current")) {
                return false;
              }
              // remove current
              var currentItem = nav.querySelector(".nav__item--current");
              currentItem.classList.remove("nav__item--current");

              // set current
              item.classList.add("nav__item--current");
            };

          navItems.forEach(function(item) {
            item.addEventListener("click", function() {
              setCurrent(item);
            });
          });
        });
    }, 4600);
  },
  methods: {
    changePage(i) {
      this.nowPage = i;
    },
    show() {
      const side = document.querySelector(".content");
      const open = document.querySelector(".open");

      side.classList.remove("hidden");
      // side.classList.add("slide-in-bck-bottom");
      open.classList.add("hidden");
    },
    clickHandler(e) {
      if (e.target.classList.contains("img")) {
        router.push({
          name: "DetailPage2",
          params: { work_id: e.target.dataset.value },
        });
      }
    },
    page_move(i) {
      document.querySelector("#image" + i).scrollIntoView();
    },
    moveToImage() {
      const blank = document.querySelector(".blank_page");
      const image = document.querySelector(".content");

      blank.classList.remove("hidden");
      image.classList.remove("hidden");

      setTimeout(() => {
        const blank = document.querySelector(".blank_page");
        const eye = document.querySelector(".eyes_container");

        blank.classList.add("hidden");
        eye.classList.add("hidden");

        window.scrollTo(0, 0);
      }, 1100);
    },
  },
  destroyed() {
    localStorage.setItem("redState", false);
  },
};
</script>

<style scoped>
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);
@font-face {
  font-family: "S-CoreDream-8Heavy";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-8Heavy.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

.subgallery {
  height: 100vh;
  overflow-x: hidden;
  /* background-color: #c20a0a; */
  background-image: url("../../assets/images/red.png");
}

.subgallery::-webkit-scrollbar {
  display: none;
}

.open {
  height: 100vh;
  width: auto;
}

.hidden {
  display: none;
}
.image {
  position: relative;
  width: auto;
  height: 100vh;
  z-index: 1;
}

.image img {
  height: 80vh;
  max-width: 90vw;
  position: relative;
  top: 10vh;
  border: 50px white solid;
  outline: 25px solid black;

  -webkit-transform: scale(1);
  -moz-transform: scale(1);
  -ms-transform: scale(1);
  -o-transform: scale(1);
  transform: scale(1);
  -webkit-transition: 0.3s;
  -moz-transition: 0.3s;
  -ms-transition: 0.3s;
  -o-transition: 0.3s;
  transition: 0.9s;

  filter: drop-shadow(10px 10px 10px #000);
}

.image .content {
  position: relative;
  top: -35%;
  z-index: 2;
  text-align: center;
}

.image .content .text {
  /* -webkit-text-stroke: 3px rgb(46, 211, 31);
  -webkit-text-fill-color: transparent; */
  color: black;
  /* font-family: "Hanna", sans-serif; */
  font-family: "S-CoreDream-8Heavy";
  transform: scale(3);
  opacity: 0;
}

.info {
  /* padding-top: 6%; */
  color: black;
  transform: scale(1.2);
  /* font-family: "Hanna", sans-serif; */
  font-family: "S-CoreDream-8Heavy";
}

.galleryType {
  position: fixed;
  transform: rotate(-90deg) scale(1.2);
  left: 2vw;
  top: 45vh;
}

.page {
  position: fixed;
  transform: rotate(90deg) scale(1.2);
  right: 2vw;
  top: 45vh;
}

.galleryType,
.page {
  color: #175711;
  /* font-family: "Hanna", sans-serif; */
  font-family: "S-CoreDream-8Heavy";
}

.image img:hover {
  -webkit-transform: scale(1.02);
  -moz-transform: scale(1.02);
  -ms-transform: scale(1.02);
  -o-transform: scale(1.02);
  transform: scale(1.02);
}

.image img:hover ~ .content .text,
.image .content:hover .text {
  opacity: 1;
  transform: scale(3.3);
  transition: all 1.2s;
}

.info {
  padding-top: 3%;
  color: black;
  transform: scale(1.2);
  /* font-family: "Hanna", sans-serif; */
  font-family: "S-CoreDream-8Heavy";
}

.router-link {
  text-decoration: none;
}

/* ==== */
.slide-in-bck-bottom {
  -webkit-animation: slide-in-bck-bottom 0.6s
    cubic-bezier(0.25, 0.46, 0.45, 0.94) both;
  animation: slide-in-bck-bottom 0.6s cubic-bezier(0.25, 0.46, 0.45, 0.94) both;
}

@-webkit-keyframes slide-in-bck-bottom {
  0% {
    -webkit-transform: translateZ(700px) translateY(300px);
    transform: translateZ(700px) translateY(300px);
    opacity: 0;
  }
  100% {
    -webkit-transform: translateZ(0) translateY(0);
    transform: translateZ(0) translateY(0);
    opacity: 1;
  }
}

@keyframes slide-in-bck-bottom {
  0% {
    -webkit-transform: translateZ(700px) translateY(300px);
    transform: translateZ(700px) translateY(300px);
    opacity: 0;
  }
  100% {
    -webkit-transform: translateZ(0) translateY(0);
    transform: translateZ(0) translateY(0);
    opacity: 1;
  }
}

.kenburns-top {
  -webkit-animation: kenburns-top 20s ease-out alternate-reverse infinite both;
  animation: kenburns-top 20s ease-out alternate-reverse infinite both;
}

@-webkit-keyframes kenburns-top {
  0% {
    -webkit-transform: scale(1) translateY(0);
    transform: scale(1) translateY(0);
    -webkit-transform-origin: 50% 16%;
    transform-origin: 50% 16%;
  }
  100% {
    -webkit-transform: scale(1.25) translateY(-15px);
    transform: scale(1.25) translateY(-15px);
    -webkit-transform-origin: top;
    transform-origin: top;
  }
}
@keyframes kenburns-top {
  0% {
    -webkit-transform: scale(1) translateY(0);
    transform: scale(1) translateY(0);
    -webkit-transform-origin: 50% 16%;
    transform-origin: 50% 16%;
  }
  100% {
    -webkit-transform: scale(1.25) translateY(-15px);
    transform: scale(1.25) translateY(-15px);
    -webkit-transform-origin: top;
    transform-origin: top;
  }
}

/* General styles */
.button {
  position: fixed;
  z-index: 2;
  right: 2vw;
  top: 70vh;
}

.nav__item {
  line-height: 1;
  position: relative;
  display: block;
  margin: 0;
  padding: 0;
  letter-spacing: 0;
  color: currentColor;
  border: 0;
  background: none;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}

.nav__item:focus {
  outline: none;
}

/*** Magool ***/

.nav--magool .nav__item {
  width: 1.5em;
  height: 1.25em;
}

.nav--magool .nav__item::after {
  content: "";
  position: absolute;
  top: 45%;
  left: 0;
  width: 100%;
  height: 10%;
  background: black;
  -webkit-transform-origin: 0 0;
  transform-origin: 0 0;
  -webkit-transition: -webkit-transform 0.5s, background-color 0.5s;
  transition: transform 0.5s, background-color 0.5s;
  -webkit-transition-timing-function: cubic-bezier(0.2, 1, 0.3, 1);
  transition-timing-function: cubic-bezier(0.2, 1, 0.3, 1);
}

.nav--magool .nav__item:not(.nav__item--current):focus::after,
.nav--magool .nav__item:not(.nav__item--current):hover::after {
  background: black;
}

.nav--magool .nav__item:not(.nav__item--current):hover::after {
  -webkit-transform: scale3d(2, 1, 1);
  transform: scale3d(2, 1, 1);
}

.nav--magool .nav__item--current::after {
  background: #333;
  -webkit-transform: scale3d(2, 1, 1);
  transform: scale3d(2, 1, 1);
}
</style>

<style lang="sass" scoped>
$door-time: 2.5s
$zoom-time: 3s
$fadeout-time: 2s

.welcome
	position: absolute
	width: 100%
	height: 100vh
	top: 0
	left: 0
	z-index: 2
	opacity: 1
	overflow: hidden
	animation: 0s remove linear 5s
	animation-fill-mode: forwards
	.dark
		position: absolute
		width: 100%
		height: 100%
		z-index: 4
		background: #000
		animation: $fadeout-time fadeout ease $zoom-time
		animation-fill-mode: forwards
		.door-open
			position: absolute
			background: red
			height: 500px
			width: 300px
			top: calc(50% - 250px)
			left: calc(50% - 150px)
			z-index: 5
			box-shadow: 10px 0px 50px red
			animation: $zoom-time growfade ease-in $door-time
			animation-fill-mode: forwards
		.door
			position: absolute
			background: #000
			height: 600px
			width: 300px
			top: calc(50% - 250px)
			left: calc(50% - 150px)
			z-index: 6
			transform-origin: 0 0
			animation: $door-time open-door ease-in-out, 0s fadeout linear $door-time
			animation-fill-mode: forwards

@-webkit-keyframes growfade
	0%
		opacity: 1
		outline: 0px solid red
	33%
		opacity: 1
		outline: 50vw solid red
	66%
		opacity: 1
		outline: 50vw solid red
	100%
		opacity: 0
		outline: 50vw solid red

@-webkit-keyframes open-door
	0%
		transform: rotateX(-20deg) rotateY(0deg)
	100%
		transform: rotateX(-20deg) rotateY(-100deg)

@-webkit-keyframes fadeout
	0%
		opacity: 1
	100%
		opacity: 0

@-webkit-keyframes remove
	0%, 100%
		top: -1000000pxF
</style>
