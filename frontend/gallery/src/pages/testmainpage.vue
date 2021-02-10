<template>
  <div class="gallery_item-list-container">
    <div class="gallery_works__plus" @click="moveAddWrok">
      PLUS
    </div>
    <div class="gallery_works__like" @click="change_like_works">
      Like
    </div>
    <div class="gallery_works__follow" @click="change_followes_works">
      follow
    </div>
    <div class="gallery_change_date_works" @click="change_date_works">
      date
    </div>
    <div class="gallery_search-bar">
      <div class="gallery_search__bar">
        <svg xmlns="http://www.w3.org/2000/svg" width="355.5" height="87.99">
          <path class="right" fill="none" stroke="rgb(44, 49, 57)" stroke-width="5" stroke-miterlimit="10"
            d="M177.75 85.99h133.5c23.334 0 42.25-18.916 42.25-42.25C352.944 20.528 333.967 2 310.748 2H177.75" />
          <path class="left" fill="none" stroke="rgb(44, 49, 57)" stroke-width="5" stroke-miterlimit="10"
            d="M177.75 85.99H44.25C20.916 85.99 2 67.074 2 43.74 2.556 20.528 21.533 2 44.752 2H177.75" />
        </svg>

        <p class="gallery_search__p"></p>
        <input type="text" class="gallery_search__input" v-model="searchHashtag" @keypress.enter="searchTag" />
        <span>Search</span>
      </div>

    </div>

    <div v-show="filter__hashs.length >0" class="gallery_filter__hashs">
      <div>{{filter__hashs}}</div><button @click="resetAll">clear</button>
    </div>

    <div class="gallery_spinner" v-if="spinner_state">
<div class="gallery_loader">
  <div class="gallery_box"></div>
  <div class="gallery_box"></div>
</div>
    </div>


    <div class="gallery_item-lists" >
      <div class="gallery_grid" v-for="(img, i) in render_image" :key="i">
        <div class="gallery__poster">
        <div class="poster__header">Why so serious?</div>
        <div class="poster__aside"> 
        Created by yong 2012-02-10 ~
        </div>
        <div class="poster__section" :style="{backgroundImage:'url('+img.work_piece+')'}"></div>
        <div class="poster__footer">
          © 2021. yong su Co. all rights reserved.
        </div>
      </div>
      </div>
    </div>



  </div>
</template>

<script>
  import "../assets/css/testmaingallery.css";
  import http from "../api/http.js";
  import init from '../assets/js/Works'
  export default {
    data: () => {
      return {
        imgList: [],
        searchHashtag: '',
        filter__hashs: [],
        following_work: [],
        most_like_work: [],
        render_image: [],
        most_like: [],
        following_list: [],
        spinner_state: false,
        no_works:false,

      };
    },
    mounted() {
      setTimeout(() => {
        init.init();
      }, 1000)
    },
    methods: {
      change_date_works() {
        this.render_image = this.imgList;
        this.resetAll();
        this.no_works = false;
        this.following_list=[];

      },
      change_like_works() {
        this.render_image = this.most_like;
        this.resetAll();
        this.no_works = false;
        this.following_list=[];

      },
      change_followes_works() {
        this.render_image = this.following_work;
        this.resetAll();
        this.no_works = false;
        this.following_list=[];

      },
      moveAddWrok() {
        this.$router.push('/workupload');
      },
      resetAll() {
        this.getAllWorks();
        this.filter__hashs = [];
        this.render_image = this.imgList
      },
      searchTag() {
        if(this.searchHashtag !== ""){

        console.log('sdf',this.searchHashtag)
        // this.filter__hashs = [];
        http.get(`/work/searchByHashTag?hashtags=${this.searchHashtag}`).then(
          (response) => {
            const data = response.data;
            console.log(data);
            for (var i = 0; i < data.length; i++) {
              data[i].work_piece = "data:image/jpeg;base64," + data[i].work_piece;
            }

            this.render_image = data;
            this.filter__hashs.push(this.searchHashtag);
            this.searchHashtag = "";
            if(!data.length){
              this.no_works = true;
              this.getAllWorks();
            }
          },
          (error) => {
            console.log(error);
          })
        }

      },
      getAllWorks() {
        console.log('filterling start')
        this.spinner_state = true;
        http.get(`/work/getAllWork/` + 1).then(
          (response) => {
            const data = response.data;
            // array 변환
            for (var i = 0; i < data.length; i++) {
              data[i].work_piece = "data:image/jpeg;base64," + data[i].work_piece;
            }

            data.forEach(item => {
              const tempArr = [];
              for (let work in item) {
                tempArr.push([work, item[work]]);
              }
              this.most_like_work.push(tempArr);
            })
            this.most_like_work.sort(function (a, b) {
              return -a[5][1] + b[5][1]
            });
            this.most_like_work.forEach(items => {
              let most = {};
              for (let item of items) {
                most[item[0]] = item[1];
              }
              this.most_like.push(most);
            })
            this.imgList = data;
            this.render_image = data;
            this.spinner_state = false;

            console.log('filtering end')
            this.getMyFollowings();

          },
          (error) => {
            console.log(error);
          }
        );
      },
      getMyFollowings() {

        this.following_work = [];
        this.following_list = [];
        http.get('/follow/getAllMyFollowing', {
            params: {
              user_id: localStorage.getItem('user_id')
            }
          })
          .then(response => {
            this.following_list = response.data
            this.imgList.forEach(item => {
              const tempArr = [];
              for (let work in item) {
                tempArr.push([work, item[work]]);
              }
              this.most_like_work.push(tempArr);
              if (this.following_list.includes(item.work_artistId)) {
                this.following_work.push(item);
              }
            })
            console.log(this.following_work)

          })
      }
    },
    created() {
      this.getAllWorks();

    },
  };
</script>

<style></style>