<template>
  <div>
    <div class="intro">
      <!-- 입장 animation -->
      <div class="enter" @click="clickEnter"></div>
      <div class="door">
        <div class="left"></div>
        <!-- <div class="line"></div> -->
        <div class="right"></div>
      </div>
    </div>

    <div class="subgallery" v-if="imgList.length != 0">
      <p class="galleryType">레드 갤러리</p>
      <div class="page">{{ nowPage }} / {{ imgList.length }}</div>
      <div v-for="(img, i) in imgList" :key="i">
        <div class="image" @mouseover="changePage(i + 1)">
          <router-link class="router-link" to="/test/GalleryJ/DetailPage">
            <img class="img" :src="img.work_piece" :alt="img.work_title" />
            <div class="content">
              <h1 class="text">{{ img.work_title }}</h1>
            </div>
          </router-link>
          <p class="info" style="padding-top: 5%">
            By {{ img.work_artistId }}, {{ img.work_uploadDate }}
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "../../api/http.js";
import gasp from "gsap";

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
      },
      (error) => {
        console.log(error);
      }
    );

    this.nowPage = 1;
  },
  methods: {
    changePage(i) {
      this.nowPage = i;
    },
    clickEnter() {
      this.unlock();
    },
    unlock() {
      const enter = document.querySelector(".enter");
      gasp.to(enter, 0.5, {
        transform: "rotate(90deg)",
        onComplete: this.open(),
      });
    },
    open() {
      const left = document.querySelector(".door .left");
      const right = document.querySelector(".door .right");
      const button = document.querySelector(".enter");
      const intro = document.querySelector(".intro");

      gasp.to(left, 1.5, { width: 0 });
      gasp.to(right, 1.5, { width: 0 });
      gasp.to(button, 1.5, {
        "margin-left": "-60px",
        onComplete: function() {
          left.parentElement.remove();
          button.remove();
          intro.remove();
        },
      });
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

.intro {
  width: 100vw;
  height: 100vh;
  overflow: hidden;
  background-color: #ff1919;
}

.door {
  height: 100%;
  width: 100%;
  position: relative;
  overflow: hidden;

  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 5;
  /* transform: scale(1.3); */
}

.door .left,
.door .right {
  position: relative;
  font-size: 10pt;
  display: inline-block;
  width: calc(50% - 2.5px);
  height: 100%;
  background-image: url("../../assets/images/grey.png");
  z-index: 3;
  /* transform: scale(1.3); */
}

/* .door .line{
  width: 3px;
  height: 100%;
  position: absolute;
  left: calc(50% - 1.25px);
  background-color: #f90000;
  border-left: solid 1px #f90000;
  border-right: solid 1px #f90000;
  box-shadow: 40px 0 150px #ff1919, inset 40px 0 150px #ff1919;
  z-index: 4;
} */

.door .left {
  float: left;
  /* border-right: solid 2.5px #111; */
  /* border-right: 10px solid #f90000; */
  /* box-shadow:inset -100px 0px 250px #ff1919 */
}

.door .right {
  float: right;
  /* border-left: solid 2.5px #111; */
  /* border-left: 10px solid #f90000; */
  /* box-shadow: -5px 0 0 #f90000; */
  /* box-shadow: inset 100px 0px 250px #ff1919 */
}

.enter {
  cursor: pointer;
  display: block;
  background: #222;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  height: 20px;
  width: 20px;
  text-align: center;
  line-height: 30px;
  border-radius: 5px;
  z-index: 6;
  position: absolute;
  padding: 5px 10px;
  margin: auto;
  color: #ccc;
  border: solid 3px black;
}
</style>
