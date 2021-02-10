<template>
<div>
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
  },
};
</script>

<style scoped>
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);

.subgallery {
  height: 100vh;
  overflow-x: hidden;
  background-color: #c20a0a;
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
</style>