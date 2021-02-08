<template>
  <div class="main_gallery_works_container">
    <p class="main_galleryType">메인 갤러리</p>
    <div class="main_work_page">{{ nowPage }} / {{ imgList.length }}</div>
    <div v-for="(img, i) in imgList" :key="i">
      <div class="main_gallery_works" @mouseover="changePage(i + 1)">
        <img class="img" :src="img.work_piece" :alt="img.work_title" />
        <div class="content">
          <!-- 작품 제목 -->
          <h1 class="text">{{ img.work_title }}</h1>
        </div>
        <p class="info">{{ img.work_title }}, {{ img.work_uploadDate }}</p>
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
.main_gallery_works_container {
  height: 100%;
  text-align: center;
  padding-top: 20%;
}

.main_gallery_works {
  display: block;
  width: auto;
  height: 100vh;
}

.main_gallery_works img {
  height: 80vh;
  width: auto;
  margin: -8.7rem;
  left: -100rem;
}

.main_galleryType {
  position: fixed;
  transform: rotate(-90deg) scale(1.2);
  left: 5vw;
  top: 45vh;
  color: #175711;
  font-family: "Hanna", sans-serif;
}

.main_work_page {
  position: fixed;
  transform: rotate(90deg) scale(1.2);
  right: 5vw;
  top: 45vh;
  color: #175711;
  font-family: "Hanna", sans-serif;
}

.main_gallery_works .content {
  position: relative;
  top: -25%;
  left: 50%;
  transform: translate(-50%, -50%);
  /* color: white; */
  z-index: 2;
  text-align: center;
}

.main_gallery_works .content .text {
  font-size: 7rem;
  font-family: "Hanna", sans-serif;
  color:black;
}

.main_gallery_works .info {
  padding-top: 3%;
  color: black;
  transform: scale(1.2);
  font-family: "Hanna", sans-serif;
}

.main_gallery_works img:hover ~ .content .text,
.main_gallery_works .content:hover .text {
  opacity: 1;
  transform: scale(1.3);
  transition: all 1.2s;
}

.main_gallery_works .img {
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
.main_gallery_works .img:hover {
  -webkit-transform: scale(1.02);
  -moz-transform: scale(1.02);
  -ms-transform: scale(1.02);
  -o-transform: scale(1.02);
  transform: scale(1.02);
}
</style>