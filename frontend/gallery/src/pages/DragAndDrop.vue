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
              
            </div>
          </div>
        </div>
      </div>


    </div>
    <div class="sub_all_works_container">

      <div class="empty_block" >
        <div class="empty">
          <div class="fill">
          </div>
        </div>
      </div>
      <div class="empty_block" v-for="(item,index) of work_list" :key="index">
        <div class="empty">
          <div class="fill" :data-value="item.work_id" draggable="true"
            :style="{backgroundImage:'url('+item.work_piece+')'}">
          </div>
        </div>
      </div>




    </div>
    

<div class="save_btn_container">
                 <div class="gallery_list_btn" @click="clickEventHandler"><span>전시관 등록</span></div>
              </div>
    <!-- <button class="gallery_list_btn" @click="clickEventHandler">전시관 등록</button> -->

    <Modal v-if="showModal_register" @close="showModal_register = false">
      <!--
      you can use custom content here to overwrite
      default content
    -->

      <div slot="header">
        <h3>
          전시관 등록
        </h3>
        <i class="fas fa-times closeModalBtn" @click="showModal_register=false"></i>
      </div>

      <div slot="body" v-if="!register_state">
        <div class="name_of_gallery">
          <label for="gallery_name"></label>
          <input type="text" id="gallery_name" placeholder="전시관 이름을 작성해 주세요." v-model="gallery_name">
        </div>
        <div class="desc_of_gallery" >
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
        <button class="closeRegisterGalleryBtn" @click="showModal_register=false">나가기</button>
      </div>
      <div slot="footer" v-else>
        <button class="closeRegisterGalleryBtn" @click="goToMypage">마이페이지로 이동</button>
      </div>
    </Modal>
    <Modal v-if="showModal_modify" @close="showModal_modify= false">
     <div slot="header">
        <h3>
          전시관 수정
        </h3>
        <i class="fas fa-times closeModalBtn" @click="showModal_modify=false"></i>
      </div>

      <div slot="body" v-if="modify_state">
        <div class="name_of_gallery">
          <label for="gallery_name"></label>
          <input type="text" id="gallery_name" placeholder="전시관 이름을 작성해 주세요." v-model="gallery_info.gallery_name">
        </div>
        <div class="desc_of_gallery" >
          <label for="gallery_desc"></label>
          <textarea type="textarea" id="gallery_desc" placeholder="전시관 소개글을 작성해 주세요." v-model="gallery_info.gallery_desc"></textarea>
        </div>
      </div>
      <div slot="body" v-else>
        <h3>전시관이 성공적으로 수정되었습니다.</h3>
        <p>수정된 전시관 이름 : {{gallery_info.gallery_name}}</p>
      </div>

      <div slot="footer" v-if="modify_state">
        <button class="registerGallery" @click="modifyArtistGallery">등록하기</button>
        <button class="closeRegisterGalleryBtn" @click="showModal_modify=false">나가기</button>
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
        showModal_register: false,
        gallery_desc: "",
        gallery_name: "",
        register_state: false,
        currIndex:0,
        gallery_works:[],
        modify_state:false,
        gallery_info:[],
        showModal_modify:false,
      }
    },
    components: {
      Modal
    },
    props:['props_id'],
    mounted() {
      setTimeout(() => {
        init.init(this.gallery_works,this.gallery_works.length);
      }, 1000)
    },
    created() {
      this.getMyWorks();
      if(this.props_id !== undefined){
        this.getGalleryInfo();
        this.getGallery();
      }
    },
    methods: {
      getGallery(){
        http.get('/gallery/getGallery/'+this.props_id)
        .then(response =>{
          this.gallery_info = response.data;
          console.log('this gallery info',this.gallery_info)
        })
      },
      getGalleryInfo(){
        http.get('/gallery/getArtistGallery/'+this.props_id)
        .then(response =>{
          this.gallery_works = response.data;
          console.log('gallery works',this.gallery_works)
        })  
      },
      getMyWorks(){
        http.get(`/work/getMyWorks/${localStorage.getItem('user_id')}`)
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
      clickEventHandler(e) {
        e.preventDefault();
        const emptyContainer = document.querySelectorAll('.sub_carousel__slider .empty');
        for (const emptyItem of emptyContainer) {
          let item = emptyItem.childNodes;
          console.log(item)
          if (item[1]) {
            this.galleryIdArray.push(item[1].dataset.value);
          }else if(item[0]){
            this.galleryIdArray.push(item[0].dataset.value);
          }
        }
        console.log(this.galleryIdArray)
        if(this.gallery_works.length >0){
          this.modify_state = true;
          this.showModal_modify = true;
        }else{
          this.showModal_register =true;
        }
      },
      modifyArtistGallery(){
        const formdata = new FormData();
        formdata.append('gallery_artistId', localStorage.getItem('user_id'));
        formdata.append('gallery_desc', this.gallery_info.gallery_desc);
        formdata.append('gallery_mainWorkId', this.galleryIdArray[0]);
        formdata.append('gallery_id', this.gallery_info.gallery_id);
        formdata.append('gallery_name', this.gallery_info.gallery_name);
        formdata.append('gallery_workIdList', this.galleryIdArray);
        http.post('/gallery/modifyArtistGallery',formdata)
        .then(response =>{
          console.log('succ modify gallery',response.data);
          this.modify_state = false;
        })
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
      goToMypage(){
        this.$router.push('/mypage');
      },
      galleryrender(){
        this.$router.push({name:'GalleryRenderPage',params:{props_id:this.gallery_info.gallery_id}});
      }
    }
  }
</script>

<style>

</style>