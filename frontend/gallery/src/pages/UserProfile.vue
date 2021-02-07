<template>
  <div class="My_profile_container">
    <div class="profile_wrapper">
      <div class="profile_card profile_card1"
        :style="{backgroundImage:'url('+this.img_url+')'}">
        <div class="profile_border">
          <h2 class="profile_card_user_name">{{userInfo.user_id}}</h2>
          <div class="profile_icons">
            <i class="profile_icon icon-instagram-1" aria-hidden="true"></i>
            <i class="profile_icon icon-facebook-1" aria-hidden="true"></i>
            <i class="profile_icon icon-bookmark" aria-hidden="true"></i>
            <span class="profile_icon_span"><i class="profile_icon icon-chat" aria-hidden="true"></i></span>
          </div>
        </div>
      </div>
      <div class="profile_about">
        <h1 class="profile_user_name">{{userInfo.user_id}} <span class="profile_batch">PRO</span>
        </h1>
        <span @click="unfollow" ><i class="icon-user-delete"></i></span>
        <span @click="follow" ><i class="icon-user-add"></i></span>
        <div class="follow">
          <span>Post : {{posts}}</span>
          <span>Following : {{followings.length}}</span>
          <span>Follower : {{followers.length}}</span>
          </div>
        <p class="artist_about">{{this.user_about}}
        </p>
        <span class="icon-pencil-alt-span" @click="[showModal=!showModal,modify_state=false]">
          <i class="icon-pencil-alt"></i></span>
      </div>
        <div class="profile_menu_footer">
          <div class="profile_menu">
            <div class="profile_label">More info</div>
            <div class="profile_spacer"></div>
            <div class="profile_item"><span class="profile_menu_item" data-value="1">Twitter</span></div>
            <div class="profile_item"><span class="profile_menu_item" data-value="2">Behance</span></div>
            <div class="profile_item"><span class="profile_menu_item" data-value="3">MixCloud</span></div>
            <div class="profile_item"><span class="profile_menu_item" @click="moveSettings">Settings</span></div>
          </div>
        </div>
    </div>
    <div class="move_to_top">TOP</div>

    <div class="second__section">

      <div class="galelry-page-content">
        <div class="gallery-card">
          <div class="gallery-content">
            <h2 class="gallery-title">Mountain View</h2>
            <p class="gallery-copy">Check out all of these gorgeous mountain trips with beautiful views of, you guessed
              it, the mountains</p><button class="gallery-btn" @click="galleryRender">View Trips</button>
          </div>
        </div>
        <div class="gallery-card">
          <div class="gallery-content">
            <h2 class="gallery-title">To The Beach</h2>
            <p class="gallery-copy">Plan your next beach trip with these fabulous destinations</p><button
              class="gallery-btn">View Trips</button>
          </div>
        </div>
      </div>

    </div>
    <div class="third__section">
      <div class="third__section__wrapper">
      <div class='gallery_container'>
        <div class='thumb album-thumb' v-for='(item,index) of ["January","February","March","April","May","Jun","July","Agust","September","October","November","December"]' :key="index">
          <div class='thumb-container'>
            <div class='images-container'>
              <img v-for="(work,index) of my_work_lists" :key="index" :src=' "data:image/jpeg;base64,"+work.work_piece' class='thumb-image'>
              <!-- <img class='thumb-image'> -->
              <!-- <img class='thumb-image'> -->
            </div>
            <div class='photo-count'>
              <div class='content'>
                <div class='number'>{{my_work_lists.length}}</div>
                <div class='label'>PHOTOS</div>
              </div>
            </div>
          </div>
          <div class='title'>
            {{item}}
          </div>
        </div>
        </div>
      </div>

    </div>
    <div class="forth__section">
      <h1>DM</h1>
      <div id="DM">
        <input type="text" v-model="dm_title" placeholder="DM title">
        <textarea name="DM" v-model="dm_content" placeholder="DM content"></textarea>
      </div>
      <button @click="DM">DM 날리기</button>
      <button @click="DMpull">DM 받아오기</button>

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
          <label for="user_about"></label>
          <textarea type="textarea" id="user_about" placeholder="짧은 본인 소개글을 작성해 주세요." v-model="user_about"></textarea>
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
    data() {
      return {
        showModal: false,
        modify_state: false,
        img_url: '',
        userInfo: {},
        test_url: '../assets/images/1.png',
        user_about: '',
        DMmodal: true,
        Aboutmodal: !this.DMmodal,
        dm_title: '',
        dm_content: '',
        dm_list: [],
        my_work_lists: [],
        follow_state:false,
        followers:0,
        followings:0,
        posts:0,

      }
    },
    components: {
      Modal
    },
    props:["props_id"],
    methods: {
      registerUserAbour() {
        let userAbout = this.user_about.replace(/(?:\r\n|\r|\n)/g, '<br/>');
        this.user_about = userAbout;
        console.log(userAbout);
        const userAboutTag = document.querySelector('.artist_about');
        userAboutTag.innerHTML = this.user_about;
        const formData = new FormData();
        formData.append("user_about", this.user_about);
        formData.append('user_id', localStorage.getItem('user_id'));
        http.post('/user/modifyUserAbout', formData, {
            headers: {
              'Content-Type': 'application/json'
            }
          })
          .then(response => {
            this.modify_state = true;
            console.log('user_about', response);
          })
      },
      DM() {
        console.log(localStorage.getItem('props_id'));
        const test = {
          "message_content": this.dm_content,
          "message_id": 0,
          "message_isCheck": 0,
          "message_receiverId": localStorage.getItem('props_id'),
          "message_sendDate": "string",
          "message_senderId": localStorage.getItem('user_id'),
          "message_title": this.dm_title,
        }
        console.log('test',test);
        http.post('/message/sendMessage', test)
          .then(response => {
            console.log('DM', response.data)
            this.dm_title = "";
            this.dm_content = "";
            alert('메세지를 보냈습니다.');
          })
      },
      DMpull() {
        http.post('/message/getAllMyReceiveMessage', localStorage.getItem('user_id'))
          .then(response => {
            console.log(response)
            this.dm_list = response.data;
          })
      },
      galleryRender(){
        this.$router.push('/galleryrender');
      },
      moveSettings(){
        this.$router.push('/settings');
      },
      getInfo(){
        const params = {user_id: localStorage.getItem('props_id')}
      http.get('/user/getUserInfo', {params: params})
        .then(response => {
          console.log('data',response.data);
          this.userInfo = response.data;
          const userAbout = document.querySelector('.artist_about');
          userAbout.innerHTML = this.userInfo.user_about;
          if(this.userInfo.user_profile){
            this.img_url = 'data:/image/jpeg;base64,'+this.userInfo.user_profile;
          }
          console.log(this.img_url)
          console.log(response.data.user_about)
          console.log('this',this.userInfo)
          this.user_about = this.userInfo.user_about;
          this.getMyWork();
        })
      },
      getMyWork(){
        const params = {user_id: localStorage.getItem('props_id')}

        http.get('/work/getMyWorks/' + params.user_id)
        .then(response => {
          console.log(response.data);
          this.my_work_lists = response.data;
        })
      },
      getAllMyFollowing(){
        http.get('/follow/getAllMyFollowing',{params:{user_id:localStorage.getItem('props_id')}})
        .then(response =>{
          this.followings = response.data;
        })
      },
      getAllMyFollower(){
        http.get('/follow/getAllMyFollower',{params:{user_id:localStorage.getItem('props_id')}})
        .then(response =>{
          console.log(response.data);
          this.followers = response.data;
        })
      },
      getMyWorksCount(){
        http.get('/work/getMyWorksCount/'+localStorage.getItem('props_id'))
        .then(response=>{
          this.posts = response.data;
        })
      },
      follow(){
        const formData = {
          follow_artistId: localStorage.getItem('props_id'),
          follow_userId:localStorage.getItem('user_id')
        }
        http.post('/follow/follow',formData)
        .then(response=>{
          console.log(response.data);
           this.getAllMyFollowing();
          this.getAllMyFollower();
        })
      },
      unfollow(){
        const formData = {
          follow_artistId: localStorage.getItem('props_id'),
          follow_userId:localStorage.getItem('user_id')
        }
        http.post('/follow/cancelFollow',formData)
        .then(response=>{
          console.log(response.data);
           this.getAllMyFollowing();
          this.getAllMyFollower();
        })
      }
    },
    mounted() {
      setTimeout(() => {
        init.init();
      }, 1000)
    },
     watch: {
    $route(to, form) {
     if (to.path !== form.path) this.getInfo();
    },
     },
    created() {
      this.getAllMyFollowing();
      this.getAllMyFollower();
      this.getMyWorksCount();
      if (this.props_id !== undefined){
        localStorage.setItem('props_id',this.props_id);
      }
      console.log('props_id',this.props_id);
      // console.log(this.$store.state.state);
      this.getInfo();
      this.getMyWork();


      window.scrollTo(0,0);
    }
  }
</script>

<style>

</style>