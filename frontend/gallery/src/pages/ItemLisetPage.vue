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
      <div class="grid">
        <img src="https://source.unsplash.com/random/4" />
        <div class="grid__body">
          <h1>Image Title</h1>
          <p>By: Someone</p>
        </div>
      </div>
      <div class="grid">
        <img src="https://source.unsplash.com/random/5" />
        <div class="grid__body">
          <h1>Image Title</h1>
          <p>By: Someone</p>
        </div>
      </div>
      <div class="grid">
        <img src="https://source.unsplash.com/random/6" />
        <div class="grid__body">
          <h1>Image Title</h1>
          <p>By: Someone</p>
        </div>
      </div>
      <div class="grid">
        <img src="https://source.unsplash.com/random/7" />
        <div class="grid__body">
          <h1>Image Title</h1>
          <p>By: Someone</p>
        </div>
      </div>
      <div class="grid">
        <img src="https://source.unsplash.com/random/8" />
        <div class="grid__body">
          <h1>Image Title</h1>
          <p>By: Someone</p>
        </div>
      </div>
      <div class="grid">
        <img src="https://source.unsplash.com/random/9" />
        <div class="grid__body">
          <h1>Image Title</h1>
          <p>By: Someone</p>
        </div>
      </div>
      <div class="grid">
        <img src="https://source.unsplash.com/random/10" />
        <div class="grid__body">
          <h1>Image Title</h1>
          <p>By: Someone</p>
        </div>
      </div>
      <div class="grid">
        <img src="https://source.unsplash.com/random/11" />
        <div class="grid__body">
          <h1>Image Title</h1>
          <p>By: Someone</p>
        </div>
      </div>
      <div class="grid">
        <img src="https://source.unsplash.com/random/12" />
        <div class="grid__body">
          <h1>Image Title</h1>
          <p>By: Someone</p>
        </div>
      </div>
      <div class="grid">
        <img src="https://source.unsplash.com/random/13" />
        <div class="grid__body">
          <h1>Image Title</h1>
          <p>By: Someone</p>
        </div>
      </div>
      <div class="grid">
        <img src="https://source.unsplash.com/random/14" />
        <div class="grid__body">
          <h1>Image Title</h1>
          <p>By: Someone</p>
        </div>
      </div>
      <div class="grid">
        <img src="https://source.unsplash.com/random/15" />
        <div class="grid__body">
          <h1>Image Title</h1>
          <p>By: Someone</p>
        </div>
      </div>
      <div class="grid">
        <img src="https://source.unsplash.com/random/16" />
        <div class="grid__body">
          <h1>Image Title</h1>
          <p>By: Someone</p>
        </div>
      </div>
      <div class="grid">
        <img src="https://source.unsplash.com/random/17" />
        <div class="grid__body">
          <h1>Image Title</h1>
          <p>By: Someone</p>
        </div>
      </div>
      <div class="grid">
        <img src="https://source.unsplash.com/random/18" />
        <div class="grid__body">
          <h1>Image Title</h1>
          <p>By: Someone</p>
        </div>
      </div>
      <div class="grid">
        <img src="https://source.unsplash.com/random/19" />
        <div class="grid__body">
          <h1>Image Title</h1>
          <p>By: Someone</p>
        </div>
      </div>
      <div class="grid">
        <img src="https://source.unsplash.com/random/20" />
        <div class="grid__body">
          <h1>Image Title</h1>
          <p>By: Someone</p>
        </div>
      </div>
      <div class="grid">
        <img src="https://source.unsplash.com/random/21" />
        <div class="grid__body">
          <h1>Image Title</h1>
          <p>By: Someone</p>
        </div>
      </div>
      <div class="grid">
        <img src="https://source.unsplash.com/random/22" />
        <div class="grid__body">
          <h1>Image Title</h1>
          <p>By: Someone</p>
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
