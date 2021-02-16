<template>
  <div class="setting">
    <div class="setting_grid">

      <div class="pw_center" v-if="!checkPassword_state">
        <p class="pw_check">회원 정보 수정을 원하시면 비밀번호를 입력해주세요. </p>
        <label>
          <div class="padlock">
            <svg id="lock" viewbox="0 0 64 64">
              <circle stroke-width="2" fill="none" cx="32" cy="32" r="31" />
              <path
                d="M40.2,29v-4c0-4.5-3.7-8.2-8.2-8.2s-8.2,3.7-8.2,8.2v4H21v15h22V29H40.2z M27.2,25 c0-2.6,2.1-4.8,4.8-4.8s4.8,2.1,4.8,4.8v4h-9.5V25z" />
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


      <div class="setting_con setting_left_size" v-show="checkPassword_state">
        <div class="setting_menu">
          <div class="active" data-value='1'>
            <span class="light"></span><span>회원정보수정</span>
          </div>
          <div data-value='2'><span class="light"></span><span>작가등록</span></div>
          <div data-value='3'><span class="light"></span><span>회원탈퇴</span></div>
        </div>
      </div>



      <div class="setting_con setting_right_size" v-show="checkPassword_state">
        <ul class="setting_content">
          <li class="active">
            <div class="modUserInfo">
              <div class="modUserContainer1">
                <div class="file_input">
                  <div class="img_preview">
                    <img :src="img_url" alt="">
                  </div>
                  <input type="file" ref="work_piece" hidden @change="onChangeImage" /><br />
                  <button class="uploadImg" @click.prevent="onClickUploadImage">프로필 사진 업로드</button><br>
                </div>
              </div>
              <div class="modUserContainer2">
                <div class="UserContainer2">
                  <input type="text" id="username" required="" v-model="userInfo.user_id" />
                  <label for="username">아이디</label>
                </div>
                <div class="UserContainer2 mod_nickname">
                  <!-- TODO:CSS layout 다시 잡기 -->

                  <input type="text" class="nickname_self" readonly=true v-model="self_nickName" />
                  <input type="text" id="nickname" readonly=true v-model="this.state.user_nickName" />
                  <button class="random_btn" @click.prevent="makeUserNickName"><i class="fas fa-random"></i></button>
                  <button class="selfmod_btn" @click.prevent="modifyUserNickName">직접수정</button>
                  <label for="nickname">닉네임</label>
                </div>
                <div class="UserContainer2">
                  <input type="password" id="password1" required="" v-model="userInfo.user_password" />
                  <label for="password1">비밀번호</label>
                </div>
                <div class="UserContainer2">
                  <input type="password" id="password2" required="" />
                  <label for="password2">비밀번호 확인</label>
                </div>
                <div class="UserContainer2">
                  <input type="email" id="email" required="" v-model="userInfo.user_email" />
                  <label for="email">이메일</label>
                </div>
                <div class="UserContainer2">
                  <input type="text" id="name" required="" v-model="userInfo.user_name" />
                  <label for="name">이름</label>
                </div>
                <div class="UserContainer2">
                  <input type="tel" id="tel" required="" v-model="userInfo.user_tel" />
                  <label for="tel">전화번호</label>
                </div>
                <div class="UserContainer2">
                  <input type="date" id="birth" v-model="userInfo.user_birth" />
                  <label for="birth">생년월일</label>
                  <p id="mod_explain">* 19금관 이용을 원하신다면 반드시 입력해주세요.</p>
                </div>
              </div>

              <div class="save_btn_container">
                 <div class="mod_info_button" @click="modifyUser"><span>수정완료</span></div>
              </div>

            </div>
          </li>

          <li>

            <div class="registerArtist">
              <h1 class="third_title">작가등록을 위해 아래를 작성해주세요.</h1>
              <!-- 축하합니다 팝업창?------ -->
              <div class="register_artist_container">
                <div class="register_artist">
                  <input type="text" id="artistname" required="" v-model="artist_nickName" autocomplete="off" />
                  <label for="artistname">필명</label>
                  <p class="registerinfo">* 미입력시 닉네임으로 대체됩니다.</p>
                </div>
                <div class="register_artist" id="bankname">
                  <input type="text" id="bank" required="" v-model="artist_bank" />
                  <label for="bank">은행명</label>
                </div>
                <div class="register_artist bankaccount ">
                  <input type="text" id="account" required="" v-model="artist_account" />
                  <label for="account">계좌번호</label>
                  <p class="registerinfo">* 후원을 위한 계좌번호를 입력해주세요.</p>
                </div>
                <div class="register_artist" id="rank_info">
                  <input type="text" id="rank" />
                  <label for="rank">등급</label>
                  <!--                  <p class="rank__p">등급 <img src="../assets/images/rookie.png" alt=""></p>-->
                  <button type='button' id="rank_modal_btn"><i class="fas fa-info-circle"></i></button>
                  <div class="rank_modal_wrap">
                    <div class="rank_modal_close"><a href="#"><i class="fas fa-times"></i></a></div>

                    <div class="rank_modal_content">
                      여기는 내용이다아아아아아아
                    </div>
                  </div>
                  <p class="registerinfo">* 등급은 작가등록 후 gallery의 등급기준에 따라 자동으로 부여됩니다.</p>
                </div>
                <div class="register_save_btn_container">
                 <div class="mod_info_button"  v-if="user__type === '1'"  @click="register_artist"><span>작가등록</span></div>
                 <div class="mod_info_button"   v-else  @click="withdraw_artist"><span>작가 해지</span></div>
                  
                </div>
              </div>
            </div>



          </li>
          <li class="leave_user_li">
            <div class="unscribe_container">
              <div class="unscribe_container2">

                <h2 class="unscribe_title">왜 저희 사이트를 떠나시나요?</h2>
                <div class="unscribe_reason">
                  <div class="reason_container">
                    <form>
                      <label>
                        <input type="radio" name="radio" checked />
                        <span>더 좋은 사이트를 발견했습니다.</span>
                      </label>
                      <label>
                        <input type="radio" name="radio" />
                        <span>필요한 정보를 얻을 수 없습니다.</span>
                      </label>
                      <label>
                        <input type="radio" name="radio" />
                        <span>기타</span>
                      </label>
                    </form>
                  </div>
                  <div class="unscribe_btn_container">

                   <div class="mod_info_button" @click="askWhy"><span>탈퇴하기</span></div>
                  </div>
                  <!-- <button @click="askWhy">탈퇴하기</button> -->
                </div>
              </div>
              <div class="delete__check1">
                정말 나갈꺼니?
              </div>
              <div class="delete__check2">
                정말 나갈꺼니?
              </div>
              <div class="delete__check3">
                정말 나갈꺼니?
              </div>
            </div>
          </li>

        </ul>
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
        <p>저희 '갤러리 Ω,'는 작가님의 다양한 작품 활동을 지원하기 위해서 최선을
          다하고 있습니다. 문의가 있다면 언제나 저희에게 연락을 주십시오. 다시 한번 환영합니다!!
        </p>
      </div>
      <div slot="body" v-else>
        <h3>{{userInfo.user_nickName}}작가님!</h3>
        <p>저희 '갤러리 Ω,'에서의 작가활동은 어떠셨나요? 다신 한번 만나뵙기를 기원하겠습니다. 작가님의 앞으로의
          작가활동을 저희 저희 '갤러리 Ω,'이 응원하겠습니다!
        </p>
      </div>


      <div slot="footer">
        <button class="closeRegisterGalleryBtn" @click="[showModal=false,resetpage()]">나가기</button>
      </div>
    </Modal>
  </div>
