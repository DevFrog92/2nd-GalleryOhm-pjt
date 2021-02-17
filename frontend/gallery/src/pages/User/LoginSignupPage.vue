<template>
<div class="wrap__login" ondragstart="return false">
  <body class="login_signup_form">
    <div class="login_signup_container" id="singup_container">
      <!-- 회원가입-->
      <div class="form-container sign-up-container">
        <form>
          <h1 class="signup_title">회원가입</h1>
          <div class="focus_container">
            <div id="id_uniqe_check">
              <div class="id_unique">
                <input id="unique_user_id" type="text" autocomplete="off" placeholder="아이디" required
                  v-model="userInfo.user_id" @blur="checkId" />
                <span class="focus-border"></span>
              </div>
              <span class="id_check_before"><i class="far fa-check-square"></i></span>
              <!-- <span class="id_check_after"><i class="fas fa-check-square"></i></span> -->
            </div>
          </div>
          <div class="nickname">
            <div class="focus_container">
              <input class="nickname_input" type="text" placeholder="재밌는 랜덤 닉네임이 주어집니다" readonly required="required" />
              <span class="focus-border"></span>
            </div>
            <div class="random_btn_container">
              <!-- <button class="random_btn" @click.prevent="makeUserNickName"><i class="fas fa-redo"></i></button> -->
            </div>
          </div>
          <div class="nickname_p">* 닉네임은 마이페이지에서 직접 수정 가능합니다.</div>
          <div class="focus_container">

            <input type="password" placeholder="비밀번호" required="required" v-model="userInfo.user_password" />
            <span class="focus-border"></span>
          </div>
          <div class="focus_container">
            <input type="password" placeholder="비밀번호 확인" required="required"
              v-model="userInfo.user_passwordConfirm" />
            <span class="focus-border"></span>
          </div>
          <div class="focus_container">
            <input type="text" placeholder="이름" autocomplete="off" required="required" v-model="userInfo.user_name" />
            <span class="focus-border"></span>
          </div>
          <div class="focus_container">
            <input type="email" placeholder="이메일" autocomplete="off" required="required"
              v-model="userInfo.user_email" />
            <span class="focus-border"></span>
          </div>
          <div class="focus_container">
            <input type="tel" placeholder="전화번호" autocomplete="off" required="required" v-model="userInfo.user_tel" />
            <span class="focus-border"></span>
          </div>
          <form>
            <div id="terms_checkbox_id">
              <input class="terms_checkbox" type="checkbox" required="required">
              <button class="triggerTerms">약관에 동의 합니다.</button>
            </div>
          </form>






          <button class="signup_btn loginFormBtn" @click.prevent="join">회원가입</button>
        </form>

      </div>

      <!--로그인-->
      <div class="form-container sign-in-container">
        <form>
          <h1 class="signin_title">로그인</h1>
          <!--
            <div class="social-container">
              <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
              <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
              <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
            </div>
            <span>or use your account</span>-->
          <div class="focus_container">
            <input class="login_id" type="text" placeholder="아이디" v-model="userInfo.user_id" />
            <span class="focus-border"></span>
          </div>
          <div class="focus_container">
            <input class="login_password" type="password" placeholder="비밀번호" @keypress.enter.prevent="login"
              v-model="userInfo.user_password" />
            <span class="focus-border"></span>
          </div>
          <button class="trigger" @click.prevent="toggleModalId">아이디를 잊어버리셨나요?</button>
          <!--         아이디찾기 모달창-->
          <div class="find_modal">
            <div class="modal_content">
              <div class="close_button" @click="toggleModalId"><i class="fas fa-times"></i></div>
              <h2 class="modaltitle">아이디 찾기</h2>
              <div class="find__id__content" v-if="!findIdState">
              <div class="modal_focus_container">
                <input type="text" placeholder="이름" v-model="userInfo.user_name">
                <span class="focus-border"></span>
              </div>
              <div class="modal_focus_container">
                <input type="email" placeholder="이메일" v-model="userInfo.user_email">
                <span class="focus-border"></span>
              </div>
              <button class="findid_btn" @click.prevent="findId">아이디 찾기</button>
              <button class="find_cancel" @click.prevent="toggleModalId">취소</button>
              </div>
              <div class="find__id__state" v-else>
                <h5>귀하의 아이디는 {{userInfo.user_id}}입니다.</h5>
              <button class="find_cancel" @click.prevent="toggleModalId">취소</button>
              </div>

            </div>
          </div>



          <button class="trigger_pw" @click.prevent='toggleModalPw'>비밀번호를 잊어버리셨나요?</button>
          <!--       비번찾기 모달창-->
          <div class="find_modal_pw">
            <div class="modal_content_pw">
              <div class="close_button_pw" @click='toggleModalPw'><i class="fas fa-times"></i></div>
              <h2 class="modaltitle">비밀번호 찾기</h2>
              <div class="modal_focus_container">
                <input type="text" placeholder="아이디" v-model="userInfo.user_id">
                <span class="focus-border"></span>
              </div>
              <div class="modal_focus_container">
                <input type="email" placeholder="이메일" v-model="userInfo.user_email">
                <span class="focus-border"></span>
              </div>
              <div class="modal_focus_container">
                <input type="tel" placeholder="전화번호" v-model="userInfo.user_tel">
                <span class="focus-border"></span>
              </div>
              <button class="findpw_btn" @click.prevent="findPassword">비밀번호 찾기</button>
              <button class="cancel_pw" @click.prevent='toggleModalPw'>취소</button>

            </div>
          </div>
          <button class="signin_btn loginFormBtn" @click.prevent="login">로그인</button>

        </form>
      </div>


      <div class="overlay-container">
        <div class="overlay">
          <div class="overlay-panel overlay-left">
            <h1>반갑습니다! 옴 갤러리와 함께 하세요.</h1>
            <p>옴 갤러리는 작가와 게스트 모두를 위한 기회의 공간입니다.</p>
            <button class="ghost loginFormBtn" id="signIn" @click="activeBtn">로그인</button>
          </div>
          <div class="overlay-panel overlay-right">
            <h1>옴 갤러리에 오신 것을 환영합니다.</h1>
            <p>오늘도 저희 옴 갤러리와 함께 새로운 경험을 해보세요. </p>
            <button class="ghost loginFormBtn" id="signUp" @click="activeBtn">회원가입</button>
          </div>
        </div>
      </div>
    </div>
    <div class="terms_modal">
      <div class="terms_content">
        <div class="close_button_terms"><i class="fas fa-times"></i></div>
        <h2 class="termstitle">저작권 및 이용약관</h2>
        <p id="more_info">
          © 갤러리 옴, All rights reserved.
          <p id="info_terms">이 웹 사이트의 콘텐츠에 접근하기 전 아래의 이용 약관을 주의 깊게 살펴보십시오.</p>
          <p id="info_terms">1. 사용자 동의</p>
          <p>본 이용 약관 계약(이하 "계약")은 당사의 웹 사이트(이하 "웹 사이트") 사용에 대한 이용 약관을 명시합니다. 이 웹 사이트는 갤러리 옴,(이하 "당사")이
            소유 및 운영합니다. 귀하는 웹 사이트를 사용함으로써 본 계약에 동의하는 것으로 간주됩니다. </p>
          <p>당사는 이 계약의 내용을 언제든 수정할 권리가 있으며, 개정된 계약을 웹 사이트에 개제하여 변경 사항을 고지할 것입니다. 모든 변경 사항은 게재 즉시 효력이 발생하므로 본 계약의
            변경 사항을 자주 확인하십시오. 본 계약의 변경 사항이 발생한 후에도 웹 사이트를 지속적으로 이용하면 변경 사항에 동의하는 것으로 간주됩니다.</p>
          <p id="info_terms">2. 웹 사이트 콘텐츠의 사용 제한</p>
          <p>본 웹 사이트의 그림의 소유권은 당사 또는 그림작가 보유하며, 모든 그림은 대한민국 저작권법 및 저작권 조약의 보호를 받습니다. 귀하는 그림을 복사, 수정에 관여하거나, 이를 이용한
            파생물을 창작할 수 없습니다. </p>
          <p id="info_terms">3. 책임의 제한</p>
          <p>당사는 어떠한 상황에서도 그림, 웹 사이트 사용 및 사용 불능으로 인해 발생하거나 이와 연관된 계약 또는 과실 행위로 인한 모든 종류의 일반, 특수, 직접, 간접, 우발적, 결과적,
            징벌적 또는 기타 손해를 포함한 모든 손해에 대해 책임을 지지 않습니다. 당사는 모든 사용자의 명예훼손, 공격 또는 불법 행위에 대한 책임을 지지 아니합니다. </p>
          <p id="info_terms">4. 사용자 행동</p>
          <p>귀하는 본 콘텐츠 및 웹 사이트를 합법적인 목적으로만 사용하는 데 동의합니다. 귀하는 범법 행위에 해당하거나, 법적 책임을 야기하거나, 또는 규정을 위반하는 방식으로 콘텐츠 또는 웹
            사이트를 사용하는 것이 금지되어 있습니다.
          </p>
      </div>
    </div>

  </body>
  </div>
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
          user_id: '',
          user_password: '',
          user_email: '',
          user_name: '',
          user_tel: '',
          user_passwordConfirm: '',
          findIdState:false,
        },
      }
    },
    created() {
      this.findIdState = false
      if(localStorage.getItem('auth_token')){
        this.$router.push('/')
      }
      },
    mounted() {
      init.toggleModalInit();
    },
    methods: {
      join() {
        const check = document.querySelector('.terms_checkbox')
        if(check.checked){
          init.join(this.userInfo);
        }else{
          alert('약관 동의를 체크해주세요.')
        }
        this.userInfo.user_password = '';
        common.objectCelar(this.userInfo);
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
          init.login(this.userInfo);
      },
      findPassword() {
        init.findPassword(this.userInfo);
      },
      findId() {
        const formData = {
          "user_name": this.userInfo.user_name,
          "user_email": this.userInfo.user_email
        }
        http.post('/login/findId', formData)
          .then(response => {
            this.findIdState = true;
            this.userInfo.user_id = response.data;
          })
      },
      makeUserNickName() {
        mod.makeUserNickName(this.userInfo.user_id);
      },
      checkId() {
        if (this.userInfo.user_id.trim() !== "") {
          const formData = new FormData();
          const checkBtn = document.querySelector('.id_check_before');

          formData.append('input_id', this.userInfo.user_id)
          http.post('/user/checkId', formData)
            .then(response => {
              if (response.data.state === 'succ') {
                checkBtn.classList.add('id_check_after');
              } else {
                alert('중복된 아이디 입니다.');
                this.userInfo.user_id = "";
                checkBtn.classList.remove('id_check_after');

              }
            })
        }
      },

    },


  }
</script>

<style>

</style>