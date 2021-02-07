<template>
  <div class="item-list-container">
    <div class="plus" @click="moveAddWrok">
      PLUS
    </div>
    <div class="search-bar">
      <div class="search__bar">
        <svg xmlns="http://www.w3.org/2000/svg" width="355.5" height="87.99">
          <path class="right" fill="none" stroke="#ff4365" stroke-width="4" stroke-miterlimit="10"
            d="M177.75 85.99h133.5c23.334 0 42.25-18.916 42.25-42.25C352.944 20.528 333.967 2 310.748 2H177.75" />
          <path class="left" fill="none" stroke="#ff4365" stroke-width="4" stroke-miterlimit="10"
            d="M177.75 85.99H44.25C20.916 85.99 2 67.074 2 43.74 2.556 20.528 21.533 2 44.752 2H177.75" />
        </svg>

        <p class="search__p"></p>
        <input type="text" class="search__input" v-model="searchHashtag" @keypress.enter="searchTag" />

        <span>Search</span>
      </div>

    </div>


    <div v-show="searchHashtag">
      <p>{{searchHashtag}}</p> <button @click="resetAll">clear</button>
    </div>
    <div class="item-lists">
      <div class="grid" v-for="(img, i) in imgList" :key="i">
        <img :src="img.work_piece" alt="DB이미지" />
        <div class="grid__body" :data-value="img.work_id">
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
  import init from '../assets/js/Works'
  export default {
    data: () => {
      return {
        imgList: [],
        searchHashtag: '',
      };
    },
    mounted() {
      init.init();
    },
    methods: {
      moveAddWrok() {
        this.$router.push('/workupload');
      },
      resetAll() {
        this.getAllWorks();
        this.searchHashtag = '';
      },
      searchTag() {
        http.get(`/work/searchByHashTag?hashtags=${this.searchHashtag}`).then(
          (response) => {
            const data = response.data;
            console.log(data);
            for (var i = 0; i < data.length; i++) {
              data[i].work_piece = "data:image/jpeg;base64," + data[i].work_piece;
            }

            this.imgList = data;
          },
          (error) => {
            console.log(error);
          })
      },
      getAllWorks(){
 http.get(`/work/getAllWork/` + 1).then(
        (response) => {
          const data = response.data;
          console.log(data);
          for (var i = 0; i < data.length; i++) {
            data[i].work_piece = "data:image/jpeg;base64," + data[i].work_piece;
          }

          this.imgList = data;
        },
        (error) => {
          console.log(error);
        }
      );
      }
    },
    created() {
      return this.getAllWorks();
    },
  };
</script>

<style></style>