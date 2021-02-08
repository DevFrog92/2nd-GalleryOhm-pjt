<template>
  <div class="subgallery">
    <p class="galleryType">서브갤러리</p>
    <div class="page">{{ nowPage }} / {{ imgList.length }}</div>
    <div v-for="(img, i) in imgList" :key="i">
      <div class="image" @mouseover="changePage(i + 1)">
        <div class="content">
          <!-- 필명 -->
          <h1 class="text">{{ img.work_artistId }}</h1>
        </div>
        <router-link active-class="active" to="/test/GalleryJ/DetailPage">
        <img class="img" :src="img.work_piece" :alt="img.work_title" />
        </router-link>
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
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  /* color: white; */
  z-index: 2;
  text-align: center;
}

.text {
  /* -webkit-text-stroke: 3px rgb(46, 211, 31);
  -webkit-text-fill-color: transparent; */
  /* font-size: 4rem; */
  color: black;
    transform: scale(3);
font-family: "Hanna", sans-serif;
}
.info {
    padding-top: 2%;
    color: black;
    transform: scale(1.2);
    font-family: "Hanna", sans-serif;
}
/* .text {
-webkit-text-stroke: 3px rgb(46, 211, 31);
  -webkit-text-fill-color: transparent;
  font-size: 10rem;
  font-family: "Hanna", sans-serif;
} */

.galleryType {
  position: fixed;
  transform: rotate(-90deg);
  left: 2vw;
  top: 50vh;

  /* -webkit-text-stroke: 3px rgb(46, 211, 31);
  -webkit-text-fill-color: transparent; */
  color: #175711;
  font-size: 1rem;
  font-family: "Hanna", sans-serif;
}

.page {
  position: fixed;
  transform: rotate(90deg);
  right: 2vw;
  top: 50vh;

  /* -webkit-text-stroke: 3px rgb(46, 211, 31);
  -webkit-text-fill-color: transparent; */
  color: #175711;
  font-size: 1rem;
  font-family: "Hanna", sans-serif;
}

.img {
    -webkit-transform:scale(1);
    -moz-transform:scale(1);
    -ms-transform:scale(1); 
    -o-transform:scale(1);  
    transform:scale(1);
    -webkit-transition:.3s;
    -moz-transition:.3s;
    -ms-transition:.3s;
    -o-transition:.3s;
    transition:0.9s;
}
.img:hover {
    -webkit-transform:scale(1.02);
    -moz-transform:scale(1.02);
    -ms-transform:scale(1.02);   
    -o-transform:scale(1.02);
    transform:scale(1.02);
}

</style>