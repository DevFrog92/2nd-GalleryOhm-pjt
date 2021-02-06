<template>

  <div id="modwrapper" class="show_me_mod">
    <div id="left-side">
      <ul>
        <li class="modmain active">
          <div class="modicon active">
            <i class="fas fa-heart icon"></i>
          </div>
          test GALLERY
        </li>
        <div v-show="checkPassword_state">
        <li class="moduser">
          <div class="modicon">

            <i class="fas fa-heart modicon"></i>
          </div>
          Modify Info
        </li>
        <!-- <li class="modpage">
          <div class="modicon">
            <i class="fas fa-heart modicon"></i>
          </div>
          Register Artist
        </li> -->
        <li class="unscribe">
          <div class="modicon">
            <i class="fas fa-heart modicon"></i>
          </div>
          Unsubscribe
        </li>
        </div>
      </ul>
    </div>
    <div id="modborder">
      <div id="line" class="one"></div>
    </div>

    <div id="right-side">

      <div id="first" class="active">

        <h2>Thank you for subscirbe</h2><br>
        <h1>test website</h1>
        <h2>You can modify infomation in this page</h2>
        <div v-if="!checkPassword_state">
          <label for="id_for_mod">아이디</label>
          <input type="text" class="id_for_mod" v-model="userInfo.user_id">
          <label for="password_for_mod">비빌번호</label>
          <input type="password" class="password_for_mod" v-model="password_for_mod">
          <button @click="checkPassword">check pw</button>
        </div>
      </div>


      <div id="second">
        <!--        <div class="modUserTitle">Modify User Info</div>-->
        <div class="modUserContainer1">
          <div class="file_input">
            <div class="img_preview">
              <img :src="img_url" alt="">
            </div>
              <input type="file" ref="work_piece" hidden @change="onChangeImage"/><br/>
              <button @click.prevent="onClickUploadImage">내사진 올려</button><br>
          </div>

        </div>
        <div class="modUserContainer2">
          <div class="UserContainer2">
            <input type="text" id="username" required=""  v-model="userInfo.user_id" />
            <label for="username">ID</label>
          </div>
          <div class="UserContainer2 mod_nickname">
            <!-- TODO:CSS layout 다시 잡기 -->
            <input type="text" class="nickname_self" readonly=true v-model="self_nickName" />
            <input type="text" id="nickname" readonly=true  v-model="this.state.user_nickName"/>
            <button @click.prevent="makeUserNickName"><i class="fas fa-redo"></i></button>
            <button @click.prevent="modifyUserNickName">직접수정</button>
            <label for="nickname">NICKNAME</label>
            <p id="mod_explain">* 닉네임은 랜덤값만 가능합니다.</p>
          </div>
          <div class="UserContainer2">
            <input type="password" id="password1" required="" />
            <label for="password1">PASSWORD</label>
          </div>
          <div class="UserContainer2">
            <input type="password" id="password2" required="" />
            <label for="password2">PASSWORD CHECK</label>
          </div>
          <div class="UserContainer2">
            <input type="email" id="email" required="" v-model="userInfo.user_email" />
            <label for="email">EMAIL</label>
          </div>
          <div class="UserContainer2">
            <input type="text" id="name" required="" v-model="userInfo.user_name"/>
            <label for="name">NAME</label>
          </div>
          <div class="UserContainer2">
            <input type="tel" id="tel" required="" v-model="userInfo.user_tel"/>
            <label for="tel">TELEPHONE</label>
          </div>
          <div class="UserContainer2">
            <input type="date" id="birth" v-model="userInfo.user_birth"/>
            <label for="birth">BIRTH</label>
            <p id="mod_explain">* 19금관 이용을 원하신다면 반드시 입력해주세요.</p>
          </div>
        </div>
        <button class="modUserBtn" @click="modifyUser">SAVE</button>
      </div>


      <div id="third">
        <button>작가등록하시겠습니까?</button>
        <p class="third_title">Ω GALLERY 작가등록을 환영합니다.</p>
        <div class="register_artist_container">
          <div class="register_artist">
            <input type="text" id="artistname" required="" />
            <label for="artistname">필명</label>
          </div>
          <div class="register_artist  bankname">
            <input type="text" id="bank" required="" />
            <label for="bank">은행명</label>
          </div>
          <div class="register_artist bankaccount ">
            <input type="text" id="account" required="" />
            <label for="account">계좌번호</label>
            <p class="registerinfo">* 후원을 위한 계좌번호를 입력해주세요.</p>
          </div>

          <div class="register_artist">
            <input type="text" id="rank" required="" />
            <label for="rank">rank</label>
            <p class="registerinfo">* 랭크는 작가등록 후 gallery의 기준에 따라 자동으로 부여됩니다.
              기준은 갤러리 소개에서 보실 수 있습니다.</p>
          </div>
          <button>작가등록</button>
        </div>
      </div>


      <div id="fourth">
        <h2>Why you want to unsubscribe our page?</h2>
        <div class="unscribe_reason">
          <div>
            <input type="radio" id="reason1">
            <label for="reason1">가려고?</label>
          </div>
          <div>
            <input type="radio" id="reason2">
            <label for="reason2">에휴...</label>
          </div>
          <div>
            <input type="radio" id="reason3">
            <label for="reason3">어쩔 수 없지....</label>
          </div>
        </div>
        <button @click='leaveUser'>unsubscribe</button>
      </div>
    </div>
  </div>
