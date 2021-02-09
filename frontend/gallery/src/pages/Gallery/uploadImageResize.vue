<template>
  <body class="upload_picture_page">
    <div class="upload_picture_page_middle">
      <div class="wrapPicture">
        <div class="previewPicture">
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
              <figure>
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="32"
                  height="32"
                  viewBox="0 0 32 32"
                >
                  <path
                    class="path1"
                    d="M9.5 19c0 3.59 2.91 6.5 6.5 6.5s6.5-2.91 6.5-6.5-2.91-6.5-6.5-6.5-6.5 2.91-6.5 6.5zM30 8h-7c-0.5-2-1-4-3-4h-8c-2 0-2.5 2-3 4h-7c-1.1 0-2 0.9-2 2v18c0 1.1 0.9 2 2 2h28c1.1 0 2-0.9 2-2v-18c0-1.1-0.9-2-2-2zM16 27.875c-4.902 0-8.875-3.973-8.875-8.875s3.973-8.875 8.875-8.875c4.902 0 8.875 3.973 8.875 8.875s-3.973 8.875-8.875 8.875zM30 14h-4v-2h4v2z"
                  ></path>
                </svg>
              </figure>
              <span class="upload-caption">{{
                hasImage ? "Replace" : "Click to upload"
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
              <p>* Separate your tags with a comma</p>
              <div class="tags-input"></div>
            </div>
          </div>

          <div class="uploadBtn">
            <button v-if="mode" @click="addWork">MODIFY</button>
            <button v-else @click="addWork">UPLOAD</button>
            <button @click="Back">뒤로가기</button>
            <button @click="hash">해쉬태그뽑기</button>
          </div>
        </div>
      </div>
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
      console.log("asd", this.work_info.hashtags.includes("안녕"));
      this.img_url = this.work_info.work_piece;
      this.work_title = this.work_info.work_title;
      this.work_desc = this.work_info.work_desc;
      this.work_tool = this.work_info.work_tool;
    }
  },
  created() {},
  watch: {},
  computed: {},
  methods: {
    hash() {
      const hashTag = document.querySelectorAll(".tags-input span");

      for (let item of hashTag) {
        if (
          this.work_info.hashtags &&
          !this.work_info.hashtags.includes(item.innerText)
        ) {
          this.hashtag_list += item.innerText + ",";
        }
      }
      console.log(this.hashtag_list);
    },
    Back() {
      this.$router.go(-1);
    },
    addWork: function () {
      this.hash();
      if (this.mode) {
        const blob = this.img_url;  
        const file = new File([blob], "profile");
        // console.log(file);
        this.work_piece = file;
        this.$store
          .dispatch("modifyWork", {
            work_artistId: localStorage.getItem("user_id"), //로컬스토리지 아이디 받아오기
            work_id: this.work_info.work_id,
            work_title: this.work_title,
            work_desc: this.work_desc,
            work_piece: this.work_piece,
            work_rating: this.work_rating,
            work_tool: this.work_tool,
            hashTags: this.hashtag_list,
          })
          .then(() => {
            // http.post("/work/addHashTag",{hashTags:this.hashtag_list,"hashtag_workId"})
          });
      } else {
        console.log(this.work_piece);
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
          .then(() => {
            // http.post("/work/addHashTag",{hashTags:this.hashtag_list,"hashtag_workId"})
          });
      }

      // console.log(this.$refs.work_piece.files);
      // this.work_piece = this.$refs.work_piece.files[0];
    },
    setImage: function (output) {
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
    };
  },
};
</script>

<style>
</style>