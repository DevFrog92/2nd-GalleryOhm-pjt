<template>
  <div class="My_profile_guest_container" ondragstart="return false">
    <div class="guest_profile_wrapper">




      <div class="profile_card profile_card1" :style="{backgroundImage:'url('+this.img_url+')'}">
        <div class="profile_border">
          <h2 class="profile_card_user_name">{{userInfo.user_id}}</h2>
          <div class="profile_icons">
            <i class="profile_icon_guest icon-chat" aria-hidden="true" @click="DMsideopen"></i>
          </div>
        </div>
      </div>




      <div class="profile_about">
        <h2 class="profile_user_name">{{userInfo.user_nickName}} <span v-show="!who_state" class="Guest__bell" @click="DMsideopen"><img
              src="../../assets/images/bell.png" alt=""><span 
              class="notification__num">{{Unread_count}}</span></span></h2>
        <div class="follow">
          <span>작품 수 : {{posts}}</span>
          <span @click="following_view">팔로잉 : {{followings.length}}</span>
          <span @click="follower_view">팔로워 : {{followers.length}}</span>
          <!-- <span  @close="showModal=true">Follower : {{followers.length}}</span> -->
        </div>
        <div class="artist_about_div">
          <p class="artist_about">{{this.user_about}}</p>
          <div v-if="modifyabout" class="modal_modify_about">
            <textarea name="artist_about_modify" id="artist_about_modify" cols="30" rows="10"
              v-model="user_about"></textarea>
              <div class="modify_btn" @click='registerUserAbout'>
              <div class="segment__guest">
                <button class="unit__guest unit__btn__guest" type="button"><img src="../../assets/images/pencil.png"
                    alt="" class='Guest__dm'></button>
              </div>
            </div>
          </div>
        </div>
        <span v-if="!modifyabout" class="icon-pencil-alt-span" @click="[modifyabout=!modifyabout,modify_state=false]">
          <div class="segment__guest" v-if="who_state">
            <button class="unit__guest unit__btn__guest" type="button"><img src="../../assets/images/pencil.png" alt=""
                class='Guest__dm'></button>
          </div>
        </span>



        <div class="profile_menu_footer">
          <div class="profile_menu">
            <div class="profile_label">더보기</div>
            <div class="profile_spacer"></div>
            <div class="profile_item"><span class="profile_menu_item" data-value="3">즐겨찾기</span></div>
            <div v-if="!who_state" class="profile_item"><span class="profile_menu_item" @click="moveSettings">회원정보 수정</span></div>
          </div>
        </div>
      </div>


      <div class="dm__list_Guest" v-show="who_state">
        <div class="exit__guest__dm" @click="DMsideclose">나가기</div>
        <div class="showDmContent_Guest">
          <div class="message__name__cover__guest">
            <div class="sender_name">{{guest_name}}</div>
          </div>
          <div class="message__title__cover__guest">
            <div class="sender_title"> 안녕하세요. {{guest_name}}입니다.</div>
          </div>

          <div class="message__content__cover__guest">
            <div class="sender_content">저에게 문의 혹은 궁금하신 점이 있다면 서신을 날려주세요.</div>
          </div>
          <div class="message__reply__guest">
            <div class="message__reply__titie">
              <label for="response_message_title" class="response_title">제목 : </label>
              <input type="text" id="response_message_title__guest" v-model="dm_title">
            </div>

            <textarea name="response_message" id="response_message__guest" v-model="dm_content"></textarea>
          </div>
          <div class="send_btn__guest" @click="DM_recived">
            <div class="segment__guest">
              <button class="unit__guest unit__btn__guest" type="button"><img src="../../assets/images/paper.png" alt=""
                  class='Guest__dm'></button>
            </div>
          </div>
        </div>
      </div>

      <div class="Guest__dm__list" v-show="!who_state">
        <div class="exit__Guest__dm" @click="DMsideclose">나가기</div>
        <div class="contact_to_me">
          <!-- <div class="GuestUnRead__message">읽지않은서신</div> -->
          <!-- <div class="GuestAll__message">전체서신</div> -->
          <ul class="Guest__DMList">
            <div :class="!dm.message_isCheck ? 'Guest__DM__item Guest__DM__item__read ' : 'Guest__DM__item' "
              v-for="(dm,index) of dm_list" :key="index" :data-name='dm.message_id' :data-value="JSON.stringify(dm)">
              <div class="Guest__DM__item__avatar">
                <img src="../../assets/images/user.png" />
              </div>
              <div class="Guest__DM__item__content">
                <span class="Guest__DM__item__title">{{dm.message_senderId}}</span>
                <span class="Guest__DM__item__message">{{dm.message_title}}<br>{{dm.message_sendDate}}</span>
              </div>
              <div class="Guest__DM__item__option delete Guest__dm-option" :data-value="dm.message_id"
                @click="DmDelete(dm.message_id)">
                <i class="fas fa-trash"></i>
              </div>
            </div>
          </ul>
        </div>
        <div class="Guest__showDmContent">
          <div class="Guest__message__name__cover">
            <div class="sender_name"></div>
          </div>
          <div class="Guest__message__title__cover">
            <div class="sender_title"></div>
            <div class="Guest__message__date__tiem">
              <div class="sender_date"></div>
              <div class="sender_time"></div>
            </div>
          </div>

          <div class="Guest__message__content__cover">
            <div class="sender_content"></div>
          </div>
          <div class="Guest__message__reply">
            <div class="Guest__message__reply__titie">
              <label for="response_message_title" class="Guest__response_title">제목 : </label>
              <input type="text" id="Guest__response_message_title" v-model="dm_title">
            </div>

            <textarea name="response_message" id="Guest__response_message" v-model="dm_content"></textarea>
          </div>
          <div class="Guest__message__footer">
            <div class="segment__mypage">
              <button class="unit__mypage unit__btn__mypage mypage__dm__send" type="button" @click="DM"><img
                  src="../../assets/images/paper.png" alt="" class='Guest__dm'></button>
            </div>
          </div>
        </div>
      </div>

      <div class="DmSendModal">

      </div>



    </div>
    <div class="segment__mypage">
      <div class="unit__mypage unit__btn__mypage move_to_top" type="button"><img src="../../assets/images/up-arrow.png"
          alt=""></div>
    </div>
    <div class="forth__section__guest">
    <h1 class="outer__wrapper__title">{{guest_name}}의 즐겨찾기</h1>
    <div class="outer-wrapper-guest" v-if="!scrap_state">
      <div class="scroll-wrapper">
        <div class="scroll-slide" v-for="(item,index) of scrap_list" :key="index">
          <img class="scrap__image" :data-value='item.work_id' :src="'data:/image/jpeg;base64,'+item.work_piece" alt="">
        </div>
      </div>
    </div>
    </div>
    
    <Modal v-if="showModal" @close="showModal = false">
      <div slot="header">
        <h3>
        </h3>
        <i class="fas fa-times closeModalBtn"
          @click="[showModal=false,getAllMyFollowing(),getAllMyFollower(),modal_following=false]"></i>
      </div>

      <div slot="body">
        <div class="notification__wrapper">
          <div class="notifications" v-if="modal_following">
            <div class="notifications__item" v-for="(people,index) of followings" :key="index">
              <div class="notifications__item__avatar">
                <img src="../../assets/images/user.png" />
              </div>
              <div class="notifications__item__content">
                <span class="notifications__item__title">{{people}}</span>
                <span class="notifications__item__message">Just started following you</span>
              </div>
              <div>
                <div class="notifications__item__option archive js-option">
                  <i class="fas fa-folder" :data-name="people"></i>
                </div>
                <div class="notifications__item__option delete js-option">
                  <i class="fas fa-trash" :data-name="people"></i>
                </div>
              </div>
            </div>
          </div>
          <div class="notifications" v-else>
            <div class="notifications__item" v-for="(people,index) of followers" :key="index">
              <div class="notifications__item__avatar">
                <img src="../../assets/images/user.png" />
              </div>
              <div class="notifications__item__content">
                <span class="notifications__item__title">{{people}}</span>
                <span class="notifications__item__message">Just started following you</span>
              </div>
              <div>
                <div class="notifications__item__option archive js-option">
                  <i class="fas fa-folder" :data-name="people"></i>
                </div>
                <div class="notifications__item__option delete js-option">
                  <i class="fas fa-trash" :data-name="people"></i>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div slot="footer">
        <button class="closeRegisterGalleryBtn"
          @click="[showModal=false,getAllMyFollowing(),getAllMyFollower(),modal_following=false]">나가기</button>
      </div>
    </Modal>


  </div>
