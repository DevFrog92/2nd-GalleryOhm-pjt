<template>

  <body class="picture_detail" id="why">
    <h1 class="picture_title" @click="moveToBottom">{{work_info.work_title}}</h1>
    <p class="picture_date deco_p"><strong><em>ⓒ 2021. {{work_info.work_artistId}} all rights reserved</em></strong></p>
    <h2 class="picture_subtitle">{{datetime.year}}년 {{datetime.month}}월 {{datetime.day}}일 작가 {{work_info.work_artistId}}
    </h2>
    <p class="picture deco_p"><img :src="img_url" alt="" class="artist__work__detail"></p>
    <p class="picture_artist deco_p"><span @click="moveToArtistPage">{{work_info.work_title}}</span> <span
        class="picture_detail_bookmark">
        <img v-if="!bookmark_state" src="../../assets/images/bookmark.png" @click="BookMark" alt=""
          class='detail__first__bookmark'>
        <img v-else src="../../assets/images/bookmark_active.png" alt="" @click="UnBookMark"
          class='detail__first__bookmark'>
      </span></p>

    <blockquote>
      <div class="pre_2">{{work_info.work_desc}}</div>
    </blockquote>



    <h3 class="subject_explain">사용한 도구</h3>
    <p v-if="work_info.work_tool" class="deco_p explain_size">{{work_info.work_tool}}</p>
    <p v-else class="deco_p explain_size">사용도구는 비공개입니다. 필요하시다면 개인적으로 문의주세요!</p>


    <!--  p태그 쓸지 pre태그 쓸지 확인!!!!!!!!!!-->
    <!-- <pre class="deco_p explain_size">사용 도구 : Ipad Air
사용 어플 : GOOD NOTE
펜 촉 : 
    </pre> -->
    <div class="like-container">
      <div class="like-cnt" @click="likeWork">
        <img v-if="like_state" class="like_image" src="../../assets/images/won (1).png" alt="">
        <!-- <i v-if="like_state" class="fas fa-dollar-sign like-btn-active"></i> -->
        <img v-else class="like_image" src="../../assets/images/won.png" alt="">
        <!-- <i v-else class="fas fa-dollar-sign like-btn-deactive"></i>  -->
        <span class="like_count">{{total_like*100}}</span>
      </div>
      <!-- <p class="total_like">{{total_like}}</p> -->
    </div>
    <p class="deco_p">
      <ul>
        <li v-for="(tag,index) of hashTagList" :key="index">#{{tag}}</li>
      </ul>
    </p>


    <div class="detail_btn_all last_p">
      <div class="dele_pic_btn_container " v-if="user_state">
        <div class="detail_back_button" @click="showModal = true">
          <button class="unit__detail__first unit__btn__detail__first" type="button"
            @click.prevent="change_date_works"><img src="../../assets/images/delete.png" alt=""></button></div>
      </div>
      <div class="mod_pic_btn_container " v-if="user_state">
        <div class="detail_back_button" @click="modifyWork"> <button class="unit__detail__first unit__btn__detail__first" type="button"
            @click.prevent="change_date_works"><img src="../../assets/images/pencil.png" alt=""></button></div>
      </div>
      <div class="back_btn_container last_p">
        <div class="detail_back_button" @click="Back"> <button class="unit__detail__first unit__btn__detail__first" type="button"
            @click.prevent="change_date_works"><img src="../../assets/images/refresh.png" alt=""></button></div>
      </div>
    </div>
    <div class="imgSacleUp">
      <img :src="img_url" alt="" class="artist__work__detail scaleUp">
    </div>


    <Modal v-if="showModal" @close="showModal = false">
      <!--
      you can use custom content here to overwrite
      default content
    -->
      <div slot="header">
        <h3>
          전시관 등록
        </h3>
        <i class="fas fa-times closeModalBtn" @click="showModal=false"></i>
      </div>

      <div slot="body" v-if="!deleteState">
        <p>{{work_info.work_title}}작품을 삭제하시겠습니까?</p>
      </div>
      <div slot="body" v-else>
        <p>{{work_info.work_title}}작품이 삭제되었습니다.</p>
      </div>

      <div slot="footer" v-if="!deleteState">
        <button class="registerGallery" @click="deleteWork">삭제하기</button>
        <button class="closeRegisterGalleryBtn" @click="showModal=false">나가기</button>
      </div>
      <div slot="footer" v-else>
        <button class="closeRegisterGalleryBtn" @click="Works">나가기</button>
      </div>
    </Modal>

    <!-- partial -->

    <!--   mojs script가 여기에 없으면 작동을 안한다!-->

  </body>
