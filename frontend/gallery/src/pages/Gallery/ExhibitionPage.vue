<template>
  <div class="gallery__poster__wrapper">
    <h1 class="titile__all__gallery">현재 기획 전시전 </h1>
    <div class="gallery_poster-list-container">
      <div class="all__gallery__btn">
        <div class="segment">
          <button class="unit unit__btn" type="button" @click.prevent="refreshAll"><img src="../../assets/images/refresh.png" alt=""
              class='all__gallery__refresh'></button>
          <button class="unit unit__btn" type="button" @click.prevent="footPrintAll"><img src="../../assets/images/footprints.png" alt=""
              class='all__gallery__footprints'></button>
          <button class="unit unit__btn" type="button" @click.prevent="followAll"><img src="../../assets/images/followers.png" alt=""
              class='all__gallery__refresh'></button>
        </div>
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
            <img :src="'data:/image/jpeg;base64,'+img.gallery_mainWork" alt="" class="gallery__poster__main">
            <div class="poster__header">{{img.gallery_name}}</div>
            <div class="poster__aside">
              <div class="poster__aside__artist">{{img.gallery_artistId}}</div> 
              <div class="poster__aside__time">{{img.gallery_writeTime.slice(0,11)}} ~</div>
            </div>
            <div class="poster__section" :style="{backgroundImage:'url('+img.work_piece+')'}"></div>
            <div class="poster__footer">
              © 2021. {{img.gallery_artistId}} Co. all rights reserved.
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import "../../assets/css/testmaingallery.css";
  import http from "../../api/http.js";
  import init from '../../assets/js/testmaingallery.js'
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
        footprinted_gallery: [],

      };
    },
    mounted() {
      setTimeout(() => {
        init.init();
      }, 1000)
    },
    methods: {
      refreshAll(){
        this.getAllGallery();
      },
      footPrintAll(){
        this.render_image = this.footprinted_gallery;
      },
      followAll(){
        this.render_image = this.following_work;
      },
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
            this.footprinted_gallery  = [];
            // array 변환
            for (var i = 0; i < data.length; i++) {
              data[i].work_piece = "data:image/jpeg;base64," + data[i].work_piece;
              if (data[i].gallery_footPrint === 1 ) {
                this.footprinted_gallery.push(data[i]);
              }
            }
            this.imgList = data;
            this.render_image = data;
            this.spinner_state = false;
            console.log(this.render_image);
            console.log('filtering end', this.footprinted_gallery)
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
            console.log('this.following_list', this.following_list);
            this.imgList.forEach(item => {
              console.log(item.gallery_artistId)
              if (this.following_list.includes(item.gallery_artistId)) {
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