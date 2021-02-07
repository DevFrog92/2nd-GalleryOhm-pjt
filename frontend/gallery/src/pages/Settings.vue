<template>

  <div id="modwrapper" class="show_me_mod" >
    <div v-show="checkPassword_state">

    <div id="left-side" >
      <ul>
        <li class="modmain active">
          <!-- <div class="modicon active">
            <i class="fas fa-heart icon"></i>
          </div> -->
          <p>WELCOME</p>
        </li>
        <div v-show="checkPassword_state">
        <li class="moduser">
          <!-- <div class="modicon">

            <i class="fas fa-heart modicon"></i>
          </div> -->
          <p>Modify Info</p>
        </li>
        <li class="modpage">
          <!-- <div class="modicon">
            <i class="fas fa-heart modicon"></i>
          </div> -->
          <p>Register Artist</p>
        </li>
        <li class="unscribe">
          <!-- <div class="modicon">
            <i class="fas fa-heart modicon"></i>
          </div> -->
          <p>Unsubscribe</p>
        </li>
        </div>
      </ul>
    </div>
    </div>
    <div id="modborder"  v-show="checkPassword_state">
      <div id="line" class="one"></div>
    </div>

    <div id="right-side">

      <div id="first" class="active">

          <div class="pw_center" v-if="!checkPassword_state">
            <label>
              <div class="padlock">
                <svg id="lock" viewbox="0 0 64 64">
                  <circle stroke-width="2" fill="none" cx="32" cy="32" r="31" />
                  <path d="M40.2,29v-4c0-4.5-3.7-8.2-8.2-8.2s-8.2,3.7-8.2,8.2v4H21v15h22V29H40.2z M27.2,25 c0-2.6,2.1-4.8,4.8-4.8s4.8,2.1,4.8,4.8v4h-9.5V25z" />
                </svg>
              </div>
            </label>
            <div class="pw_check_icon">
              <input class="setting_pw_check" placeholder="Password" type="password" v-model="password_for_mod">
              <div class="pw_check_arrow">
                <svg id="arrow" viewbox="0 0 48 44">
                  <polygon points="27.7,13.3 26.3,14.7 31.6,20 14,20 14,22 31.6,22 26.3,27.3 27.7,28.7 35.4,21 " />
                </svg>
              </div>
              <div class="pw_error_message">Please try again.</div>
            </div>
          </div>


        <!-- <h2>Thank you for subscirbe</h2><br>
        <h1>test website</h1>
        <h2>You can modify infomation in this page</h2>
        <div v-if="!checkPassword_state">
          <label for="id_for_mod">아이디</label>
          <input type="text" class="id_for_mod" v-model="userInfo.user_id">
          <label for="password_for_mod">비빌번호</label>
          <input type="password" class="password_for_mod" v-model="password_for_mod">
          <button @click="checkPassword">check pw</button>
        </div> -->
      </div>


      <div id="second">
        <!--        <div class="modUserTitle">Modify User Info</div>-->
        <div class="modUserContainer1">
          <div class="file_input">
            <div class="img_preview">
              <img :src="img_url" alt="">
            </div>
              <input type="file" ref="work_piece" hidden @change="onChangeImage"/><br/>
              <button class="uploadImg" @click.prevent="onClickUploadImage">Upload Image</button><br>
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
            <button class="random_btn" @click.prevent="makeUserNickName"><i class="fas fa-redo"></i></button>
            <button class="selfmod_btn" @click.prevent="modifyUserNickName">Edit</button>
            <label for="nickname">NICKNAME</label>
            <p id="mod_explain">* Edit 버튼을 통해 직접 수정도 가능합니다.</p>
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

        <div class="save_btn_container">
          <div class="saveBtns">
            <div class="saveBtn" @click="modifyUser" data-display="SAVE"></div>
          </div>
        </div>
      </div>


      <div id="third">
        <!-- TODO: if문으로 띄우기 -->
         <!-- <div class="register_btn_container">
          <div class="registerBtns">
            <div class="registerBtn" data-display="Register Artist"></div>
          </div>
        </div> -->

        <p class="third_title">작가등록을 위해 아래를 작성해주세요.</p>
        <!-- 축하합니다 팝업창?------ -->
        <div class="register_artist_container">
          <div class="register_artist">
            <input type="text" id="artistname" required="" v-model="artist_nickName"/>
            <label for="artistname">필명</label>
            <p class="registerinfo" >* 미입력시 닉네임으로 대체됩니다.</p>
          </div>
          <div class="register_artist  bankname">
            <input type="text" id="bank" required="" v-model="bank_name" />
            <label for="bank">은행명</label>
          </div>
          <div class="register_artist bankaccount ">
            <input type="text" id="account" required="" v-model="bank_account"/>
            <label for="account">계좌번호</label>
            <p class="registerinfo">* 후원을 위한 계좌번호를 입력해주세요.</p>
          </div>

          <div class="register_artist">
            <!-- <input type="text" id="rank" required="" readonly :value="rank_new" /> -->
            <!-- <label for="rank">rank</label> -->
            <p class="rank__p">등급 <img src="../assets/images/rookie.png" alt=""></p>
            <p class="registerinfo">* 등급은 작가등록 후 gallery의 등급기준에 따라 자동으로 부여됩니다.
              기준은 갤러리 소개에서 보실 수 있습니다.</p>
          </div>
            <div class="register_save_btn_container">
          <div class="registerSaveBtns">
            <div class="registerSaveBtn" v-if="user__type === '1'" data-display="Register Artist" @click="register_artist"></div>
            <div class="registerSaveBtn" v-else data-display="Withdraw  Artist" @click="withdraw_artist"></div>
          </div>
        </div>
        </div>
      </div>


      <div id="fourth">
        <div class="unscribe_container">
      
        <h2>Why you want to unsubscribe our page?</h2>
        <div class="unscribe_reason">
         
        <div class="reason_container">
          <form>
            <label>
              <input type="radio" name="radio" checked/>
              <span>다른사이트가 더 좋아서</span>
            </label>
            <label>
              <input type="radio" name="radio"/>
              <span>그냥그냥</span>
            </label>
            <label>
              <input type="radio" name="radio"/>
              <span>내맘이지</span>
            </label>
          </form>
        </div>
        </div>

        <button class="unscribe_button">
            <div class="trash">
                <div class="unscribe_top">
                    <div class="paper"></div>
                </div>
                <div class="box"></div>
                <div class="check">
                    <svg viewBox="0 0 8 6">
                        <polyline points="1 3.4 2.71428571 5 7 1"></polyline>
                    </svg>
                </div>
            </div>
            <!-- 몇초 후에 삭제하고 싶다..... -->
            <span >Delete Item</span>
        </button>

      </div>
          
        </div>
    </div>
     <Modal v-if="showModal" @close="showModal = false">
      <!--
      you can use custom content here to overwrite
      default content
    -->
      <div slot="header" v-if="artist_resister === 'changeArtist'">
        <h3>
          작가 등록
        </h3>
        <i class="fas fa-times closeModalBtn" @click="showModal=false"></i>
      </div>
      <div slot="header" v-else>
        <h3>
          작가 해지
        </h3>
        <i class="fas fa-times closeModalBtn" @click="showModal=false"></i>
      </div>

      <div slot="body" v-if="artist_resister === 'changeArtist'">
        <h3>{{userInfo.user_nickName}}작가님! 환영합니다.</h3>
        <p>저희 SSAATCHI Gallery는 작가님의 다양한 작품 활동을 지원하기 위해서 최선을
          다하고 있습니다. 문의가 있다면 언제나 저희에게 연락을 주십시오. 다시 한번 환영합니다!!
        </p>
      </div>
      <div slot="body" v-else>
        <h3>{{userInfo.user_nickName}}작가님!</h3>
        <p>저희 SSAATCHI Gallery에서의 작가활동은 어떠셨나요? 다신 한번 만나뵙기를 기원하겠습니다. 작가님의 앞으로의 
          작가활동을 저희 저희 SSAATCHI Gallery가 응원하겠습니다!!
        </p>
      </div>

    
      <div slot="footer">
        <button class="closeRegisterGalleryBtn" @click="showModal=false">나가기</button>
      </div>
    </Modal>

  </div>
