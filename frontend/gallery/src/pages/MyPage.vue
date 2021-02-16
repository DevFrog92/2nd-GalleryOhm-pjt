<template>
  <div class="My_profile_container">
    <div class="profile_wrapper">



      <div class="profile_card profile_card1" :style="{backgroundImage:'url('+this.img_url+')'}">
        <div class="profile_border">
          <h2 class="profile_card_user_name">{{userInfo.user_id}}</h2>
          <div class="profile_icons">
            <i class="profile_icon icon-chat" aria-hidden="true" @click="DMsideopen"></i>
          </div>
        </div>
      </div>



      <div class="profile_about">
        <h2 class="profile_user_name">{{userInfo.user_nickName}} <span class="Mypage__profile_batch">고수</span><span
            class="Mypage__bell" @click="showDm=true"><img src="../assets/images/bell.png" alt=""><span
              class="notification__num" @click="DMsideopen">{{UnreadDm}}</span></span></h2>
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
                <button class="unit__guest unit__btn__guest" type="button"><img src="../assets/images/pencil.png" alt=""
                    class='Guest__dm'></button>
              </div>
            </div>
          </div>
        </div>
        <span v-if="!modifyabout" class="icon-pencil-alt-span" @click="[modifyabout=!modifyabout,modify_state=false]">
          <div class="segment__guest">
            <button class="unit__guest unit__btn__guest" type="button"><img src="../assets/images/pencil.png" alt=""
                class='Guest__dm'></button>
          </div>
        </span>



        <div class="profile_menu_footer">
          <div class="profile_menu">
            <div class="profile_label">더보기</div>
            <div class="profile_spacer"></div>
            <div class="profile_item"><span class="profile_menu_item" data-value="1">갤러리</span></div>
            <div class="profile_item"><span class="profile_menu_item" data-value="2">전체 작품</span></div>
            <div class="profile_item"><span class="profile_menu_item" data-value="3">즐겨찾기</span></div>
            <div class="profile_item"><span class="profile_menu_item" @click="moveSettings">회원정보 수정</span></div>
          </div>
        </div>
      </div>


      <div class="Mypage__dm__list">
        <div class="exit__Mypage__dm" @click="DMsideclose">나가기</div>
        <div class="contact_to_me">
          <div class="MypageUnRead__message">읽지않은서신</div>
          <div class="MypageAll__message">전체서신</div>
          <ul class="Mypage__DMList">
            <div :class="!dm.message_isCheck ? 'Mypage__DM__item Mypage__DM__item__read ' : 'Mypage__DM__item' "
              v-for="(dm,index) of dm_list" :key="index" :data-name='dm.message_id' :data-value="JSON.stringify(dm)">
              <div class="Mypage__DM__item__avatar">
                <img src="../assets/images/user.png" />
              </div>
              <div class="Mypage__DM__item__content">
                <span class="Mypage__DM__item__title">{{dm.message_senderId}}</span>
                <span class="Mypage__DM__item__message">{{dm.message_title}}<br>{{dm.message_sendDate}}</span>
              </div>
              <div class="Mypage__DM__item__option delete Mypage__dm-option" :data-value="dm.message_id"
                @click="DmDelete(dm.message_id)">
                <i class="fas fa-trash"></i>
              </div>
            </div>
          </ul>
        </div>
        <div class="Mypage__showDmContent">
          <div class="Mypage__message__name__cover">
            <div class="sender_name"></div>
          </div>
          <div class="Mypage__message__title__cover">
            <div class="sender_title"></div>
            <div class="Mypage__message__date__tiem">
              <div class="sender_date"></div>
              <div class="sender_time"></div>
            </div>
          </div>

          <div class="Mypage__message__content__cover">
            <div class="sender_content"></div>
          </div>
          <div class="Mypage__message__reply">
            <div class="Mypage__message__reply__titie">
              <label for="response_message_title" class="Mypage__response_title">제목 : </label>
              <input type="text" id="Mypage__response_message_title" v-model="dm_title">
            </div>

            <textarea name="response_message" id="Mypage__response_message" v-model="dm_content"></textarea>
          </div>
          <div class="Mypage__message__footer">
            <div class="segment__mypage">
              <button class="unit__mypage unit__btn__mypage mypage__dm__send" type="button" @click="DM"><img
                  src="../assets/images/paper.png" alt="" class='Mypage__dm'></button>
            </div>
          </div>
        </div>
      </div>

      <div class="DmSendModal">

      </div>



    </div>

    <div class="segment__mypage">
      <div class="unit__mypage unit__btn__mypage move_to_top" type="button"><img src="../assets/images/up-arrow.png"
          alt=""></div>
    </div>

    <div class="second__section">
      <h1>{{userInfo.user_nickName}}의 전시관</h1>
      <div class="Mypage__my_works">
        <div class="Mypage__poster__card1" :data-value="my_gallery_1.gallery_id">
          <div v-if="my_gallery_1.gallery_id" class="Mypage__gallery__entrance_1">입장</div>
          <div v-if="my_gallery_1.gallery_id" class="Mypage__gallery_modify"
            @click="modifyGallery(my_gallery_1.gallery_id)">수정
          </div>
          <div v-if="my_gallery_1.gallery_id" class="Mypage__gallery_delete"
            @click="[delete_gallery_id=my_gallery_1.gallery_id,delete_gallery=true,delete_gallery_name=my_gallery_1.gallery_name]">
            삭제</div>
          <div v-else class="Mypage__plus_gallery" @click="CreateGallery">
            <div class="segment__mypage__add">
              전시관이 없습니다.
              <button class="unit__mypage unit__btn__mypage mypage__add__btn" type="button"><img
                  src="../assets/images/add.png" alt="" class='Mypage__add'></button>
            </div>
          </div>
          <img v-if="my_gallery_1.gallery_id" :src="'data:/image/jpeg;base64,' + my_gallery_poster[0] " alt="">

          <h3>{{my_gallery_1.gallery_name}}</h3>
          <p v-if="my_gallery_1.gallery_id">작가 : {{my_gallery_1.gallery_artistId}}
            {{my_gallery_1.gallery_writeTime.slice(0,10)}} ~</p>
        </div>

        <div class="Mypage__poster_card2" :data-value="my_gallery_2.gallery_id">
          <div v-if="my_gallery_2.gallery_id" class="Mypage__gallery__entrance_2">입장</div>
          <div v-if="my_gallery_2.gallery_id" class="Mypage__gallery_modify"
            @click="modifyGallery(my_gallery_2.gallery_id)">수정
          </div>
          <div v-if="my_gallery_2.gallery_id" class="Mypage__gallery_delete"
            @click="[delete_gallery_id=my_gallery_2.gallery_id,delete_gallery=true,delete_gallery_name=my_gallery_2.gallery_name]">
            삭제</div>
          <div v-else class="Mypage__plus_gallery" @click="CreateGallery">
            <div class="segment__mypage__add">
              전시관이 없습니다.
              <button class="unit__mypage unit__btn__mypage mypage__add__btn" type="button"><img
                  src="../assets/images/add.png" alt="" class='Mypage__add'></button>
            </div>
          </div>
          <img v-if="my_gallery_2.gallery_id" :src="'data:/image/jpeg;base64,' + my_gallery_poster[1] " alt="">
          <h3>{{my_gallery_2.gallery_name}}</h3>
          <p v-if="my_gallery_2.gallery_id">Created By {{my_gallery_2.gallery_artistId}}
            {{my_gallery_2.gallery_writeTime.slice(0,10)}} ~</p>
        </div>
      </div>


    </div>


    <div class="third__section">
      <h1>{{userInfo.user_nickName}}의 작품</h1>
      <div class="third__section__wrapper">

        <!-- season -->
        <div class="Mypage__options">
          <div class="options_wrapper">
            <div class="Mypage__option" :data-value="index" v-for="(item,index) of all_my_works_month_first"
              :key="index">
              <!-- <img :src="'../assets/images/bg'+index+'.jpg'" alt=""> -->
              <div class="shadow"></div>
              <div class="label">
                <div class="icon">
                  <i class="fas fa-walking"></i>
                </div>
                <div class="info">
                  <div class="show__my__pint" @click="show_pint_woks(index,1)">그림보러 가기</div>
                  <div class="main">{{Number(index)}}월</div>
                </div>
              </div>
            </div>
          </div>
          <div class="options_wrapper">

            <div class="Mypage__option num2" :data-value="Number(index)+6"
              v-for="(item,index) of all_my_works_month_second" :key="Number(index)+6">
              <div class="shadow"></div>
              <div class="label">
                <div class="icon">
                  <i class="fas fa-snowflake"></i>
                </div>
                <div class="info">
                  <div class="show__my__pint" @click="show_pint_woks(index,2)">그림보러 가기</div>
                  <div class="main">{{String(Number(index)+6)}}월</div>
                </div>
              </div>
            </div>

          </div>

        </div>



        <!-- season -->

        <div class="pinter__side">
          <div class="pinter_item-lists">
            <div class="pinter_grid" v-for="(img, i) in pinter_image" :key="i" :data-value="img.work_id">
              <img :src="'data:image/jpeg;base64,'+img.work_piece" alt="DB이미지" />
              <div class="pinter_grid__body" :data-value="img.work_id">
                <div class="pinter_grid__bod__modify">수정</div>
                <div class="pinter_grid__body__delete">삭제</div>
              </div>
            </div>
            <div class="exit_side">나가기</div>
          </div>
        </div>
      </div>
    </div>



    <div class="forth__section">
      <h1 class="outer__wrapper__title">{{userInfo.user_nickName}}의 즐겨찾기</h1>
      <div class="outer-wrapper" v-if="!scrap_state">
        <div class="scroll-wrapper">
          <div class="scroll-slide" v-for="(item,index) of scrap_list" :key="index">
            <img class="scrap__image" :data-value='item.work_id' :src="'data:/image/jpeg;base64,'+item.work_piece"
              alt="">
          </div>
        </div>
      </div>
      <div v-else>
        <h2 class="no__scrap"> 스크랩한 작품이 없습니다.</h2>
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
                <img src="../assets/images/user.png" />
              </div>
              <div class="notifications__item__content">
                <span class="notifications__item__title">{{people}}</span>
                <!-- <span class="notifications__item__message">Just started following you</span> -->
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
                <img src="../assets/images/user.png" />
              </div>
              <div class="notifications__item__content">
                <span class="notifications__item__title">{{people}}</span>
                <!-- <span class="notifications__item__message">Just started following you</span> -->
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

    <Modal v-if="delete_gallery" @close="delete_gallery= false">
      <div slot="header">
        <h3>
          전시관 삭제
        </h3>
        <i class="fas fa-times closeModalBtn" @click="delete_gallery=false"></i>
      </div>

      <div slot="body" v-if="!delete_gallery_state">
        <h3>전시관을 삭제하시겠습니까?</h3>
        <p>삭제할 전시관 이름 : {{delete_gallery_name}}</p>
      </div>
      <div slot="body" v-else>
        <h3>전시관이 성공적으로 삭제되었습니다.</h3>
        <p>삭제된 전시관 이름 : {{delete_gallery_name}}</p>
      </div>

      <div slot="footer" v-if="!delete_gallery_state">
        <button class="registerGallery" @click="deleteGallery(delete_gallery_id)">삭제하기</button>
        <button class="closeRegisterGalleryBtn" @click="delete_gallery=false">나가기</button>
      </div>
      <div slot="footer" v-else>
        <button class="closeRegisterGalleryBtn" @click="refresh">나가기</button>
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
        img_url: localStorage.getItem('user_profile'),
        userInfo: {},
        test_url: '../assets/images/1.png',
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

          '01': [],
          '02': [],
          '03': [],
          '04': [],
          '05': [],
          '06': []
        },
        showPinterSide: false,
        showDm: false,
        showDMSide: false,
        pinter_image: [],
        UnreadDm: 0,
        UnreadDmList: [],
        scrap_state: false,
      }
    },
    components: {
      Modal
    },
    props: ["props_id"],
    methods: {
      show_pint_woks(index, season) {
        if (season == 1) {
          this.pinter_image = this.all_my_works_month_first[index]
        } else {
          this.pinter_image = this.all_my_works_month_second[index]
        }
        console.log(this.pinter_image, 'show')

      },
      DMsideopen() {
        const DMList = document.querySelector('.Mypage__dm__list');
        DMList.classList.add('show_dm_side');
        init.DMModal();
      },
      DMsideclose() {
        const DMList = document.querySelector('.Mypage__dm__list');
        DMList.classList.remove('show_dm_side');
      },
      DmRead(message_id) {
        console.log('Dm Read', message_id);
      },
      DmDelete(id) {
        // const DMList = document.querySelector('.DMList')
        setTimeout(() => {
          console.log('DM Delete', id);
          const DMLIST = document.querySelectorAll('.Mypage__DM__item')
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
      CreateGallery() {
        this.$router.push('/creategallery');
      },
      deleteGallery(gallery_id) {
        console.log('지운다')
        http.post('/gallery/deleteArtistGallery/' + gallery_id)
          .then(response => {
            this.getMyGallery();
            console.log('response.data', response.data);
            this.delete_gallery_state = true;

          })
      },
      modifyGallery(gallery_id) {
        this.$router.push({
          name: 'DragAndDrop',
          params: {
            props_id: gallery_id
          }
        });
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
        console.log('Show me follower')
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
        formData.append('user_id', localStorage.getItem('user_id'));
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
      },
      DMpull() {
        http.post('/message/getAllMyReceiveMessage', localStorage.getItem('user_id'))
          .then(response => {
            console.log('Get all my dm list', response.data);
            this.dm_list = response.data;
            for (let dm of this.dm_list) {
              if (dm.message_isCheck == 1) {
                this.UnreadDm += 1;
                this.UnreadDmList.push(dm);
              }
            }
            console.log('총 읽지 않은 디엠', this.UnreadDm)
          })
      },
      galleryRender() {
        this.$router.push('/galleryrender');
      },
      moveSettings() {
        this.$router.push('/settings');
      },
      getAllMyFollowing() {
        http.get('/follow/getAllMyFollowing', {
            params: {
              user_id: localStorage.getItem('user_id')
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
              user_id: localStorage.getItem('user_id')
            }
          })
          .then(response => {
            console.log('Get all my followers', response.data);
            this.followers = response.data;
          })
      },
      getMyWorksCount() {
        http.get('/work/getMyWorksCount/' + localStorage.getItem('user_id'))
          .then(response => {
            console.log('Get all my works count', response.data);

            this.posts = response.data;
          })
      },
      getMyGallery() {
        http.get('/gallery/getMyGallery/' + localStorage.getItem('user_id'))
          .then(response => {
            console.log('Get my gallery', response.data);
            if (response.data.length !== 2) {
              if (response.data.length === 1) {
                this.my_gallery_list = response.data;
                this.my_gallery_1 = this.my_gallery_list[0];
                this.getMyGalleryDetail(this.my_gallery_1.gallery_id);
                this.my_gallery_2 = {
                  "gallery_id": '',
                  "gallery_name": "",
                  "gallery_desc": "",
                  "gallery_artistId": "",
                  "gallery_writeTime": "",
                  "gallery_mainWorkId": '',
                  "gallery_footPrint": ''
                }
              } else {
                this.my_gallery_list = response.data;
                this.my_gallery_1 = {
                  "gallery_id": '',
                  "gallery_name": "",
                  "gallery_desc": "",
                  "gallery_artistId": "",
                  "gallery_writeTime": "",
                  "gallery_mainWorkId": '',
                  "gallery_footPrint": ''
                }
                this.my_gallery_2 = {
                  "gallery_id": '',
                  "gallery_name": "",
                  "gallery_desc": "",
                  "gallery_artistId": "",
                  "gallery_writeTime": "",
                  "gallery_mainWorkId": '',
                  "gallery_footPrint": ''
                }
              }
            } else {
              this.my_gallery_list = response.data;
              this.my_gallery_1 = this.my_gallery_list[0];
              this.getMyGalleryDetail(this.my_gallery_1.gallery_id);
              this.my_gallery_2 = this.my_gallery_list[1];
              this.getMyGalleryDetail(this.my_gallery_2.gallery_id);
            }
          })
      },
      getAllScrapWork() {
        http.get('/work/getAllScrapWork/' + localStorage.getItem('user_id'))
          .then(response => {
            console.log('Get all scrap', response.data);
            this.scrap_list = response.data;
            if (!this.scrap_list.length) {
              this.scrap_state = true;
              console.log('스트랩한 작품이 없습니다.')
            }

          })
      },
      getMyWorks() {
        http.get('/work/getMyWorks/' + localStorage.getItem('user_id'))
          .then(response => {

            console.log('Create year category', this.all_my_works_year);
            for (let item of response.data) {
              let itemMonth = item.work_uploadDate.slice(5, 7)
              if (Object.keys(this.all_my_works_month_first).includes(itemMonth)) {
                console.log('itemMonth', itemMonth)
                this.all_my_works_month_first[itemMonth].push(item);
              } else {
                this.all_my_works_month_second[Number(itemMonth) - 6].push(item);
              }
            }
            console.log(this.all_my_works_month_first, this.all_my_works_month_second)
          })
      },
      getMyGalleryDetail(gallery_id) {
        http.get('http://localhost:7080/artGallery/api/gallery/getArtistGallery/' + gallery_id)
          .then(response => {
            console.log(response.data, 'gallery_detail');
            this.my_gallery_poster.push(response.data[0].work_piece);
          })
      },
      getUserInfo() {
        http.get('/user/getUserInfo', {
            params: {
              user_id: localStorage.getItem('user_id')
            }
          })
          .then(response => {
            console.log('Get user Info', response.data);
            this.userInfo = response.data;

            const userAbout = document.querySelector('.artist_about');
            userAbout.innerHTML = this.userInfo.user_about;
            this.user_about = this.userInfo.user_about;

            if (response.data.user_profile !== null) {
              localStorage.setItem("user_profile", "data:/image/jpeg;base64," + this.userInfo.user_profile);
              this.img_url = "data:/image/jpeg;base64," + this.userInfo.user_profile;
            }
          })
      }
    },
    mounted() {
      setTimeout(() => {
        init.init();
      }, 1000)
    },
    created() {
      this.getUserInfo();
      // following 목록
      this.getAllMyFollowing();
      // follower 목록
      this.getAllMyFollower();
      // 전체 포스팅 글 숫자
      this.getMyWorksCount();
      // 나의 전시관 가지고 오기
      this.getMyGallery();
      //  즐겨 찾기 목록 가지고 오기
      this.getAllScrapWork();
      // 나의 전체 작품 가지고 오기 
      this.getMyWorks();
      this.DMpull();
      window.scrollTo(0, 0);


    }
  }
</script>

<style>

</style>