</template>
<script>
  import '../../assets/css/Detail.css'
  import http from '../../api/http'
  import init from '../../assets/js/Detail'
  import Modal from '../../pages/Modal'

  export default {
    data() {
      return {
        work_info: {},
        img_url: '',
        work_id_store: '',
        like_state: 0,
        hashTagList: [],
        showModal: false,
        total_like: 0,
        deleteState: false,
        bookmark_state: 0,
        datetime: {
          year: '',
          month: '',
          day: '',
        },
        user_state:false
      }
    },
    components: {
      Modal
    },
    mounted() {
      init.init();
    },
    props: [
      'work_id'
    ],
    methods: {
      moveToArtistPage() {
        if (this.work_info.work_artistId === localStorage.getItem('user_id')) {
          this.$router.push('/mypage');
        } else {
          this.$router.push({
            name: 'UserProfile',
            params: {
              props_id: this.work_info.work_artistId
            }
          });
        }
      },
      Works() {
        this.$router.push('/listitem');
        this.deleteState = false;
      },
      BookMark() {
        const params = {
          scrap_userId: localStorage.getItem('user_id'),
          scrap_workId: this.work_info.work_id
        }
        this.$store.dispatch('BookMark', params);
        this.bookmark_state = 1;
      },
      UnBookMark() {
        const params = {
          scrap_userId: localStorage.getItem('user_id'),
          scrap_workId: this.work_info.work_id
        }
        this.$store.dispatch('UnBookMark', params);
        this.bookmark_state = 0;
      },
      Back() {
        this.$router.go(-1);
      },
      moveToBottom() {
        window.scrollTo({
          top: document.body.scrollHeight,
          left: 0,
          behavior: 'smooth'
        });
      },
      deleteWork() {
        this.$store.dispatch('deleteWork', this.work_info.work_id);
        this.deleteState = true;
      },
      modifyWork() {
        console.log('이동', this.work_info);
        this.$router.push({
          name: "uploadImageResize",
          params: {
            work_info: this.work_info,
            mode: 'modify'
          }
        });
      },
      likeWork() {
        if (this.like_state) {
          console.log('like')
          http.get('/work/clearToWorkCost', {
              params: {
                cost_userId: localStorage.getItem('user_id'),
                cost_workId: localStorage.getItem('work_id')
              }
            })
            .then(response => {
              console.log(response.data);
              this.getWorkDetail();
            })
        } else {
          console.log('여기다')
          http.get('/work/giveCostToWork', {
              params: {
                cost_userId: localStorage.getItem('user_id'),
                cost_workId: localStorage.getItem('work_id')
              }
            })
            .then(response => {
              console.log(response.data);
              this.getWorkDetail();
            })

        }
        this.like_state = !this.like_state;
      },
      getWorkDetail() {
        http.get(`/work/getWork/${localStorage.getItem('work_id')}`)
          .then(response => {
            this.work_info = response.data;
            this.total_like = response.data.work_cost;
            console.log(this.total_like)
            this.work_info.work_piece = "data:image/jpeg;base64," + response.data.work_piece;
            this.img_url = this.work_info.work_piece;
            console.log('getinfo', this.work_info);
            this.datetime.year = this.work_info.work_uploadDate.slice(0, 4)
            this.datetime.month = this.work_info.work_uploadDate.slice(5, 7)
            this.datetime.day = this.work_info.work_uploadDate.slice(8, 10)
            if(this.work_info.work_artistId === localStorage.getItem('user_id')){
              this.user_state = true;
            }else{
              this.user_state = false;
            }
            // const my_desc = document.querySelector('.deco_p.explain_size');
            // console.log('my_desc', my_desc);
            // my_desc.innerHTML = this.work_info.work_desc;
          })
      },
      isCheckCost() {
        http.get(`/work/isCheckCost`, {
            params: {
              cost_userId: localStorage.getItem('user_id'),
              cost_workId: localStorage.getItem('work_id')
            }
          })
          .then(response => {
            console.log('checkCost  ', response.data);
            this.like_state = response.data;
            window.scrollTo(0, 0);

          })
      },
      getWorkHashTag() {
        http.get(`/work/getWorkHashTag/${localStorage.getItem('work_id')}`)
          .then(response => {
            console.log(response.data)
            this.hashTagList = response.data;
          })
      },
      isCheckScrap() {
        http.get('/work/isCheckScrap', {
            params: {
              scrap_userId: localStorage.getItem('user_id'),
              scrap_workId: localStorage.getItem('work_id')
            }
          })
          .then(response => {
            this.bookmark_state = response.data;
            console.log('bookmark', this.bookmark_state)
          })
      }

    },
    watch: {
      $route(to, form) {
        if (to.path !== form.path) {
          console.log('저장되어 있는가?', this.work_id_store);
          //  this.getInfo();
        }
      },
    },
    created() {

      this.work_id_store = this.work_id;
      if (this.work_id !== undefined) {
        localStorage.setItem('work_id', this.work_id);
      }
      console.log('detail', this.work_id_store);
      this.getWorkDetail();
      this.getWorkHashTag();
      this.isCheckCost();
      this.isCheckScrap();
      window.scrollTo(0, 0);
    }
  }
</script>

<style>

</style>