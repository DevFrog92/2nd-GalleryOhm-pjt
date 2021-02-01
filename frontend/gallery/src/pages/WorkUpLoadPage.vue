<template>
    <body class="upload_picture_page">
  <div class="upload_picture_page_middle">
    <div class="wrapPicture">
      <div class="previewPicture">
        <img v-if="img_url" :src="img_url" alt="">
      </div>
      <div class="pictureInfo">

        <div class="picture_Title info_input">
          <label for="pictureTitle">작품명</label><br>
          <input type="text" id="pictureTitle" placeholder="작품의 제목을 입력해주세요." v-model="work_title">
        </div>


        <div class="picture_Age ">
          <p>작품 연령 선택</p>
          <label><input type="radio" name="age" value="0" @click="rating">전체관람</label>
          <label class="gallery19"><input type="radio" name="age" value="19" @click="rating">19금</label>
        </div>


        <div class="picture_About info_input">
          <label for="pictureAbout">작품소개</label><br>
          <textarea name="pictureAbout" id="pictureAbout" placeholder="작품에 대한 설명을 적어주세요." v-model="work_desc"></textarea>
        </div>

        <div class="picture_Tool info_input">
          <label for="pictureTool">작품그린툴</label><br>
          <textarea name="pictureTool" id="pictureTool" placeholder="작품그린툴(비공개시 빈칸으로 남겨주세요)"></textarea>
        </div>

        <div class="picture_Tag info_input">
          <label for="pictureTag">해쉬태그</label>
          <div class="hashtag">
            <p>* Separate your tags with a comma</p>
            <div class="tags-input"></div>
          </div>
        </div>

        <div class="picture_Upload info_input">
          <label for="pictureUpload">그림업로드</label><br>
           <input type="file" ref="work_piece" hidden @change="onChangeImage"/><br/>
          <button @click.prevent="onClickUploadImage">그림업로드</button><br>



        </div>
        <div class="uploadBtn">
          <button @click="addWork">UPLOAD</button>
          <button>뒤로가기</button>
        </div>

      </div>
    </div>
  </div>


</body>
</template>

<script>
import '../assets/css/WorkUpLoadPage.css'
export default {
  components: {},
  created() {},
  watch: {},
  computed: {
  },
  methods: {
    addWork: function() {
      // console.log(this.$refs.work_piece.files);
      this.work_piece = this.$refs.work_piece.files[0];
      // 업로드한 파일로 부터 url 을 생성할 수 있다.
      this.$store.dispatch("addWork",{
        // work_artistId: localStorage.getItem('user_id'), //로컬스토리지 아이디 받아오기
        work_artistId: "ohj",
        work_title: this.work_title,
        work_desc:this.work_desc,
        work_piece:this.work_piece,
        work_rating: this.work_rating
      })
      .then(() => {
        // console.log(data);
      });
    },
    onChangeImage(event){
      const imgFile = event.target.files[0];
      this.img_url = URL.createObjectURL(imgFile);
    },
    onClickUploadImage(){
      this.$refs.work_piece.click();
    },
    rating(event){
      this.work_rating = event.target.value;
    }
  },
  data: () => {
    return {
      work_artistId:"",
      work_title:"",
      work_desc:"",
      work_piece:"",
      img_url:'',
    };
  },
};
</script>

<style >
</style>