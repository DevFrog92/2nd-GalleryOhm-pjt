<template>
  <body class="picture_detail" id="why">
  <h1 class="picture_title" @click="moveToBottom">{{work_info.work_title}}</h1>
    <p class="picture_date deco_p"><strong><em>ⓒ 2021. {{work_info.work_artistId}} all rights reserved</em></strong></p>
  <h2 class="picture_subtitle">June 11th, 2018 by painting picasso</h2>
  <p class="picture deco_p"><img :src="img_url" alt=""></p>
  <p class="picture_artist deco_p">Dancing Picaso</p>
  
  <blockquote>
    <pre class="pre_1 highlight_badge">PRO(배지이름) <span @click="moveToArtistPage"> Artist</span></pre>
    <pre class="pre_2">저는 아이패드를 통해 동양화를 그리는 취미가 있습니다. 저에 대한 더 많은 정보를 얻고 싶으신 분은 제 마이페이지를 방문해주세요. </pre>
  </blockquote>

  <h3 class="subject_explain">About Picture</h3>
  <p class="deco_p explain_size">{{work_info.work_desc}}</p>

  <h3 class="subject_explain">Using Tool</h3>

  <!--  p태그 쓸지 pre태그 쓸지 확인!!!!!!!!!!-->
  <!-- <pre class="deco_p explain_size">사용 도구 : Ipad Air
사용 어플 : GOOD NOTE
펜 촉 : 
    </pre> -->
    <p class="deco_p explain_size">{{work_info.work_tool}}</p>
  <div class="like-container">
    <div class="like-cnt unchecked" id="like-cnt" @click="likeWork">
      <i v-if="like_state" class="fas fa-dollar-sign like-btn-active"></i>
      <i v-else class="fas fa-dollar-sign like-btn-deactive"></i>
      </div>
      <p class="deco_p">{{total_like}}</p>
  </div>
  <p class="deco_p"> <ul><li v-for="(tag,index) of hashTagList" :key="index">#{{tag}}</li></ul></p>
  
    <p class="deco_p" v-if="bookmark_state" @click="UnBookMark">Un-BookMark</p>
    <p class="deco_p" v-else @click="BookMark">BookMark</p>
    <p class="deco_p" @click="modifyWork">Modify</p>
    <p class="deco_p" @click="showModal = true">Delete</p>
  <div class="back_btn_container ">
  <div class="backBtns last_p">
    <div class="backBtn" data-display="BACK" @click="Back"></div>
  </div>
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

      <div slot="footer" v-if="!deleteState" >
        <button class="registerGallery" @click="deleteWork">삭제하기</button>
        <button class="closeRegisterGalleryBtn" @click="showModal=false">나가기</button>
      </div>
      <div slot="footer" v-else >
        <button class="closeRegisterGalleryBtn" @click="Works">나가기</button>
      </div>
    </Modal>

  <!-- partial -->

  <!--   mojs script가 여기에 없으면 작동을 안한다!-->

</body>
</template>
<script>
import '../assets/css/Detail.css'
import http from '../api/http'
import init from '../assets/js/Detail'
  import Modal from '../pages/Modal'

export default {
  data(){
    return {
      work_info :{},
      img_url :'',
      work_id_store:'',
      like_state:0,
      hashTagList:[],
      showModal: false,
      total_like:0,
      deleteState: false,
      bookmark_state:0,
    }
  },
  components:{
    Modal
  },
  mounted(){
    init.init();
  },
  props:[
    'work_id'
  ],
  methods:{
    moveToArtistPage(){
      if(this.work_info.work_artistId === localStorage.getItem('user_id')){
          this.$router.push('/mypage');
      }else{
        this.$router.push({name:'UserProfile',params:{props_id:this.work_info.work_artistId}});
      }
    },
    Works(){
      this.$router.push('/listitem');
      this.deleteState = false;
    },
    BookMark(){
      const params = {scrap_userId:localStorage.getItem('user_id'),scrap_workId:this.work_info.work_id}
      this.$store.dispatch('BookMark',params);
      this.bookmark_state = 1;
    },
    UnBookMark(){
      const params = {scrap_userId:localStorage.getItem('user_id'),scrap_workId:this.work_info.work_id}
      this.$store.dispatch('UnBookMark',params);
      this.bookmark_state = 0;
    },
    Back(){
      this.$router.go(-1);
    },
    moveToBottom(){
      window.scrollTo({
  top: document.body.scrollHeight,
  left: 0,
  behavior: 'smooth'
});
    },
    deleteWork(){
      this.$store.dispatch('deleteWork',this.work_info.work_id);
      this.deleteState = true;
    },
    modifyWork(){
      console.log('이동',this.work_info);
      this.$router.push({name:"WorkUpLoad",params:{work_info:this.work_info,mode:'modify'}});
    },
    likeWork(){
    if(this.like_state){
      console.log('like')
      http.get('/work/clearToWorkCost',{params:{cost_userId:localStorage.getItem('user_id'),cost_workId:localStorage.getItem('work_id')}})
      .then(response=>{
        console.log(response.data);
        this.getWorkDetail();
      })
    }else{
      console.log('여기다')
       http.get('/work/giveCostToWork',{params:{cost_userId:localStorage.getItem('user_id'),cost_workId:localStorage.getItem('work_id')}})
      .then(response=>{
        console.log(response.data);
      this.getWorkDetail();
      })

    }
    this.like_state = !this.like_state;
    },
    getWorkDetail(){
      http.get(`/work/getWork/${localStorage.getItem('work_id')}`)
          .then(response=>{
            this.work_info = response.data;
            this.total_like = response.data.work_cost;
            console.log(this.total_like)
            this.work_info.work_piece = "data:image/jpeg;base64," + response.data.work_piece;
            this.img_url = this.work_info.work_piece;
            console.log('getinfo',this.work_info);
          })
    },
    isCheckCost(){
      http.get(`/work/isCheckCost`,{params:{cost_userId:localStorage.getItem('user_id'),cost_workId:this.work_id_store}})
    .then(response=>{
      console.log('checkCost  ',response.data);
      this.like_state = response.data;
      window.scrollTo(0,0);

    })
    },
    getWorkHashTag(){
      http.get(`/work/getWorkHashTag/${localStorage.getItem('work_id')}`)
    .then(response=>{
      console.log(response.data)
      this.hashTagList = response.data;
    })
    },
    isCheckScrap(){
      http.get('/work/isCheckScrap',{params:{scrap_userId:localStorage.getItem('user_id'),scrap_workId:this.work_id_store}})
    .then(response =>{
      this.bookmark_state = response.data;
      console.log('bookmark',this.bookmark_state)
    })
    }
      
  },
  watch: {
    $route(to, form) {
     if (to.path !== form.path){
       console.log('저장되어 있는가?',this.work_id_store);
      //  this.getInfo();
     }
    },
  },
  created(){
    
    this.work_id_store = this.work_id;
    if(this.work_id !== undefined){
      localStorage.setItem('work_id',this.work_id);
    }
    console.log('detail',this.work_id_store);
    this.getWorkDetail();
    this.getWorkHashTag();
    this.isCheckCost();
    this.isCheckScrap();
    window.scrollTo(0,0);
  }
}
</script>

<style>

</style>