</template>

<script>
import '../assets/css/Settings.css'
import mod from '../assets/js/settings.js'
import http from '../api/http'
import { mapState } from 'vuex'
export default {
  data(){
    return {
      name:'다비드',
      userInfo:{},
      user_password_confirm:'',
      password_for_mod:'',
      img_url:'',
      checkPassword_state : localStorage.getItem('checkPassword_state'),
      self_nickName:'',
      self_mode:false,
    }
  },
  computed:{
    ...mapState(['state']),
  },
  mounted(){
    mod.design();
      // this.img_url = '../assets/images/Anonymous.png'
    // if(this.userInfo.user_profile === null){
    // }
  },
  methods:{
    modifyUser(){
      if (this.self_nickName){
        this.userInfo.user_nickName = this.self_nickName;

      }else{
        this.userInfo.user_nickName = this.state.user_nickName;
        }
      mod.modifyUser(this.userInfo,this.password_for_mod);
    },
    leaveUser(){
      mod.leaveUser(this.userInfo.user_id);
    },
    checkPassword(){
      mod.checkPassword(this.userInfo,this.password_for_mod);
      this.password_for_mod = '';
    },
    makeUserNickName(){
      mod.makeUserNickName(this.userInfo.user_id);
    },
    modifyUserNickName(){
      const readNickName= document.querySelector('#nickname');
      const self_NickName= document.querySelector('.nickname_self');
      readNickName.classList.add("nickname_no_show");
      self_NickName.classList.add('nickname_self_show');
      self_NickName.readOnly = false;
      // this.self_mode != this.self_mode;
    },
    onChangeImage(event){
      const imgFile = event.target.files[0];
      // console.log(imgFile,'onChangeImage');
      this.userInfo.user_profile = imgFile;
      this.img_url = URL.createObjectURL(imgFile);
    },
    onClickUploadImage(){
      this.$refs.work_piece.click();
    }

  },
  created(){
    http.get('/user/getUserInfo',{params:{user_id:localStorage.getItem('user_id')}})
    .then(response => {
      this.userInfo = response.data;
      if (response.data.user_profile!== null){
        this.img_url = "data:/image/jpeg;base64," +this.userInfo.user_profile
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
      }else{
        this.userInfo.user_profile = "../assets/images/Anonymous.png"
      }
    })
  }
}
</script>

<style>

</style>