</template>

<script>
  import '../../assets/css/MyPageForGuest.css'
  import init from '../../assets/js/MyPageForGuest.js'
  import Modal from '../../pages/Modal'
  import http from '../../api/http'

  export default {
    data() {
      return {
        showModal: false,
        modify_state: false,
        artist_name: this.props_id,
        img_url: localStorage.getItem('user_profile'),
        userInfo: {},
        test_url: '../../assets/images/1.png',
        user_about: '',
        DMmodal: true,
        Aboutmodal: !this.DMmodal,
        dm_title: '',
        dm_content: '',
        dm_list: [],
        my_work_lists: [],
        followers: 0,
        followings: 0,
        posts: 0,
        my_gallery_list: [],
        modifyabout: false,
        scrap_list: [],
        modal_follower: false,
        modal_following: false,
        my_gallery_1: {},
        my_gallery_2: {},
        my_gallery_poster: [],
        delete_gallery: false,
        delete_gallery_state: false,
        delete_gallery_name: '',
        all_my_works_year: {},
        all_my_works_month_first: {
          '01': [],
          '02': [],
          '03': [],
          '04': [],
          '05': [],
          '06': [],
        },
        all_my_works_month_second: {

          '07': [],
          '08': [],
          '09': [],
          '10': [],
          '11': [],
          '12': []
        },
        showPinterSide: false,
        showDm: false,
        showDMSide: false,
        pinter_image: [],
        guest_name: localStorage.getItem('props_id'),
        who_state: true,
        Unread_count: 0,
        scrap_state: false,
        bell__state: false,
      }
    },
    components: {
      Modal
    },
    props: ["props_id"],
    methods: {
      DMsideopen() {
        if (this.who_state) {
          const DMList = document.querySelector('.dm__list_Guest');
          console.log(DMList)
          DMList.classList.add('show_dm_side');
        } else {
          const DMList = document.querySelector('.Guest__dm__list');
          console.log(DMList)

          DMList.classList.add('show_dm_side');
        }
        init.DMModal();
      },
      DMsideclose() {
        if (this.who_state) {
          const DMList = document.querySelector('.dm__list_Guest');
          console.log(DMList)
          DMList.classList.remove('show_dm_side');
        } else {
          const DMList = document.querySelector('.Guest__dm__list');
          console.log(DMList)

          DMList.classList.remove('show_dm_side');
        }
        this.DMpull()
      },
      DmRead(message_id) {
        console.log('Dm Read', message_id);
      },
      DmDelete(id) {
        // const DMList = document.querySelector('.DMList')
        setTimeout(() => {
          console.log('DM Delete', id);
          const DMLIST = document.querySelectorAll('.Guest__DM__item')
          for (let item of DMLIST) {
            if (item.dataset.name == id) {
              console.log('지운다.')
              item.remove()
            }
          }
          console.log('뺀다')
        }, 1400);
      },
      refresh() {
        this.$router.go();
      },
      following_view() {
        console.log('Show me following')
        this.modal_following = true;
        setTimeout(() => {
          init.follow_modal();
        }, 200);
        this.showModal = true;

      },
      follower_view() {
        console.log('Show me follower', this.followers)
        setTimeout(() => {
          init.follow_modal();
        }, 200);
        this.showModal = true;
      },
      registerUserAbout() {
        let userAbout = this.user_about.replace(/(?:\r\n|\r|\n)/g, '<br/>');
        this.user_about = userAbout;
        const userAboutTag = document.querySelector('.artist_about');
        userAboutTag.innerHTML = this.user_about;
        const formData = new FormData();
        formData.append("user_about", this.user_about);
        formData.append('user_id', localStorage.getItem('props_id'));
        http.post('/user/modifyUserAbout', formData, {
            headers: {
              'Content-Type': 'application/json'
            }
          })
          .then(response => {
            this.modify_state = true;
            console.log('Register user_about', response);
            this.modifyabout = !this.modifyabout;
          })
      },
      DM_recived() {
        // const reply_id = document.querySelector('.sender_name')
        // console.log(reply_id.innerText);
        if (this.dm_title !== "" && this.dm_content !== "") {

          const test = {
            "message_content": this.dm_content,
            "message_id": 0,
            "message_isCheck": 1,
            "message_receiverId": localStorage.getItem('props_id'),
            "message_sendDate": "string",
            "message_senderId": localStorage.getItem('user_id'),
            "message_title": this.dm_title,
          }
          console.log('test', test)
          http.post('/message/sendMessage', test)
            .then(response => {
              console.log('Send DM', response.data)
              this.dm_title = "";
              this.dm_content = "";
            })
        } else {
          alert('내용을 입력해주세요.')
        }
      },
      DMpull() {
        http.post('/message/getAllMyReceiveMessage', localStorage.getItem('props_id'))
          .then(response => {
            console.log('Get all my dm list', response.data);
            this.dm_list = response.data;
            this.Unread_count = 0;
            for (let item of this.dm_list) {
              console.log(item.message_isCheck)
              if (item.message_isCheck == '1') {
                this.Unread_count += 1
              }
            }
            console.log(this.Unread_count, 'dmcount')
          })
      },
      moveSettings() {
        this.$router.push('/settings');
      },
      getAllMyFollowing() {
        http.get('/follow/getAllMyFollowing', {
            params: {
              user_id: localStorage.getItem('props_id')
            }
          })
          .then(response => {
            console.log('Get all my followings', response.data);
            this.followings = response.data;
          })
      },
      getAllMyFollower() {
        http.get('/follow/getAllMyFollower', {
            params: {
              user_id: localStorage.getItem('props_id')
            }
          })
          .then(response => {
            console.log('Get all my followers', response.data);
            this.followers = response.data;
          })
      },
      getMyWorksCount() {
        http.get('/work/getMyWorksCount/' + localStorage.getItem('props_id'))
          .then(response => {
            console.log('Get all my works count', response.data);

            this.posts = response.data;
          })
      },

      getAllScrapWork() {
        http.get('/work/getAllScrapWork/' + localStorage.getItem('props_id'))
          .then(response => {
            console.log('Get all scrap', response.data);
            this.scrap_list = response.data;
            if (!this.scrap_list.length) {
              this.scrap_state = true;
              console.log('스트랩한 작품이 없습니다.')
            }
          })
      },


      getUserInfo() {
        http.get('/user/getUserInfo', {
            params: {
              user_id: localStorage.getItem('props_id')
            }
          })
          .then(response => {
            console.log('Get props info data', response.data);
            this.userInfo = response.data;
            this.guest_name = this.userInfo.user_nickName;

            const userAbout = document.querySelector('.artist_about');
            userAbout.innerHTML = this.userInfo.user_about;
            this.img_url = 'data:/image/jpeg;base64,' + this.userInfo.user_profile;
            this.user_about = this.userInfo.user_about;
          })
      },
      DM() {
        const reply_id = document.querySelector('.sender_name')
        console.log(reply_id.innerText);

        const test = {
          "message_content": this.dm_content,
          "message_id": 0,
          "message_isCheck": 1,
          "message_receiverId": reply_id.innerText,
          "message_sendDate": "string",
          "message_senderId": localStorage.getItem('user_id'),
          "message_title": this.dm_title,
        }
        http.post('/message/sendMessage', test)
          .then(response => {
            console.log('Send DM', response.data)
            this.dm_title = "";
            this.dm_content = "";
          })
      }
    },
    mounted() {
      setTimeout(() => {
        init.init();
      }, 1000)
    },
    created() {
      console.log('this.propsId', this.props_id)
      if (this.props_id && localStorage.getItem('user_id') !== localStorage.getItem('props_id')) {
        this.who_state = true;
        this.bell__state = false;
      } else if (!this.props_id && localStorage.getItem('user_id') === localStorage.getItem('props_id')) {
        this.who_state = false;
        this.bell__state = true;
      } else if (localStorage.getItem('user_id') !== localStorage.getItem('props_id')) {
        this.who_state = true;
        this.bell__state = false;
      }
      this.getUserInfo();
      // following 목록
      this.getAllMyFollowing();
      // follower 목록
      this.getAllMyFollower();
      // 전체 포스팅 글 숫자
      // this.getMyWorksCount();
      // 나의 전시관 가지고 오기
      // this.getMyGallery();
      //  즐겨 찾기 목록 가지고 오기
      this.getAllScrapWork();
      // 나의 전체 작품 가지고 오기 
      // this.getMyWorks();
      this.DMpull();
      window.scrollTo(0, 0);


    }
  }
</script>

<style>

</style>