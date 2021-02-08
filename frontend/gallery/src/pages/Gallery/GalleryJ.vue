<template>
  <div class="subgallery">
      <p class="galleryType">서브갤러리</p>
    <div class="page">{{ nowPage }} / {{ imgList.length }}</div>
    <div v-for="(img, i) in imgList" :key="i">
      <div class="image" @mouseover="changePage(i+1)">
        <div class="content">
          <!-- 필명 -->
          <h1 class="text">{{ img.work_artistId }}</h1>
        </div>
        <img :src="img.work_piece" :alt="img.work_title" />
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
}

.image {
  display: block;
  width: auto;
  height: 100vh;
  z-index: 1;
/* 
  position: relative;
  overflow: hidden; */
}

.image img {
  height: 80vh;
  width: auto;
}

.image .content {
  position: relative;
  top: 54%;
  left: 50%;
  transform: translate(-50%, -50%);
  /* color: white; */
  z-index: 2;
  text-align: center;
}

.image .content .text {
-webkit-text-stroke: 3px rgb(46, 211, 31);
  -webkit-text-fill-color: transparent;
  font-size: 10rem;
  font-family: "Hanna", sans-serif;
} 

 img:hover .text {
-webkit-text-stroke: 3px rgb(46, 211, 31);
  -webkit-text-fill-color: transparent;
  font-size: 10rem;
  font-family: "Hanna", sans-serif;
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
</style>