</template>

<script>
  import '../assets/css/Settings.css'
  import mod from '../assets/js/settings.js'
  import http from '../api/http'
  import {
    mapState
  } from 'vuex'
  import Modal from '../pages/Modal'

  export default {
    data() {
      return {
        name: '다비드',
        userInfo: {},
        user_password_confirm: '',
        password_for_mod: '',
        img_url: '',
        checkPassword_state: localStorage.getItem('checkPassword_state'),
        self_nickName: '',
        self_mode: false,
        artist_nickName: '',
        artist_bank: '',
        artist_account: '',
        rank_new: 'Rookie',
        artist_resister: "",
        showModal: false,
        user__type: localStorage.getItem('user_type'),
        artist_info: {
          "artist_account": '',
          "artist_bank": '',
          "artist_exp": 0,
          "artist_id": '',
          "artist_nickName": '',
        }
      }
    },
    components: {
      Modal
    },
    computed: {
      ...mapState(['state']),
    },
    mounted() {
      mod.design();
      // this.img_url = '../assets/images/Anonymous.png'
      // if(this.userInfo.user_profile === null){
      // }
    },
    methods: {
      askWhy() {
        const askWhy = confirm('정말이냐??????')
        console.log(askWhy);
      },
      resetpage() {
        this.$router.go();
      },
      modifyUser() {
        if (this.self_nickName) {
          this.userInfo.user_nickName = this.self_nickName;

        } else {
          this.userInfo.user_nickName = this.state.user_nickName;
        }
        mod.modifyUser(this.userInfo, this.password_for_mod);
      },
      leaveUser() {
        mod.leaveUser(this.userInfo.user_id);
      },
      checkPassword() {
        mod.checkPassword(this.userInfo, this.password_for_mod);
        this.password_for_mod = '';
      },
      makeUserNickName() {
        mod.makeUserNickName(this.userInfo.user_id);
      },
      modifyUserNickName() {
        const readNickName = document.querySelector('#nickname');
        const self_NickName = document.querySelector('.nickname_self');
        readNickName.classList.add("nickname_no_show");
        self_NickName.classList.add('nickname_self_show');
        self_NickName.readOnly = false;
        // this.self_mode != this.self_mode;
      },
      onChangeImage(event) {
        const imgFile = event.target.files[0];
        // console.log(imgFile,'onChangeImage');
        this.userInfo.user_profile = imgFile;
        this.img_url = URL.createObjectURL(imgFile);
      },
      onClickUploadImage() {
        this.$refs.work_piece.click();
      },
      register_artist() {
        const formData = {
          "artist_account": this.artist_account,
          "artist_bank": this.artist_bank,
          "artist_exp": 0,
          "artist_id": localStorage.getItem('user_id'),
          "artist_nickName": this.artist_nickName,
        }
        http.post("/artist/changeArtist", formData)
          .then(response => {
            console.log(response.data);
            this.artist_resister = response.data.name;
            this.showModal = true;
            localStorage.setItem('user_type', 2);
            this.user__type = 2;

          })
      },
      withdraw_artist() {
        http.post(`/artist/leaveArtist/{artist_id}?artist_id=` + localStorage.getItem('user_id'))
          .then(response => {
            this.artist_resister = response.data.name;
            console.log(response.data);
            this.showModal = true;
            localStorage.setItem('user_type', 1);
            this.user__type = 1;

          })
      },
      getArtistInfo() {
        http.get('/artist/getArtistInfo/', {
          params: {
            artist_id: localStorage.getItem('user_id')
          }
        })
          .then(response => {
            console.log('get artist info', response.data)
            this.artist_info = response.data;
            this.artist_account = this.artist_info.artist_account;
            this.artist_bank = this.artist_info.artist_exp;
            this.artist_nickName = this.artist_info.artist_nickName;
          })
      }

    },
    created() {
      this.rank_new = 'Rookie';
      http.get('/user/getUserInfo', {
          params: {
            user_id: localStorage.getItem('user_id')
          }
        })
        .then(response => {
          this.userInfo = response.data;
          localStorage.setItem('user_type', this.userInfo.user_type);
          if (response.data.user_profile !== null) {
            this.img_url = "data:/image/jpeg;base64," + this.userInfo.user_profile
            const byteCharacters = atob(this.userInfo.user_profile);
            const byteNumbers = new Array(byteCharacters.length);
            for (let i = 0; i < byteCharacters.length; i++) {
              byteNumbers[i] = byteCharacters.charCodeAt(i);
            }
            const byteArray = new Uint8Array(byteNumbers);
            const blob = new Blob([byteArray], {
              type: 'image/jpeg'
            });
            const file = new File([blob], 'profile')
            // console.log(file);
            this.userInfo.user_profile = file;
          } else {
            this.img_url = "../assets/images/Anonymous.png"
          }
        })
      this.getArtistInfo();

    }
  }
</script>

<style>

</style>