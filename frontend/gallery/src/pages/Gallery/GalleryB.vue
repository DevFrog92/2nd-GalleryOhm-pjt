<template>
  <div class="subgallery" v-if="imgList.length != 0">
    <div class="eyes">
      <button @click="show" v-scroll-to="'.content'">버튼</button>

      <section id="dude">
        <img src="https://i.imgur.com/rOFqbbs.png" alt="" id="gentleman" />
        <div id="eyes"></div>
        <canvas class="draw-map" style="width: 500px; height: 684px"></canvas>
      </section>

      <section id="copy">
        <h3 class="title"></h3>
        <a href="" id="reset"></a>
      </section>
    </div>
    <div class="content hidden">
      <div class="side">
        <p class="galleryType">서브갤러리</p>
        <div class="page">{{ nowPage }} / {{ imgList.length }}</div>
      </div>
      <div class="list">
        <div v-for="(img, i) in imgList" :key="i">
          <div class="image slide-in-bck-bottom" @mouseover="changePage(i + 1)">
            <router-link
              class="router-link"
              active-class="active"
              to="/test/GalleryJ/DetailPage2"
            >
              <img class="img" :src="img.work_piece" :alt="img.work_title" />
              <div class="content">
                <!-- 작품명 -->
                <h1 class="text">{{ img.work_title }}</h1>
              </div>
              <p class="info">
                {{ img.work_artistId }}, {{ img.work_uploadDate }}
              </p>
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Vue from "vue";
import http from "../../api/http.js";
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
  mounted() {},
  destroyed() {},
  methods: {
    changePage(i) {
      this.nowPage = i;
    },
    show() {
      const side = document.querySelector(".content");
      const eyes = document.querySelector(".eyes");

      side.classList.remove("hidden");
      // side.classList.add("slide-in-bck-bottom");
      eyes.classList.add("hidden");
    },
  },
};
</script>

<style scoped>
.subgallery {
  /* height: 100vh; */
  overflow-x: hidden;
  background-color: #fcf1e7;
}

.subgallery::-webkit-scrollbar {
  display: none;
}

.eyes {
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
  color: black;
  font-family: "Hanna", sans-serif;
  transform: scale(3);
  opacity: 0;
}

.info {
  padding-top: 4%;
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

.router-link {
  text-decoration: none;
}

.slide-in-bck-bottom {
  -webkit-animation: slide-in-bck-bottom 0.3s
    cubic-bezier(0.25, 0.46, 0.45, 0.94) both;
  animation: slide-in-bck-bottom 0.3s cubic-bezier(0.25, 0.46, 0.45, 0.94) both;
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
</style>

<style lang="scss" scoped>
// VARIABLES
$red: #fe0000;

// MIXINS / EXTENDS
%no-select {
  -webkit-touch-callout: none;
  -webkit-user-select: none;
  -khtml-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

// IMPORTS
@import url(https://fonts.googleapis.com/css?family=Pacifico);

*,
*:before,
*:after {
  box-sizing: inherit;
}

// STYLE
.subgallery {
  > section {
    margin: 0 auto;
    width: 500px;
    position: relative;
  }
}

.title {
  text-align: center;
  font-family: "Pacifico";
  font-size: 2rem;
}

section#dude {
  @extend %no-select;
  #gentleman {
    position: relative;
    z-index: 1;
    @extend %no-select;
  }
  #draw-map {
    @extend %no-select;
    position: absolute;
    top: 0;
    left: 0;
    z-index: 2;
  }
}

#reset {
  display: block;
  background: #333;
  margin-top: 1rem;
  padding: 1rem initial;
  border-radius: 5px;
  font-family: sans-serif;
  color: #fff;
  text-align: center;
  font-size: 0.8rem;
  text-transform: uppercase;
  letter-spacing: 3px;
  text-decoration: none;
  &:hover {
    background: darken(#333, 1%);
  }
  &:active {
    background: darken(#333, 5%);
  }
}

.thickness-options {
  list-style: none;
  padding-left: 0;
  li {
    display: inline-block;
    width: percentage(1/4);
    text-align: center;
    &.list-title {
      font-weight: 600;
    }
    a {
      display: block;
      color: #aaa;
      // border: 2px solid #eee;
      text-decoration: none;
      &.active {
        color: #000;
        // border-color: #000;
      }
    }
  }
}
</style>
