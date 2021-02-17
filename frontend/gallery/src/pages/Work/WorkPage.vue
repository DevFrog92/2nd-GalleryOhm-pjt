<template>
  <div class="item-list-container" ondragstart="return false">
    <div class="segment__works">
          <button class="unit__work unit__btn__works" type="button" @click.prevent="change_date_works"><img src="../../assets/images/refresh.png" alt=""
              class='all__works__refresh'></button>
          <button class="unit__work unit__btn__works" type="button" @click.prevent="change_like_works"><img src="../../assets/images/won.png" alt=""
              class='all__works__footprints'></button>
          <button class="unit__work unit__btn__works" type="button" @click.prevent="change_followes_works"><img src="../../assets/images/followers.png" alt=""
              class='all__works__refresh'></button>
          <button class="unit__work unit__btn__works" type="button" @click.prevent="moveToCreateWork"><img src="../../assets/images/add.png" alt=""
              class='all__works__refresh'></button>
        </div>
    <div class="search-bar">
      <div class="search__bar">
        <svg xmlns="http://www.w3.org/2000/svg" width="355.5" height="87.99">
          <path class="right" fill="none" stroke="#EFB730" stroke-width="7" stroke-miterlimit="10"
            d="M177.75 85.99h133.5c23.334 0 42.25-18.916 42.25-42.25C352.944 20.528 333.967 2 310.748 2H177.75" />
          <path class="left" fill="none" stroke="#EFB730" stroke-width="7" stroke-miterlimit="10"
            d="M177.75 85.99H44.25C20.916 85.99 2 67.074 2 43.74 2.556 20.528 21.533 2 44.752 2H177.75" />
        </svg>

        <p class="search__p"></p>
        <input type="text" class="search__input" v-model="searchHashtag" @keypress.enter="searchTag" />
        <span>검색</span>
      </div>

    </div>

    <div v-show="filter__hashs.length >0" class="filter__hashs">
      <span v-for="(hash,index) of filter__hashs" :key="index" class="hash__word">
        {{hash}}
        <span class="delete__hash__word" @click="delete__hash__word(index)">X</span>
      </span>
      <button class="search_clear_btn" @click="resetAll">전체삭제</button>
    </div>
    <div class="no_works" v-if="no_works">
      검색한 태그와 관련한 작품이 없습니다.
    </div>

    <div class="spinner" v-show="spinner_state">
      <div class="loader">
        <div class="box"></div>
        <div class="box"></div>
      </div>
    </div>

    <div class="item-lists" v-show="!spinner_state">
      <div class="grid" v-for="(img, i) in render_image" :key="i">
        <img :src="img.work_piece" alt="DB이미지" />
        <div class="grid__body" :data-value="img.work_id">
          <h1 v-text="img.work_title"></h1>
          <p>작가 : {{ img.work_artistId }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import "../../assets/css/WorkPage.css";
  import http from "../../api/http.js";
  import init from '../../assets/js/Works'
  import prevent from '../../assets/js/common/prevent'
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
        hashTagsObject: {},
        checkhashimages:[],
        resetarray:[]
      };
    },
    mounted() {
      window.addEventListener('load',function(){
        init.init();
      })
      setTimeout(() => {
        prevent.init();
      }, 1000)
      
    },
    methods: {
      moveToCreateWork(){
        this.$router.push('/test/uploadImageResize')
      },
      delete__hash__word(index) {
        this.resetarray = [];
        delete this.hashTagsObject[this.filter__hashs[index]];
        this.filter__hashs.splice(index, 1);
        this.checkhashimages.splice(index, 1);
        // for (let items of Object.values(this.hashTagsObject)) {
        //   for (let item of Object.values(items)) {
        //     if(!this.render_image.includes(item)){
        //       this.resetarray.push(item);
        //     }
        //   }
        // }
        this.render_image = this.checkhashimages
        if(!this.checkhashimages.length){
          this.no_works = true
          this.render_image = this.getAllWorks();
        }else{
          this.no_works = false
        }
      },
      change_date_works() {
        this.render_image = this.imgList;
        this.resetAll();
        this.no_works = false;
        this.following_list = [];

      },
      change_like_works() {
        this.render_image = this.most_like;
        this.resetAll();
        this.no_works = false;
        this.following_list = [];

      },
      change_followes_works() {
        this.render_image = this.following_work;
        this.resetAll();
        this.no_works = false;
        this.following_list = [];

      },
      moveAddWrok() {
        this.$router.push('/workupload');
      },
      resetAll() {
        this.filter__hashs = [];
        this.checkhashimages = [];
        this.hashTagsObject = {}
        // this.getAllWorks();
      },
      searchTag() {
        if (this.searchHashtag.trim() !== "" && !Object.keys(this.hashTagsObject).includes(this.searchHashtag.trim())) {
          http.get(`/work/searchByHashTag?hashtags=${this.searchHashtag.trim()}`).then(
            (response) => {
              const data = response.data;
              for (var i = 0; i < data.length; i++) {
                data[i].work_piece = "data:image/jpeg;base64," + data[i].work_piece;
              }
              this.hashTagsObject[this.searchHashtag.trim()] = data;
              console.log('ths.hash',this.hashTagsObject)
              this.filter__hashs.push(this.searchHashtag);
              this.searchHashtag = "";
              if (data.length === 0) {
                this.no_works = true;
                // this.getAllWorks();
              } else {
                this.render_image = [];
                for (let items of Object.values(this.hashTagsObject)) {
                  console.log('1차 for',items)
                  for (let item of Object.values(items)) {
                  console.log('2차 for',item)
                  console.log('checkimage',this.checkhashimages)
                    if(!this.checkhashimages.includes(item)){
                      console.log('들어갈거야')
                      this.checkhashimages.push(item);
                      
                    }
                  }
                }
                this.render_image = this.checkhashimages
                this.no_works = false;
              }
            },
            )
        } else {
          this.searchHashtag = "";
        }

      },
      getAllWorks() {
        this.spinner_state = true;
        http.get(`/work/getAllWork/` + 1).then(
          (response) => {
            const data = response.data;
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
            
            this.getMyFollowings();
            init.init();
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
              console.log(this.following_work);
            })
          })
      }
    },
    created() {
      this.getAllWorks();

    },
  };
</script>

<style></style>