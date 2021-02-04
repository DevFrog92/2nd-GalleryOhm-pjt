<template>

  <body class="login_signup_form">
    <div class="login_signup_container" id="container">
      <!-- 회원가입-->
      <div class="form-container sign-up-container">
        <form>
          <h1 class="signup_title">Create Account</h1>
          <div class="focus_container">
            <div id="id_uniqe_check">
              <div class="id_unique">
              <input id="unique_user_id" type="text" placeholder="ID" required v-model="userInfo.user_id" @blur="checkId" />
              <span class="focus-border"></span>
              </div>
              <span class="id_check_before"><i class="far fa-check-square"></i></span>
              <!-- <span class="id_check_after"><i class="fas fa-check-square"></i></span> -->
            </div>
          </div>
          <div class="nickname">
            <div class="focus_container">

              <input class="nickname_input" type="text" placeholder="재밌는 랜덤 닉네임이 주어집니다" readonly required="required"
                v-model="nickName" />
              <span class="focus-border"></span>
            </div>
            <div class="random_btn_container">
            <!-- <button class="random_btn" @click.prevent="makeUserNickName"><i class="fas fa-redo"></i></button> -->
            </div>
          </div>
          <div class="nickname_p">* 닉네임은 마이페이지에서 직접 수정 가능합니다.</div>
          <div class="focus_container">

            <input type="password" placeholder="Password" required="required" v-model="userInfo.user_password" />
            <span class="focus-border"></span>
          </div>
          <div class="focus_container">

            <input type="password" placeholder="Password confirm" required="required"
              v-model="userInfo.user_passwordConfirm" />
            <span class="focus-border"></span>
          </div>
          <div class="focus_container">

            <input type="text" placeholder="이름" required="required" v-model="userInfo.user_name" />
            <span class="focus-border"></span>
          </div>
          <div class="focus_container">

            <input type="email" placeholder="Email" required="required" v-model="userInfo.user_email" />
            <span class="focus-border"></span>
          </div>
          <div class="focus_container">

            <input type="tel" placeholder="Phone" required="required" v-model="userInfo.user_tel" />
            <span class="focus-border"></span>
          </div>
          <button class="signup_btn loginFormBtn" @click.prevent="join">Sign Up</button>
        </form>
      </div>

      <!--로그인-->
      <div class="form-container sign-in-container">
        <form>
          <h1 class="signin_title">SIGN IN</h1>
          <!--
            <div class="social-container">
              <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
              <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
              <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
            </div>
            <span>or use your account</span>-->
          <div class="focus_container">
            <input class="login_id" type="text" placeholder="ID" v-model="userInfo.user_id" />
            <span class="focus-border"></span>
          </div>
          <div class="focus_container">
            <input class="login_password" type="password" placeholder="Password" @keypress.enter="login" v-model="userInfo.user_password" />
            <span class="focus-border"></span>
          </div>
          <button class="trigger" @click.prevent="toggleModalId">Forgot your ID?</button>
          <!--         아이디찾기 모달창-->
          <div class="find_modal">
            <div class="modal_content">
              <div class="close_button" @click="toggleModalId"><i class="fas fa-times"></i></div>
              <h2 class="modaltitle">Find ID</h2>
              <div class="focus_container">
                <input type="text" placeholder="Name" v-model="userInfo.user_name">
                <span class="focus-border"></span>
              </div>
              <div class="focus_container">
                <input type="email" placeholder="Email" v-model="userInfo.user_email">
                <span class="focus-border"></span>
              </div>
              <button class="findid_btn" @click.prevent="findId">Find ID</button>
              <button class="find_cancel" @click.prevent="toggleModalId">Cancel</button>

            </div>
          </div>



          <button class="trigger_pw" @click.prevent='toggleModalPw'>Forgot your Password?</button>
          <!--       비번찾기 모달창-->
          <div class="find_modal_pw">
            <div class="modal_content_pw">
              <div class="close_button_pw" @click='toggleModalPw'><i class="fas fa-times"></i></div>
              <h2 class="modaltitle">Find PW</h2>
              <div class="focus_container">
                <input type="text" placeholder="ID" v-model="userInfo.user_id">
                <span class="focus-border"></span>
              </div>
              <div class="focus_container">
                <input type="email" placeholder="Email" v-model="userInfo.user_email">
                <span class="focus-border"></span>
              </div>
              <div class="focus_container">
                <input type="tel" placeholder="Phone" v-model="userInfo.user_tel">
                <span class="focus-border"></span>
              </div>
              <button class="findpw_btn" @click.prevent="findPassword">Find PW</button>
              <button class="cancel_pw" @click.prevent='toggleModalPw'>Cancel</button>

            </div>
          </div>
          <button class="signin_btn loginFormBtn" @click.prevent="login">Sign In</button>
          <div class="social-span">or use your SNS account</div>
          <div class="social-container">
            
            <a href="#" class="social1"><img src="../../assets/images/google.png" alt=""></a>
            <a href="#" class="social2"><img src="../../assets/images/naver.png" alt=""></a>
            <a href="#" class="social3"><img src="../../assets/images/kakao5.png" alt=""></a>
          </div>
        </form>
      </div>


      <div class="overlay-container">
        <div class="overlay">
          <div class="overlay-panel overlay-left">
            <h1>Welcome Back!</h1>
            <p>To keep connected with us please login with your personal info</p>
            <button class="ghost loginFormBtn" id="signIn" @click="activeBtn">Sign In</button>
          </div>
          <div class="overlay-panel overlay-right">
            <h1>Hello, Friend!</h1>
            <p>Enter your personal details and start journey with us</p>
            <button class="ghost loginFormBtn" id="signUp" @click="activeBtn">Sign Up</button>
          </div>
        </div>
      </div>
    </div>
  </body>
