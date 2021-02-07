<template>
  <div class="item-list-container">
    <div class="search-bar">
      검색바의 위치
    </div>
    <div class="item-lists">
      <div class="grid" v-for="(img, i) in imgList" :key="i">
        <img :src="img.work_piece" alt="DB이미지" />
        <div class="grid__body">
          <h1 v-text="img.work_title"></h1>
          <p>By : {{ img.work_artistId }}</p>
        </div>
      </div>
      
    </div>
  </div>
</template>

<script>
import "../assets/css/ItemListPage.css";
import http from "../api/http.js";

export default {
  data: () => {
    return {
      imgList: [],
    };
  },
  created() {
    return http.get(`/work/getAllWork/` + 1).then(
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
  },
};
</script>

<style></style>