</template>

<script>
import '../assets/css/Settings.css'
import mod from '../assets/js/settings.js' 
import http from '../api/http'
import { mapState } from 'vuex'
  import Modal from '../pages/Modal'

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
      artist_nickName:'',
      bank_name:'',
      bank_account:'',
      rank_new:'Rookie',
      artist_resister:"",
      showModal: false,
      user__type:localStorage.getItem('user_type'),
    }
  },
  components:{
    Modal
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
    },
    register_artist(){
      const formData = {
      "artist_account": this.bank_account,
      "artist_bank": this.bank_name,
      "artist_exp": 0,
      "artist_id": localStorage.getItem('user_id'),
      "artist_nickName": localStorage.getItem('user_nickName')
      }
      http.post("/artist/changeArtist",formData)
      .then(response => {
        console.log(response.data);
        this.artist_resister = response.data.name;
        this.showModal = true;
        localStorage.setItem('user_type',2);
        this.user__type = 2;

      })
    },
    withdraw_artist(){
      console.log(localStorage.getItem('user_id'))
      http.post(`/artist/leaveArtist/${localStorage.getItem('user_id')}`)
      .then(response=>{
        this.artist_resister = response.data.name;
        console.log(response.data);
        this.showModal = true;
        localStorage.setItem('user_type',1);
        this.user__type = 1;

      })
    }

  },
  created(){
    this.rank_new = 'Rookie';
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
        this.img_url = "../assets/images/Anonymous.png"
      }
    })
  }
}
</script>

<style>

</style>