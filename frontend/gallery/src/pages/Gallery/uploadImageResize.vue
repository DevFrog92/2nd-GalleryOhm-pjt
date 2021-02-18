<template>
  <body class="upload_picture_page" ondragstart="return false">
    <!-- <div class="upload_picture_page_middle"> -->
    <div class="wrapPicture">
      <div class="previewPicture">
        <p class="preview_pic">작품 미리보기</p>

        <image-uploader
          ref="work_piece"
          :preview="true"
          :className="['fileinput', { 'fileinput--loaded': hasImage }]"
          capture="environment"
          :debug="1"
          doNotResize="gif"
          :autoRotate="true"
          outputFormat="blob"
          @input="setImage"
        >
          <label for="fileInput" slot="upload-label">
            <span class="upload_caption">{{
              hasImage ? "작품 변경" : "작품 선택"
            }}</span>
          </label>
        </image-uploader>
      </div>

      <div class="pictureInfo">
        <div class="picture_Title info_input">
          <label for="pictureTitle">작품명</label><br />
          <input
            type="text"
            id="pictureTitle"
            placeholder="작품의 제목을 입력해주세요."
            v-model="work_title"
          />
        </div>

        <div class="picture_Age">
          <p>작품 연령 선택</p>
          <label
            ><input
              type="radio"
              name="age"
              value="0"
              required
              @click="rating"
            />전체관람</label
          >
          <label class="gallery19"
            ><input
              type="radio"
              name="age"
              required
              value="19"
              @click="rating"
            />19금</label
          >
        </div>

        <div class="picture_About info_input">
          <label for="pictureAbout">작품소개</label><br />
          <textarea
            name="pictureAbout"
            id="pictureAbout"
            placeholder="작품에 대한 설명을 적어주세요."
            v-model="work_desc"
          ></textarea>
        </div>

        <div class="picture_Tool info_input">
          <label for="pictureTool">작품그린툴</label><br />
          <textarea
            name="pictureTool"
            id="pictureTool"
            placeholder="작품그린툴(비공개시 빈칸으로 남겨주세요)"
            v-model="work_tool"
          ></textarea>
        </div>

        <div class="picture_Tag info_input">
          <label for="pictureTag">해쉬태그</label>
          <div class="hashtag">
            <p>* 태그는 ,(쉼표)를 기준으로 구분됩니다.</p>
            <!-- <textarea
              name="hashtagList"
              id="hashtagList"
              v-model="hashtag_list"
            ></textarea> -->
            <div class="tags-input"></div>
          </div>
        </div>

        <div class="upload_btn_container1">
          <div class="uploadBtns1">
            <!-- <div class="uploadBtn1" v-if="mode" @click="addWork" data-display="작품수정"></div> -->
            <div class="upload_art_button" v-if="mode" @click="addWork">
              <button
                class="unit__detail__upload unit__btn__upload"
                type="button"
              >
                <img src="../../assets/images/checked.png" alt="" />
              </button>
            </div>
            <div class="upload_art_button" v-else @click="addWork">
              <button
                class="unit__detail__upload unit__btn__upload"
                type="button"
              >
                <img src="../../assets/images/checked.png" alt="" />
              </button>
            </div>
            <!-- <div class="uploadBtn1" v-else @click="addWork" data-display="작품등록"></div> -->
          </div>
        </div>
        <div class="upload_btn_container2">
          <div class="upload_art_button" @click="Back">
            <button
              class="unit__detail__upload unit__btn__upload"
              type="button"
              @click.prevent="change_date_works"
            >
              <img src="../../assets/images/back.png" alt="" />
            </button>
          </div>
          <!-- <div class="uploadBtns2">
            <div class="uploadBtn2" @click="Back" data-display="뒤로가기"></div>
          </div> -->
        </div>
        <!-- <div class="upload_btn_container3">
          <div class="uploadBtns3">
            <div class="uploadBtn3" @click="hash" data-display="해쉬태그뽑기"></div>
          </div>
        </div> -->
      </div>
      <!-- </div> -->
    </div>
  </body>
</template>

<script>
import ImageUploader from "vue-image-upload-resize";
import "../../assets/css/WorkUpLoadPage.css";
import init from "../../assets/js/UploadWork";
// import http from '../api/http'
export default {
  components: {
    ImageUploader,
  },
  props: ["work_info", "mode"],
  mounted() {
    init.input();
    if (this.work_info) {
      this.img_url = this.work_info.work_piece;
      this.work_title = this.work_info.work_title;
      this.work_desc = this.work_info.work_desc;
      this.work_tool = this.work_info.work_tool;
      this.work_piece = this.work_info.work_piece;
    }
  },
  created() {},
  watch: {},
  computed: {},
  methods: {
    hash() {
      const hashTag = document.querySelectorAll(".tags-input span");
      this.hashtag_list = `${localStorage.getItem("user_id")},${this.work_title},`
      for (let item of hashTag) {
        if (
          this.work_info &&
          !this.work_info.hashtags.includes(item.innerText)
        ) {
          this.hashtag_list += item.innerText + ",";
        } else {
          this.hashtag_list += item.innerText + ",";
        }
      }
    },
    Back() {
      this.$router.go(-1);
    },
    description() {
      let work__desc = this.work_desc.replace(/(?:\r\n|\r|\n)/g, "<br/>");
      let work__tool = this.work_tool.replace(/(?:\r\n|\r|\n)/g, "<br/>");
      this.work_desc = work__desc;
      this.work_tool = work__tool;
    },
    addWork: function() {
      this.hash();
      this.description();

      const blob = this.img_url;
      const file = new File([blob], "profile");
      this.work_piece = file;
      // 업로드한 파일로 부터 url 을 생성할 수 있다.
      this.$store
        .dispatch("addWork", {
          work_artistId: localStorage.getItem("user_id"), //로컬스토리지 아이디 받아오기
          work_title: this.work_title,
          work_desc: this.work_desc,
          work_piece: this.work_piece,
          work_rating: this.work_rating,
          work_tool: this.work_tool,
          hashTags: this.hashtag_list,
        })
    },
    setImage: function(output) {
      this.hasImage = true;
      this.work_piece = output;
      this.img_url = output;
    },
    onClickUploadImage() {
      this.$refs.work_piece.click();
    },
    rating(event) {
      this.work_rating = event.target.value;
    },
  },
  data: () => {
    return {
      work_artistId: "",
      work_title: "",
      work_desc: "",
      work_piece: "",
      work_tool: "",
      img_url: "",
      hashtag_list: "",
      hasImage: false,
    };
  },
};
</script>

<style></style>
