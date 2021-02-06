<template>
  <div class="My_profile_container">
    <div class="profile_wrapper">
      <div class="profile_card profile_card1" :style="{backgroundImage:'url('+this.$store.state.state.user_profile+')'}">
        <div class="profile_border">
          <h2 class="profile_card_user_name">Joker</h2>
          <div class="profile_icons">
            <i class="profile_icon icon-instagram-1" aria-hidden="true"></i>
            <i class="profile_icon icon-facebook-1" aria-hidden="true"></i>
            <i class="profile_icon icon-bookmark" aria-hidden="true"></i>
            <span class="profile_icon_span" ><i class="profile_icon icon-chat" aria-hidden="true"></i></span>
          </div>
        </div>
      </div>
      <div class="profile_about">
        <h1 class="profile_user_name">Joker <span class="profile_batch">PRO</span></h1>
        <p class="artist_about">{{this.user_about}}
          <span class="icon-pencil-alt-span" @click="showModal=!showModal"><i class="icon-pencil-alt"></i></span>
          </p>
        <div class="profile_menu_footer">
          <div class="profile_menu">
            <div class="profile_label">More info</div>
            <div class="profile_spacer"></div>
            <div class="profile_item"><span class="profile_menu_item" data-value="1">Twitter</span></div>
            <div class="profile_item"><span class="profile_menu_item" data-value="2">Behance</span></div>
            <div class="profile_item"><span class="profile_menu_item" data-value="3">MixCloud</span></div>
          </div>
        </div>
      </div>
    </div>
    <div class="move_to_top">TOP</div>
    <div class="second__section">
      <div class="gallery_box"></div>
      <div class="gallery_box"></div>
      <div class="gallery_box"></div>
    </div>
    <div class="third__section"></div>
    <div class="forth__section">
      <h1>DM</h1>
      <div id="DM">
        <input type="text" v-model="dm_title" placeholder="DM title">
        <textarea name="DM" v-model="dm_content" placeholder="DM content"></textarea>
      </div>
      <button @click="DM">DM 날리기</button>

      <div class="dmlist">
        <ul>
          <li v-for="(item,index) of dm_list" :key="index">
            {{item}}
          </li>
        </ul>
      </div>
    </div>

        <Modal v-if="showModal&&Aboutmodal" @close="showModal = false">
      <!--
      you can use custom content here to overwrite
      default content
    -->
      <div slot="header">
        <h3>
          사용자 정보 수정
        </h3>
        <i class="fas fa-times closeModalBtn" @click="showModal=false"></i>
      </div>

      <div slot="body" v-if="!modify_state">
        <div class="desc_of_gallery" v-if="!modify_state">
          <label for="gallery_desc"></label>
          <textarea type="textarea" id="gallery_desc" placeholder="짧은 본인 소개글을 작성해 주세요." v-model="user_about"></textarea>
        </div>
      </div>

      <div slot="body" v-else>
        <h3>소개글이 성공적으로 등록되었습니다.</h3>
        <p>{{user_about}}</p>
      </div>

      <div slot="footer" v-if="!modify_state">
        <button class="registerGallery" @click="registerUserAbour">등록하기</button>
        <button class="closeRegisterGalleryBtn" @click="showModal=false">나가기</button>
      </div>
      <div slot="footer" v-else>
        <button class="closeRegisterGalleryBtn" @click="showModal=false">나가기</button>
      </div>
    </Modal>
  </div>
</template>

<script>
  import '../assets/css/MyPage.css'
  import init from '../assets/js/MyPage'
  import Modal from '../pages/Modal'
  import http from '../api/http'

  export default {
    data(){
      return {
        showModal: false,
        modify_state:false,
        img_url:'',
        userInfo:{},
        test_url:'../assets/images/1.png',
        user_about:'',
        DMmodal:true,
        Aboutmodal:!this.DMmodal,
        dm_title:'',
        dm_content:'',
        dm_list:[],
      }
    },
    components:{
      Modal
    },
    methods:{
      registerUserAbour(){
        console.log(this.user_about);
        const formData = new FormData();
        formData.append("user_about",this.user_about);
        formData.append('user_id',localStorage.getItem('user_id'));
        http.post('/user/modifyUserAbout',formData, {headers: {'Content-Type': 'application/json'}})
        .then(response => {
          this.modify_state = true;
          console.log('user_about',response);
        })
      },
      DM(){
        const test ={
  "message_content": this.dm_content,
  "message_id": 0,
  "message_isCheck": 0,
  "message_receiverId": "yong",
  "message_sendDate": "string",
  "message_senderId": "testuser",
  "message_title":this.dm_title,
}
        http.post('/message/sendMessage',test)
        .then(response=>{
          console.log('DM',response.data)
          this.dm_title = "";
          this.dm_content="";
        })
      }
    },
    mounted() {
      setTimeout(() => {
        init.init();
      }, 1000)
    },
    created(){
    console.log(this.$store.state.state);
    http.get('/user/getUserInfo',{params:{user_id:localStorage.getItem('user_id')}})
    .then(response => {
      this.userInfo = response.data;
      this.user_about = this.userInfo.user_about;
      if (response.data.user_profile!== null){
        localStorage.setItem("user_profile","data:/image/jpeg;base64," +this.userInfo.user_profile);

        const byteCharacters = atob(this.userInfo.user_profile);
        const byteNumbers = new Array(byteCharacters.length);
        for (let i = 0; i < byteCharacters.length; i++) {
            byteNumbers[i] = byteCharacters.charCodeAt(i);
        }
        const byteArray = new Uint8Array(byteNumbers);
        const blob = new Blob([byteArray], {type: 'image/jpeg'});
        const file = new File([blob],'profile')
        // console.log(file);
        this.userInfo.user_profile = file;
      }
    })
    .then(() =>{

          http.post('/message/getAllMyReceiveMessage',{user_id:localStorage.getItem('user_id')})
          .then(response=>{
            console.log(response)
            this.dm_list = response.data;
          })
    })
    
  }
  }
</script>

<style>

</style>