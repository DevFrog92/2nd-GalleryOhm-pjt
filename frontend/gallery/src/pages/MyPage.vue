<template>
  <div class="My_profile_container">
    <div class="profile_wrapper">




      <div class="profile_card profile_card1" :style="{backgroundImage:'url('+this.img_url+')'}">
        <div class="profile_border">
          <h2 class="profile_card_user_name">{{userInfo.user_id}}</h2>
          <div class="profile_icons">
            <i class="profile_icon icon-instagram-1" aria-hidden="true"></i>
            <i class="profile_icon icon-facebook-1" aria-hidden="true"></i>
            <i class="profile_icon icon-bookmark" aria-hidden="true"></i>
            <i class="profile_icon icon-chat" aria-hidden="true" @click="DMsideopen"></i>
          </div>
        </div>
      </div>




      <div class="profile_about">
        <h2 class="profile_user_name">{{userInfo.user_id}} <span class="profile_batch">PRO</span><span
            @click="showDm=true">Bell</span></h2>
        <div class="follow">
          <span>Post : {{posts}}</span>
          <span @click="following_view">Following : {{followings.length}}</span>
          <span @click="follower_view">Follower : {{followers.length}}</span>
          <!-- <span  @close="showModal=true">Follower : {{followers.length}}</span> -->
        </div>
        <div class="artist_about_div">
          <p class="artist_about">{{this.user_about}}</p>
          <div v-if="modifyabout" class="modal_modify_about">
            <textarea name="artist_about_modify" id="artist_about_modify" cols="30" rows="10"
              v-model="user_about"></textarea>
            <div class="modify_btn" @click='registerUserAbout'>Edit</div>
          </div>
        </div>
        <span v-if="!modifyabout" class="icon-pencil-alt-span" @click="[modifyabout=!modifyabout,modify_state=false]">
          <i class="icon-pencil-alt">pencile</i></span>



        <div class="profile_menu_footer">
          <div class="profile_menu">
            <div class="profile_label">More info</div>
            <div class="profile_spacer"></div>
            <div class="profile_item"><span class="profile_menu_item" data-value="1">Twitter</span></div>
            <div class="profile_item"><span class="profile_menu_item" data-value="2">Behance</span></div>
            <div class="profile_item"><span class="profile_menu_item" data-value="3">MixCloud</span></div>
            <div class="profile_item"><span class="profile_menu_item" data-value="4">Scrap</span></div>
            <div class="profile_item"><span class="profile_menu_item" @click="moveSettings">Settings</span></div>
          </div>
        </div>
      </div>


      <div class="dm__list">
        <div @click="DMsideclose">exit</div>
        <div class="contact_to_me">
          <ul class="DMList">
            <div :class="!dm.message_isCheck ? 'DM__item DM__item__read ' : 'DM__item' " v-for="(dm,index) of dm_list"
              :key="index" :data-name='dm.message_id' :data-value="JSON.stringify(dm)">
              <div class="DM__item__avatar">
                <img src="../assets/images/user.png" />
              </div>
              <div class="DM__item__content">
                <span class="DM__item__title">{{dm.message_senderId}}</span>
                <span class="DM__item__message">{{dm.message_title}} -{{dm.message_sendDate}}</span>
              </div>
              <div class="DM__item__option delete dm-option" :data-value="dm.message_id"
                @click="DmDelete(dm.message_id)">
                <i class="fas fa-trash"></i>
              </div>
            </div>
          </ul>
        </div>
        <div class="showDmContent">
          <div class="message__name__cover">
            <div class="sender_name"></div>
          </div>
          <div class="message__title__cover">
            <div class="sender_title"></div>
            <div class="message__date__tiem">
              <div class="sender_date"></div>
              <div class="sender_time"></div>
            </div>
          </div>

          <div class="message__content__cover">
            <div class="sender_content"></div>
          </div>
          <div class="message__reply">
            <div class="message__reply__titie">
              <label for="response_message_title" class="response_title">제목 : </label>
              <input type="text" id="response_message_title" v-model="dm_title">
            </div>

            <textarea name="response_message" id="response_message" v-model="dm_content"></textarea>
          </div>
          <div class="send_btn" @click="DM">답장 보내기</div>
        </div>
      </div>

      <div class="DmSendModal">

      </div>



    </div>
    <div class="move_to_top">TOP</div>

    <div class="second__section">
      <h1>{{userInfo.user_nickName}}의 전시관</h1>
      <div class="my_works">
        <div class="poster_card1" :data-value="my_gallery_1.gallery_id">
          <div v-if="my_gallery_1.gallery_id" class="gallery_modify" @click="modifyGallery(my_gallery_1.gallery_id)">수정
          </div>
          <div v-if="my_gallery_1.gallery_id" class="gallery_delete"
            @click="[delete_gallery_id=my_gallery_1.gallery_id,delete_gallery=true,delete_gallery_name=my_gallery_1.gallery_name]">
            삭제</div>
          <div v-else class="plus_gallery" @click="CreateGallery">+</div>
          <img v-if="my_gallery_1.gallery_id" :src="'data:/image/jpeg;base64,' + my_gallery_poster[0] " alt="">
          <img v-else src="../assets/images/1.png" alt="">

          <h3>{{my_gallery_1.gallery_name}}</h3>
          <p v-if="my_gallery_1.gallery_id">Created By {{my_gallery_1.gallery_artistId}}
            {{my_gallery_1.gallery_writeTime.slice(0,10)}} ~</p>
        </div>

        <div class="poster_card2" :data-value="my_gallery_2.gallery_id">
          <div v-if="my_gallery_2.gallery_id" class="gallery_modify" @click="modifyGallery(my_gallery_2.gallery_id)">수정
          </div>
          <div v-if="my_gallery_2.gallery_id" class="gallery_delete"
            @click="[delete_gallery_id=my_gallery_2.gallery_id,delete_gallery=true,delete_gallery_name=my_gallery_2.gallery_name]">
            삭제</div>
          <div v-else class="plus_gallery" @click="CreateGallery">+</div>
          <img v-if="my_gallery_2.gallery_id" :src="'data:/image/jpeg;base64,' + my_gallery_poster[1] " alt="">
          <img v-else src="../assets/images/1.png" alt="">
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
        <div class="options">
          <div class="options_wrapper">
            <div class="option" :data-value="index" v-for="(item,index) of all_my_works_month_first" :key="index"
              style="--optionBackground:url(https://66.media.tumblr.com/6fb397d822f4f9f4596dff2085b18f2e/tumblr_nzsvb4p6xS1qho82wo1_1280.jpg);">
              <div class="shadow"></div>
              <div class="label">
                <div class="icon">
                  <i class="fas fa-walking"></i>
                </div>
                <div class="info">
                  <div class="show__my__pint" @click="show_pint_woks(index,1)">그림보러 가기</div>
                  <div class="main">{{index}}</div>
                  <div class="sub">Omuke trughte a otufta</div>
                </div>
              </div>
            </div>
          </div>
          <div class="options_wrapper">

            <div class="option" :data-value="index" v-for="(item,index) of all_my_works_month_second" :key="index"
              style="--optionBackground:url(https://66.media.tumblr.com/8b69cdde47aa952e4176b4200052abf4/tumblr_o51p7mFFF21qho82wo1_1280.jpg);">
              <div class="shadow"></div>
              <div class="label">
                <div class="icon">
                  <i class="fas fa-snowflake"></i>
                </div>
                <div class="info">
                  <div class="show__my__pint" @click="show_pint_woks(index,2)">그림보러 가기</div>
                  <div class="main">{{index}}</div>
                  <div class="sub">Omuke trughte a otufta</div>
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
                  <h1 >수정</h1>
                  <h1 >삭제</h1>
                </div>
              </div>
          <div class="exit_side">X</div>
          </div>
        </div>
      </div>
    </div>



    <div class="forth__section">
      <h1>{{userInfo.user_nickName}}의 즐겨찾기</h1>
      <div class="outer-wrapper">
        <div class="scroll-wrapper">
          <div class="scroll-slide" v-for="(item,index) of scrap_list" :key="index">
            <img :src="'data:/image/jpeg;base64,'+item.work_piece" alt="">
          </div>
        </div>
      </div>

    </div>



    <Modal v-if="showModal" @close="showModal = false">
      <div slot="header">
        <h3>
        </h3>
        <i class="fas fa-times closeModalBtn" @click="[showModal=false,getAllMyFollowing()]"></i>
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
                <img src="../assets/images/user.png" />
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
        <button class="closeRegisterGalleryBtn" @click="[showModal=false,getAllMyFollowing()]">나가기</button>
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
        pinter_image:[],

      }
    },
    components: {
      Modal
    },
    props: ["props_id"],
    methods: {
      show_pint_woks(index,season){
        if(season == 1){
          this.pinter_image = this.all_my_works_month_first[index]
        }else{
          this.pinter_image = this.all_my_works_month_second[index]
        }
        console.log(this.pinter_image,'show')

      },
      DMsideopen() {
        const DMList = document.querySelector('.dm__list');
        DMList.classList.add('show_dm_side');
        init.DMModal();
      },
      DMsideclose() {
        const DMList = document.querySelector('.dm__list');
        DMList.classList.remove('show_dm_side');
      },
      DmRead(message_id) {
        console.log('Dm Read', message_id);
      },
      DmDelete(id) {
        // const DMList = document.querySelector('.DMList')
        setTimeout(() => {
          console.log('DM Delete', id);
          const DMLIST = document.querySelectorAll('.DM__item')
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
            // for (let item of this.dm_list) {
            //   let dmContent = item.message_content.replace(/(?:\r\n|\r|\n)/g, '<br/>');
            //   dmItem.innerHTML = "Sender user : " + item.message_senderId + " Content : " + dmContent;
            // }
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
          })
      },
      getMyWorks() {
        http.get('/work/getMyWorks/' + localStorage.getItem('user_id'))
          .then(response => {

            console.log('Create year category', this.all_my_works_year);
            for (let item of response.data) {
              let itemMonth = item.work_uploadDate.slice(5, 7)
              if(Object.keys(this.all_my_works_month_first).includes(itemMonth)){
                console.log('itemMonth', itemMonth)
              this.all_my_works_month_first[itemMonth].push(item);
              }else{
                this.all_my_works_month_second[itemMonth].push(item);
              }
            }
            console.log(this.all_my_works_month_first,this.all_my_works_month_second)
          })
      },
      getMyGalleryDetail(gallery_id) {
        http.get('http://localhost:7080/artGallery/api/gallery/getArtistGallery/' + gallery_id)
          .then(response => {
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
              // DB에서 응답 받은 이미지 파일을 다시 파일 형태로 변환 -> settings에서 필요
              // const byteCharacters = atob(this.userInfo.user_profile);
              // const byteNumbers = new Array(byteCharacters.length);
              // for (let i = 0; i < byteCharacters.length; i++) {
              //   byteNumbers[i] = byteCharacters.charCodeAt(i);
              // }
              // const byteArray = new Uint8Array(byteNumbers);
              // const blob = new Blob([byteArray], {
              //   type: 'image/jpeg'
              // });
              // const file = new File([blob], 'profile')
              // // console.log(file);
              // this.userInfo.user_profile = file;
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