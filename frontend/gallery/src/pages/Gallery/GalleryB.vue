<template>
  <div class="subgallery" v-if="imgList.length != 0">
    <p class="galleryType">서브갤러리</p>
    <div class="page">{{ nowPage }} / {{ imgList.length }}</div>
    <div v-for="(img, i) in imgList" :key="i">
      <div class="image" @mouseover="changePage(i + 1)">
        <img :src="img.work_piece" :alt="img.work_title" />
        <div class="content">
          <!-- 작품명 -->
          <h1 class="text">{{ img.work_title }}</h1>
        </div>
        <div class="bottom">
          <!-- 작가, 올린날짜 -->
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
  destroyed() {},
  methods: {
    changePage(i) {
      this.nowPage = i;
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

.galleryType {
  position: fixed;
  transform: rotate(-90deg);
  left: 5vw;
  top: 45vh;
}

.page {
  position: fixed;
  transform: rotate(90deg);
  right: 5vw;
  top: 45vh;
}

.galleryType,
.page {
  color: #175711;
  font-family: "Hanna", sans-serif;
}

.subgallery::-webkit-scrollbar {
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
}

.image img:hover {
  -webkit-transform: scale(1.02);
  -moz-transform: scale(1.02);
  -ms-transform: scale(1.02);
  -o-transform: scale(1.02);
  transform: scale(1.02);
}

.image .content {
  position: relative;
  top: -35%;
  z-index: 2;
  text-align: center;
}

.image .content .text {
  -webkit-text-stroke: 3px rgb(46, 211, 31);
  -webkit-text-fill-color: transparent;
  font-family: "Hanna", sans-serif;
  transform: scale(3);
  opacity: 0;
}

.image img:hover ~ .content .text,
.image .content:hover .text {
  opacity: 1;
  transform: scale(3.3);
  transition: all 1.2s;
}
</style>
