<template>
  <div class="subgallery" v-if="imgList.length != 0">
    <!-- 문열림 -->
    <div class="welcome">
      <div class="dark">
        <div class="door-open"></div>
        <div class="door"></div>
      </div>
    </div>
    <!-- 눈동자 -->
    <div class="eyes_container">
      <div id="illustration">
        <svg
          id="eyes-illustration"
          xmlns="http://www.w3.org/2000/svg"
          viewBox="0 0 456.5 167.5"
        >
          <g id="frame">
            <!-- <rect class="cls-1" x="0.25" y="0.25" width="456" height="167" /> -->
            <rect class="cls-2" x="30.25" y="30.25" width="396" height="107" />
            <!-- <rect
              class="cls-3"
              x="30.25"
              y="30.25"
              width="14.56"
              height="107"
            /> -->
            <!-- <rect class="cls-4" x="30.25" y="30.25" width="396" height="107" /> -->
            <!-- <line class="cls-4" x1="44.81" y1="137.25" x2="44.81" y2="30.25" /> -->
          </g>
          <g class="Eyes">
            <defs>
              <clipPath id="eyeLeftClip">
                <path
                  id="left-clip"
                  d="M209.72,83.75s-22.81,27.93-50.95,27.93-50.95-27.93-50.95-27.93,22.81-27.93,50.95-27.93S209.72,83.75,209.72,83.75Z"
                />
              </clipPath>
              <clipPath id="eyeRightClip">
                <path
                  id="right-clip"
                  d="M363.23,83.75s-22.81,27.93-50.95,27.93-50.95-27.93-50.95-27.93,22.81-27.93,50.95-27.93S363.23,83.75,363.23,83.75Z"
                />
              </clipPath>
            </defs>
            <g class="eye-left">
              <path
                class="eye-left-inner"
                d="M209.72,83.75s-22.81,27.93-50.95,27.93-50.95-27.93-50.95-27.93,22.81-27.93,50.95-27.93S209.72,83.75,209.72,83.75Z"
              />
              <g clip-path="url(#eyeLeftClip)">
                <g class="eye-left-pupil">
                  <circle
                    class="eye-left-pupil-color"
                    cx="158.78"
                    cy="83"
                    r="19.75"
                  />
                  <circle
                    class="eye-left-pupil-dark"
                    cx="158.78"
                    cy="83"
                    r="13.37"
                  />
                  <circle
                    class="eye-left-pupil-glare"
                    cx="149.37"
                    cy="71.95"
                    r="8.7"
                  />
                </g>
              </g>
            </g>
            <g class="eye-right">
              <path
                class="eye-right-inner"
                d="M363.23,83.75s-22.81,27.93-50.95,27.93-50.95-27.93-50.95-27.93,22.81-27.93,50.95-27.93S363.23,83.75,363.23,83.75Z"
              />
              <g clip-path="url(#eyeRightClip)">
                <g class="eye-right-pupil">
                  <circle
                    class="eye-right-pupil-color"
                    data-name="eye-left-pupil-color"
                    cx="312.29"
                    cy="83"
                    r="19.75"
                  />
                  <circle
                    class="eye-right-pupil-dark"
                    data-name="eye-left-pupil-dark"
                    cx="312.29"
                    cy="83"
                    r="13.37"
                  />
                  <circle
                    class="eye-right-pupil-glare"
                    data-name="eye-left-pupil-glare"
                    cx="302.88"
                    cy="71.95"
                    r="8.7"
                  />
                </g>
              </g>
            </g>
          </g>
        </svg>
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
            <!-- <transition name="fade"> -->
            <!-- <router-link class="router-link" to="/test/GalleryJ/DetailPage2"> -->
            <img
              class="img"
              :src="img.work_piece"
              :alt="img.work_title"
              :data-value="img.work_id"
            />
            <div class="content">
              <h1 class="text">{{ img.work_title }}</h1>
            </div>
            <!-- </router-link> -->
            <!-- </transition> -->

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
import { gsap, Power1 } from "gsap";
// import VuePageTransition from "vue-page-transition";
Vue.use(VueScrollTo);
// Vue.use(VuePageTransition);

