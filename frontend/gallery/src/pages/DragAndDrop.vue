<template>
  <div class="sub_create_gallery_wrap">
    <div class="sub_preview_gallery_container">
      <div class="sub_gallery__render">
        <div class="sub_main__carousel">
          <div class="sub_carousel__body">
            <div class="sub_carousel__prev"><i class="icon-left-open"></i></div>
            <div class="sub_carousel__next"><i class="icon-right-open"></i></div>
            <div class="sub_carousel__slider">
              <!--              -->
              <div class="sub_carousel__slider__item">
                <div class="sub_item__3d-frame">
                  <div class="sub_item__3d-frame__box sub_item__3d-frame__box--front">
                    <div class="empty">
                    </div>
                  </div>
                  <div class="sub_item__3d-frame__box sub_item__3d-frame__box--left"></div>
                  <div class="sub_item__3d-frame__box sub_item__3d-frame__box--right"> </div>
                </div>
                <div class="sub_item__title">
                  <h1></h1>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>


    </div>
    <div class="sub_all_works_container">

      <div class="empty_block" v-for="(item,index) of work_list" :key="index">
        <div class="empty">
          <div class="fill" :data-value="item.work_id" draggable="true"
            :style="{backgroundImage:'url('+item.work_piece+')'}">
          </div>
        </div>
      </div>




    </div>
    
    <a href="#" @click="plusDivs(-1)">prev</a>
    <a href="#" @click="plusDivs(1)">next</a>

    <button class="gallery_list_btn" @click="clickEventHandler">전시관 등록</button>

    <Modal v-if="showModal" @close="showModal = false">
      <!--
      you can use custom content here to overwrite
      default content
    -->
      <div slot="header">
        <h3>
          전시관 등록
        </h3>
        <i class="fas fa-times closeModalBtn" @click="showModal=false"></i>
      </div>

      <div slot="body" v-if="!register_state">
        <div class="name_of_gallery">
          <label for="gallery_name"></label>
          <input type="text" id="gallery_name" placeholder="전시관 이름을 작성해 주세요." v-model="gallery_name">
        </div>
        <div class="desc_of_gallery" v-if="!register_state">
          <label for="gallery_desc"></label>
          <textarea type="textarea" id="gallery_desc" placeholder="전시관 소개글을 작성해 주세요." v-model="gallery_desc"></textarea>
        </div>
      </div>

      <div slot="body" v-else>
        <h3>전시관이 성공적으로 등록되었습니다.</h3>
        <p>생성된 전시관 이름 : {{gallery_name}}</p>
      </div>

      <div slot="footer" v-if="!register_state">
        <button class="registerGallery" @click="makeGallery">등록하기</button>
        <button class="closeRegisterGalleryBtn" @click="showModal=false">나가기</button>
      </div>
      <div slot="footer" v-else>
        <button class="registerGallery" @click="galleryrender">전시관으로 이동</button>
        <button class="closeRegisterGalleryBtn" @click="goToMypage">마이페이지로 이동</button>
      </div>
    </Modal>
  </div>



</template>

<script>
  import '../assets/css/DragAndDrop.css'
  import '../assets/fontello/css/fontello.css'
  import init from '../assets/js/DragAndDrop'
  import http from '../api/http'
  import Modal from '../pages/Modal'

  export default {
    data() {
      return {
        work_list: [],
        galleryIdArray: [],
        showModal: false,
        gallery_desc: "",
        gallery_name: "",
        register_state: false,
        currIndex:0,
      }
    },
    components: {
      Modal
    },
    mounted() {
      setTimeout(() => {
        init.init();
      }, 1000)
    },
    created() {
      const user_id = localStorage.getItem('user_id')
      return http.get(`/work/getMyWorks/${user_id}`)
        .then(response => {
          const workList = response.data;
          console.log('worklist',workList);
          for (let i = 0; i < workList.length; i++) {
            workList[i].work_piece = "data:image/jpeg;base64," + workList[i].work_piece;
          }
          this.work_list = workList;
          console.log(workList);
        })
        .catch((err) => {
          console.log(err);
        })
    },
    methods: {
      clickEventHandler(e) {
        e.preventDefault();
        const emptyContainer = document.querySelectorAll('.sub_carousel__slider .empty');
        for (const emptyItem of emptyContainer) {
          let item = emptyItem.childNodes;
          console.log(item);
          if (item[0]) {
            this.galleryIdArray.push(item[0].dataset.value);
          }
        }
        this.showModal = !this.showModal;
      },
      makeGallery() {
        const formdata = new FormData();
        formdata.append('gallery_artistId', localStorage.getItem('user_id'));
        formdata.append('gallery_desc', this.gallery_desc);
        formdata.append('gallery_mainWorkId', this.galleryIdArray[0]);
        formdata.append('gallery_name', this.gallery_name);
        formdata.append('gallery_workIdList', this.galleryIdArray);
        console.log(this.galleryIdArray,'array');
        http.post('/gallery/addArtistGallery', formdata)
          .then(response => {
            console.log(response.data);
            this.register_state = true;
          })
          .catch(err => {
            console.log(err);
          })
      },
      moveTo(index){

        // TODO: 슬라이더 만들어야 한다. 추가적으로 전시관에서 사진 확대 기능 추가해야 한다.


          const  items = document.querySelectorAll('.empty_block');
          const slider = document.querySelector('.sub_all_works_container');

          slider.style.width = 150*items.length + "px";
          console.log(150*items.length + "px");
          console.log(index);

          // console.log("move",index);
          // if(index < 1) index = items.length;
          // if(index > items.length) index = 1;
          // this.currIndex = index;
          // slider.style.transform = "translate3d(" + ((index * -width) + (width / 2) + window.innerWidth / 2) + "px, 0, 0)";
      },
      goToMypage(){
        this.$router.push('/mypage');
      },
      galleryrender(){
        this.$router.push('/galleryrender');
      }
    }
  }
</script>

<style>

</style>