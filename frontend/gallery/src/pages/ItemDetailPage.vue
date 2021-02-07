<template>
  <body class="picture_detail" id="why">
  <h1 class="picture_title">{{work_info.work_title}}</h1>
    <p class="picture_date deco_p"><strong><em>ⓒ 2021. {{work_info.work_artistId}} all rights reserved</em></strong></p>
  <h2 class="picture_subtitle">June 11th, 2018 by painting picasso</h2>
  <p class="picture deco_p"><img :src="img_url" alt=""></p>
  <p class="picture_artist deco_p">Dancing Picaso</p>
  
  <blockquote>
    <pre class="pre_1 highlight_badge">PRO(배지이름) Artist</pre>
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
      <i v-if="!like_state" class="fas fa-dollar-sign like-btn-active"></i>
      <i v-else class="fas fa-dollar-sign like-btn-deactive"></i>
      </div>
      <p class="deco_p">{{total_like}}</p>
  </div>
  <p class="deco_p"> <ul><li v-for="(tag,index) of hashTagList" :key="index">#{{tag}}</li></ul></p>
  
    <p class="deco_p" @click="modifyWork">Modify</p>
  <div class="back_btn_container ">
  <div class="backBtns last_p">
    <div class="backBtn" data-display="BACK" @click="Back"></div>
  </div>
</div>
 


  <!-- partial -->

  <!--   mojs script가 여기에 없으면 작동을 안한다!-->

</body>
</template>
<script>
import '../assets/css/Detail.css'
import http from '../api/http'
import init from '../assets/js/Detail'
export default {
  data(){
    return {
      work_info :{},
      img_url :'',
      work_id_store:'',
      like_state:0,
      total_like:0,
      hashTagList:[],
    }
  },
  mounted(){
    init.init();
  },
  props:[
    'work_id'
  ],
  computed(){
    return this.total_like;
  },
  methods:{
    Back(){
      this.$router.go(-1);
    },
    modifyWork(){
      console.log('이동',this.work_info);
      this.$router.push({name:"WorkUpLoad",params:{work_info:this.work_info,mode:'modify'}});
    },
    likeWork(){
    if(this.like_state){
      console.log('like')
      http.get('/work/giveCostToWork',{params:{cost_userId:localStorage.getItem('user_id'),cost_workId:this.work_id_store}})
      .then(response=>{
        console.log(response.data);
      })
      this.like_state = !this.like_state;
      this.getWorkDetail();

    }else{
      console.log('여기다')
       http.get('/work/clearToWorkCost',{params:{cost_userId:localStorage.getItem('user_id'),cost_workId:this.work_id_store}})
      .then(response=>{
        console.log(response.data);
      })
      this.like_state = !this.like_state;
    this.getWorkDetail();

    }
    },
    getWorkDetail(){
      http.get(`/work/getWork/${this.work_id_store}`)
          .then(response=>{
            this.work_info = response.data;
            this.total_like = response.data.work_cost;
            console.log(this.total_like)
            this.work_info.work_piece = "data:image/jpeg;base64," + response.data.work_piece;
            this.img_url = this.work_info.work_piece;
            console.log(this.work_info);
          })
    }
      
  },
  created(){
    
    this.work_id_store = this.work_id;
    console.log('detail',this.work_id_store);
    this.getWorkDetail();

    http.get(`/work/isCheckCost`,{params:{cost_userId:localStorage.getItem('user_id'),cost_workId:this.work_id_store}})
    .then(response=>{
      console.log(response.data);
      this.like_state = response.data;
    })
    http.get(`work/getWorkHashTag/${this.work_id_store}`)
    .then(response=>{
      console.log(response.data)
      this.hashTagList = response.data;
    })
    window.scrollTo(0,0);
  }
}
</script>

<style>

</style>