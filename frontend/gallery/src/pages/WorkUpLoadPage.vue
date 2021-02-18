<template>
  <body class="upload_picture_page">
    <div class="upload_picture_page_middle">
      <div class="wrapPicture">
        <div class="previewPicture">
          <img v-if="img_url" :src="img_url" alt="" />
          <div class="picture_Upload info_input">
            <!-- <label for="pictureUpload">그림업로드</label><br> -->
            <input
              type="file"
              ref="work_piece"
              hidden
              @change="onChangeImage"
            /><br />
            <button @click.prevent="onClickUploadImage" v-if="!mode">
              그림업로드</button
            ><br />
          </div>
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

          <div class="picture_Age ">
            <p>작품 연령 선택</p>
            <label
              ><input
                type="radio"
                name="age"
                value="0"
                required
                @click="rating"
                id="allAge"
              />전체관람</label
            >
            <label class="gallery19"
              ><input
                type="radio"
                name="age"
                required
                value="19"
                @click="rating"
                id="redAge"
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
              <textarea
                name="hashtagList"
                id="hashtagList"
                v-model="hashtag_list"
              ></textarea>
              <!-- <div class="tags-input"></div> -->
            </div>
          </div>

          <div class="uploadBtn">
            <button
              class="unit__detail__upload unit__btn__upload"
              v-if="mode"
              @click="addWork"
              type="button"
            >
              <img
                src="../assets/images/pencil.png"
                alt=""
                class="all__works__modify"
              />
            </button>
            <button
              class="unit__detail__upload unit__btn__upload"
              @click="Back"
            >
              <img
                src="../assets/images/back.png"
                alt=""
                class="all__works__back"
              />
            </button>
            <button
              class="unit__detail__upload unit__btn__upload"
              @click="hash"
            >
              <img
                src="../assets/images/hashtag.png"
                alt=""
                class="all__works__hashtag"
              />
            </button>
          </div>
        </div>
      </div>
    </div>
  </body>
</template>

<script>
import "../assets/css/WorkUpLoadPage.css";
import init from "../assets/js/UploadWork";
// import http from '../api/http'
export default {
  components: {},
  props: ["work_info", "mode"],
  mounted() {
    init.input();
    if (this.work_info) {
      this.img_url = this.work_info.work_piece;
      this.work_title = this.work_info.work_title;
      this.work_desc = this.work_info.work_desc;
      this.work_tool = this.work_info.work_tool;
      this.work_rating = this.work_info.work_rating;

      for (let i = 0; i < this.work_info.hashtags.length - 1; i++) {
        this.hashtag_list += this.work_info.hashtags[i] + ",";
      }
      this.hashtag_list += this.work_info.hashtags[
        this.work_info.hashtags.length - 1
      ];

      if (this.work_info.work_rating == 0) {
        console.log("전연령");
        document.getElementById("allAge").checked = true;
      } else if (this.work_info.work_rating == 19) {
        console.log("19금");
        document.querySelector("#redAge").checked = true;
        this.work_rating = this.work_info.work_rating;
      }
    }
  },
  created() {},
  watch: {},
  computed: {},
  methods: {
    hash() {
      const hashTag = document.querySelectorAll(".tags-input");

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
    addWork: function() {
      // console.log(this.img_url.slice(23))
      this.hash();
      console.log(this.hashtag_list);
      if (this.mode) {
        const byteCharacters = atob(this.img_url.slice(23));
        const byteNumbers = new Array(byteCharacters.length);
        for (let i = 0; i < byteCharacters.length; i++) {
          byteNumbers[i] = byteCharacters.charCodeAt(i);
        }
        const byteArray = new Uint8Array(byteNumbers);
        const blob = new Blob([byteArray], { type: "image/jpeg" });
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
      }
    },
    onChangeImage(event) {
      const imgFile = event.target.files[0];
      this.work_piece = imgFile;
      this.img_url = URL.createObjectURL(imgFile);
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

<style></style>