export default {
  data() {
    return {
      imgList: [],
      nowPage: 1,
    };
  },
  components: {},
  created() {
    // http.get(`/gallery/getAdultGallery`).then(
    http.get(`/gallery/getAllSubGallery`).then(
      (response) => {
        var data = response.data;
        for (var i = 0; i < data.length; i++) {
          data[i].work_piece = "data:image/jpeg;base64," + data[i].work_piece;
        }
        this.imgList = data;
      },
      (error) => {
        console.log(error);
      }
    );

    this.nowPage = 1;
  },
  mounted() {
    setTimeout(() => {
      const welcome = document.querySelector(".welcome");

      welcome.remove();
    }, 4500);

    setTimeout(() => {
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

      const squintSpeed = 0.5;
      const easeType = Power1.in;
      const squintEyes = gsap.timeline({ paused: true });
      squintEyes.to(
        ".eye-left-inner",
        {
          duration: squintSpeed,
          ease: easeType,
          scaleY: 0.5,
          transformOrigin: "0% 50%",
        },
        0
      );
      squintEyes.to(
        "#left-clip",
        {
          duration: squintSpeed,
          ease: easeType,
          scaleY: 0.5,
          transformOrigin: "0% 50%",
        },
        0
      );
      squintEyes.to(
        ".eye-right-inner",
        {
          duration: squintSpeed,
          ease: easeType,
          scaleY: 0.5,
          transformOrigin: "0% 50%",
        },
        0
      );
      squintEyes.to(
        "#right-clip",
        {
          duration: squintSpeed,
          ease: easeType,
          scaleY: 0.5,
          transformOrigin: "0% 50%",
        },
        0
      );

      // -----------
      // Event trackers
      // -----------

      const blink = document.querySelector("#eyes-illustration");

      blink.addEventListener("mouseenter", enterButton);
      blink.addEventListener("mouseleave", leaveButton);

      function enterButton() {
        squintEyes.play();
      }
      function leaveButton() {
        squintEyes.reverse();
      }

      // -----------
      // Eye tracking
      // -----------

      const eyeRightPupil = document.querySelector(".eye-right-pupil");
      const eyeLeftPupil = document.querySelector(".eye-left-pupil");
      const eyeLeftInner = document.querySelector(".eye-left-inner");
      const innerEyeWidth = eyeLeftInner.getBoundingClientRect().width;
      const innerEyeHeight = eyeLeftInner.getBoundingClientRect().height;
      const pupilWidth = eyeLeftPupil.getBoundingClientRect().width;
      const pupilHeight = eyeLeftPupil.getBoundingClientRect().height;
      const xMovement = (innerEyeWidth - pupilWidth - 10) / 2;
      const yMovement = (innerEyeHeight - pupilHeight + 5) / 2;

      window.addEventListener("mousemove", updateEyePosition);

      function updateEyePosition(event) {
        const posX =
          ((event.clientX / document.body.clientWidth) * 2 - 1) * xMovement;
        const posY =
          ((event.clientY / document.body.clientHeight) * 2 - 1) * yMovement;

        eyeLeftPupil.style.transform = `translate(${posX}px, ${posY}px)`;
        eyeRightPupil.style.transform = `translate(${posX}px, ${posY}px)`;
      }
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
      console.log("e.target 출력");
      console.log(e.target);
      if (e.target.classList.contains("img")) {
        console.log("e.target.dataset.value 출력");
        console.log(e.target.dataset.value);
        router.push({
          name: "DetailPage2",
          params: { work_id: e.target.dataset.value },
        });
      }
    },
    page_move(i) {
      document.querySelector("#image" + i).scrollIntoView();
    },
  },
};
</script>

<style scoped>
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);

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
  font-family: "Hanna", sans-serif;
  transform: scale(3);
  opacity: 0;
}

.info {
  /* padding-top: 6%; */
  color: black;
  transform: scale(1.2);
  font-family: "Hanna", sans-serif;
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
  font-family: "Hanna", sans-serif;
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
  font-family: "Hanna", sans-serif;
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

svg {
  width: 560px;
}

.cls-1 {
  fill: #fecdce;
}
.cls-1,
.cls-4 {
  stroke: #373737;
}
.cls-1,
.cls-4,
.cls-5 {
  stroke-miterlimit: 10;
  stroke-width: 0.5px;
}
.cls-2 {
  fill: #c20a0a;
}
.cls-3 {
  fill: #fd9596;
}
.cls-4,
.cls-5 {
  fill: none;
}
.cls-5 {
  stroke: #fecdce;
}
.eye-left-inner,
.eye-right-inner,
.eye-left-pupil-glare,
.eye-right-pupil-glare {
  fill: #fff;
}
.eye-left-pupil-color,
.eye-right-pupil-color {
  fill: #38b77e;
}
.eye-left-pupil-dark,
.eye-right-pupil-dark {
  fill: #15422e;
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
