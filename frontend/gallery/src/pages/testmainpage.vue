<template>
  <div class="gallery__poster__wrapper">
    <div class="gallery_poster-list-container">
      <div class="gallery_works__follow" @click="change_followes_works">
        follow
      </div>
      <div class="gallery_change_date_works" @click="change_date_works">
        date
      </div>


      <div class="gallery_poster_spinner" v-if="spinner_state">
        <div class="gallery_poster_loader">
          <div class="gallery_poster_box"></div>
          <div class="gallery_poster_box"></div>
        </div>
      </div>


      <div class="gallery_poster-lists">
        <div class="gallery_poster_grid" v-for="(img, i) in render_image" :key="i">
          <div class="gallery__poster" :data-value="img.gallery_id">
            <div class="poster__header">{{img.gallery_name}}</div>
            <div class="poster__aside">
              Created by {{img.gallery_artistId}} {{img.gallery_writeTime}} ~
            </div>
            <div class="poster__section" :style="{backgroundImage:'url('+img.work_piece+')'}"></div>
            <div class="poster__footer">
              © 2021.  {{img.gallery_artistId}} Co. all rights reserved.
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import "../assets/css/testmaingallery.css";
  import http from "../api/http.js";
  import init from '../assets/js/testmaingallery'
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
        no_works: false,

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
        this.no_works = false;
        this.following_list = [];

      },
      change_followes_works() {
        this.render_image = this.following_work;
        this.no_works = false;
        this.following_list = [];

      },
      getAllGallery() {
        this.spinner_state = true;
        http.get('/gallery/getAllGallery').then(
          (response) => {
            const data = response.data;
            // array 변환
            for (var i = 0; i < data.length; i++) {
              data[i].work_piece = "data:image/jpeg;base64," + data[i].work_piece;
            }
            this.imgList = data;
            this.render_image = data;
            this.spinner_state = false;
            console.log(this.render_image);
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
            this.following_list = response.data;
            console.log('this.following_list',this.following_list);
            this.imgList.forEach(item => {
              console.log(item.gallery_artistId)
              if(this.following_list.includes(item.gallery_artistId)){
                this.following_work.push(item);
              }
            })
            console.log(this.following_work)

          })
      },
    },
    created() {
      this.getAllGallery();

    },
  };
</script>

<style></style>