</template>

<script>
  import '../../assets/css/LoginSingup.css'
  import init from '../../assets/js/SigninSignup'
  import common from '../../assets/js/common/objectCelar'
  import mod from '../../assets/js/settings'
  import http from '../../api/http'

  export default {
    data() {
      return {
        userInfo: {
          user_id:'',
          user_password:'',
          user_email:'',
          user_name:'',
          user_tel:'',
        },
      }
    },
    computed:{
      nickName(){
        return this.$store.state.state.user_nickName
      }
    },
    created() {
    },
    mounted(){
    },
    methods: {
      join() {
        this.userInfo.user_nickName = this.nickName;
        init.join(this.userInfo);
        this.userInfo.user_password='';
        // common.objectCelar(this.userInfo);
      },
      activeBtn() {
        init.activeBtn(this.userInfo);
      },
      toggleModalId() {
        init.toggleModalId(this.userInfo);
      },
      toggleModalPw() {
        init.toggleModalPw(this.userInfo);
      },
      objectCelar() {
        common.objectCelar(this.userInfo);
      },
      login() {
        console.log(this.userInfo);
        init.login(this.userInfo);
      },
      findPassword() {
        init.findPassword(this.userInfo);
      },
      findId() {
        init.findId(this.userInfo);
      },
      makeUserNickName() {
        mod.makeUserNickName(this.userInfo.user_id);
      },
      checkId(){
        if (this.userInfo.user_id.trim() !== "" ){
        const formData = new FormData();
        const checkBtn = document.querySelector('.id_check_before');

        formData.append('input_id',this.userInfo.user_id)
        http.post('/user/checkId',formData)
          .then(response => {
            if(response.data.state === 'succ'){
              checkBtn.classList.add('id_check_after');
            }else{
              alert('중복된 아이디 입니다.');
              this.userInfo.user_id = "";
              checkBtn.classList.remove('id_check_after');

            }
          }).catch(err => {
            console.log(err);
          })
        }
      }
    },

  }
</script>

<style>

</style>