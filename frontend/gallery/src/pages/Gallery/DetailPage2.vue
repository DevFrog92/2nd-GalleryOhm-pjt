<template>
  <div>
    <div class="detailPage">
      <div class="preview">
        <div class="container">
          <div class="work_view">
            <!-- 첫 div 정보 -->
            <div class="title">
              <!-- title -->
              <h1 class="title_text">{{ work.work_title }}</h1>
              <!-- 액자 이미지 -->
              <div class="title_img">
                <img class="work" :src="work.work_piece" />
              </div>
            </div>
          </div>
        </div>
      </div>
      <div
        class="pullImg type-b"
        :style="'background-image: url(' + work.work_piece + ')'"
      ></div>
      <div class="detail">
        <div class="img_box">
          <div class="img">
            <img :src="work.work_piece" alt="" class="detail_img" />
          </div>
          <div class="actions">
            <!-- 좋아요, 북마크 -->
            <div class="scrap scrap_active" v-if="scrapState">
              <!-- 북마크 되어있는 상태 -->
              <img
                src="../../assets/images/bookmark_active.png"
                alt=""
                @click="deleteBookmark()"
              />
            </div>
            <div class="scrap scrap_no" v-else>
              <img
                src="../../assets/images/bookmark.png"
                alt=""
                @click="addBookmark()"
              />
              <!-- 북마크 안되어있는 상태 -->
            </div>
            <div class="cost cost_active" v-if="costState">
              <!-- 좋아요 되어있는 상태 -->
              <div class="cost_info">
                <img
                  src="../../assets/images/won (1).png"
                  alt="코스트"
                  @click="deleteCost()"
                />
              </div>
            </div>
            <div class="cost cost_no" v-else>
              <!-- 좋아요 안되어있는 상태 -->
              <div class="cost_info">
                <img
                  src="../../assets/images/won.png"
                  alt="코스트"
                  @click="addCost()"
                />
              </div>
            </div>
            <div class="costCnt">
              {{ totalCost }}
            </div>
          </div>
        </div>
        <div class="info_box">
          <div class="info">
            <!-- 작품 이름 -->
            <div class="first">{{ work.work_title }}</div>
            <!-- 작가 이름-->
            <div class="name" @click="moveToArtistPage()">
              <!-- 작가 페이지 이동 함수 넣기 -->
              By {{ work.work_artistId }}
            </div>
            <!-- 작품 설명 -->
            <div class="desc">
              {{ work.work_desc }}
            </div>
            <!-- 작품 그린 툴 -->
            <div class="desc" v-if="work.work_tool != null">
              Drawing By {{ work.work_tool }}
            </div>
            <!-- 작품 사이즈 -->
            <div class="size">size : {{ size }}</div>
            <!-- 작품 해시태그 -->
            <div class="hashtags" v-if="hashtags.length != 0">
              <p v-for="(tag, i) in hashtags" :key="i" class="hashtag">
                #{{ tag }}
              </p>
            </div>
            <div class="modify" v-if="artistState">
              <!-- 작가 본인이라면 수정, 삭제 -->
              <button
                class="modify__work modify__btn__works"
                type="button"
                @click="modifyWork()"
              >
                <img
                  src="../../assets/images/pencil.png"
                  alt=""
                  class="all__works__modify"
                />
              </button>

              <button
                class="modify__work modify__btn__works"
                type="button"
                @click="deleteWork()"
              >
                <img
                  src="../../assets/images/delete.png"
                  alt=""
                  class="all__works__delete"
                />
              </button>
              <button
                class="modify__work modify__btn__works"
                type="button"
                @click="goBack()"
              >
                <img
                  src="../../assets/images/back.png"
                  alt=""
                  class="all__works__back"
                />
              </button>
            </div>
            <div class="modify" v-else>
              <button
                class="modify__work modify__btn__works"
                type="button"
                @click="goBack()"
              >
                <img
                  src="../../assets/images/back.png"
                  alt=""
                  class="all__works__back"
                />
              </button>
            </div>
          </div>
        </div>
      </div>
      <div class="footer">
        <footer>
          <div class="foot">
            <p class="deco_p">
              <strong
                ><em
                  >ⓒ 2021. {{ work.work_artistId }} all rights reserved</em
                ></strong
              >
            </p>
          </div>
        </footer>
      </div>
    </div>
  </div>
</template>

<script>
import http from "../../api/http";
import router from "../../router";

export default {
  data() {
    return {
      imgSrc: "",
      prop_workId: "",
      work: [],
      size: "",
      artistState: false,
      costState: false,
      scrapState: false,
      costCnt: 0,
      totalCost: 0,
      hashtags: [],
    };
  },
  props: ["work_id"],
  created() {
    this.prop_workId = this.$route.params.work_id;

    if (this.prop_workId == null) {
      alert("잘못된 접근방식입니다.");
      history.go(-1);
    } else {
      this.getWorkInfo();
      setTimeout(() => {
        this.getSize();
        this.matchArtist();
        this.getCostState();
        this.getScrapState();
      }, 500);
    }
  },
  mounted() {
    window.scrollTo(0, 0);
  },
  methods: {
    getWorkInfo() {
      http.get("/work/getWork/" + this.prop_workId).then(
        (response) => {
          var work = response.data;
          work.work_piece = "data:image/jpeg;base64," + work.work_piece;
          this.work = work;
          this.costCnt = this.work.work_cost;
          this.totalCost = this.costCnt * 100;
          this.hashtags = this.work.hashtags;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getSize() {
      const img = document.querySelector(".work");
      const pull = document.querySelector(".pullImg");

      let width = img.clientWidth;
      let height = img.clientHeight;

      this.size = width + " x " + height;

      let rate = window.innerWidth / width;
      let changeHeight = height * rate;

      pull.style.width = "100%";
      pull.style.height = changeHeight + "px";

      const actions = document.querySelector(".actions");
      const detailImg = document.querySelector(".detail_img");

      actions.style.width = detailImg.width + "px";
    },
    matchArtist() {
      if (localStorage.getItem("user_id") == this.work.work_artistId) {
        this.artistState = true;
      }
    },
    getCostState() {
      http
        .get("/work/isCheckCost", {
          params: {
            cost_userId: localStorage.getItem("user_id"),
            cost_workId: this.work.work_id,
          },
        })
        .then(
          (response) => {
            if (response.data == 1) {
              this.costState = true;
            }
          },
          (error) => {
            console.log(error);
          }
        );
    },
    getScrapState() {
      http
        .get("/work/isCheckScrap", {
          params: {
            scrap_userId: localStorage.getItem("user_id"),
            scrap_workId: this.work.work_id,
          },
        })
        .then(
          (response) => {
            if (response.data == 1) {
              this.scrapState = true;
            }
          },
          (error) => {
            console.log(error);
          }
        );
    },
    moveToArtistPage() {
      console.log(this.work.work_artistId);
      if (this.work.work_artistId === localStorage.getItem("user_id")) {
        router.push("/mypage");
      } else {
        localStorage.setItem("props_id", this.work.work_artistId);

        router.push({
          name: "ArtistMyPage",
          params: { props_id: this.work.work_artistId },
        });
      }
    },
    addBookmark() {
      http
        .get("/work/scrapWork", {
          params: {
            scrap_userId: localStorage.getItem("user_id"),
            scrap_workId: this.work.work_id,
          },
        })
        .then(
          () => {
            alert("즐겨찾기 했습니다.");
            this.scrapState = true;
          },
          (error) => {
            console.log(error);
          }
        );
    },
    deleteBookmark() {
      http
        .get("/work/clearToWorkScrap", {
          params: {
            scrap_userId: localStorage.getItem("user_id"),
            scrap_workId: this.work.work_id,
          },
        })
        .then(
          () => {
            alert("즐겨찾기를 취소했습니다.");
            this.scrapState = false;
          },
          (error) => {
            console.log(error);
          }
        );
    },
    addCost() {
      http
        .get("/work/giveCostToWork", {
          params: {
            cost_userId: localStorage.getItem("user_id"),
            cost_workId: this.work.work_id,
          },
        })
        .then(
          () => {
            alert("작품 좋아요");
            this.costState = true;
            this.costCnt += 1;
            this.totalCost += 100;
          },
          (error) => {
            console.log(error);
          }
        );
    },
    deleteCost() {
      http
        .get("/work/clearToWorkCost", {
          params: {
            cost_userId: localStorage.getItem("user_id"),
            cost_workId: this.work.work_id,
          },
        })
        .then(
          () => {
            alert("작품 좋아요 취소");
            this.costState = false;
            this.costCnt -= 1;
            this.totalCost -= 100;
          },
          (error) => {
            console.log(error);
          }
        );
    },
    deleteWork() {
      if (confirm("작품을 삭제하시겠습니까?")) {
        this.$store.dispatch("deleteWork", this.work.work_id).then(() => {
          history.go(-1);
        });
      }
    },
    modifyWork() {
      this.$router.push({
        name: "WorkUpLoad",
        params: {
          work_info: this.work,
          mode: "modify",
          hashtags: this.hashtags,
        },
      });
    },
    goBack() {
      this.$router.go(-1);
    },
  },
};
</script>

<style scoped>
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);
@font-face {
  font-family: "S-CoreDream-8Heavy";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-8Heavy.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: "Cafe24Ohsquare";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/Cafe24Ohsquare.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

.preview {
  /* background-color: #ffe8e8; */
  /* background-color: #f4f5f9; */
  background-color: #ebecf0;
  color: black;
}

.container {
  margin: 0 auto;
  padding: 0;
  width: 100%;
}

.work_view {
  padding-top: 28vh;
  padding-bottom: 28vh;
}

.work_view .title .title_text {
  font-size: 10rem;
  /* font-family: "Hanna", sans-serif; */
  font-family: "S-CoreDream-8Heavy";
}

.work_view .title_img {
  margin-top: 28vh;
  width: 100%;
  height: auto;
}

.work_view .title_img img {
  border: 10px white solid;
  width: 100%;
  height: auto;
}

.pullImg {
  /* height: auto;
  width: 100%; */
  overflow: hidden;

  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
}

.detail {
  height: 70vh;
  display: flex;
  justify-content: space-around;
  /* background-color: #f4f5f9; */
  background-color: #ebecf0;
}

.detail .img_box {
  order: flex;
  width: 45%;
  height: 100%;
  top: 0%;
}

.detail .img_box .img {
  display: inline-block;
  padding: 0;
}

.detail .img_box .img img {
  display: block;
  margin-left: auto;
  margin-right: auto;
  border: 10px white solid;
  height: 50vh;
  margin-top: 14vh;
}
.detail .info_box {
  order: flex;
  width: 45%;
  height: 100%;
  position: relative;
  color: black;
}

.detail .info_box .info {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  /* font-family: "Hanna", sans-serif; */
  font-family: "S-CoreDream-8Heavy";
  width: 80%;
}

.detail .info_box .info .first {
  font-size: 40px;
}

.detail .info_box .info .name {
  margin-top: 2vh;
  font-size: 20px;
  cursor: pointer;
}

.detail .info_box .info .desc {
  margin-top: 1.5vh;
  font-size: 15px;
}

.detail .info_box .btn {
  margin-top: 3vh;
}

.footer {
  display: block;
  /* background-color: #f4f5f9; */
  background-color: #ebecf0;
  padding-top: 10vh;
}

footer {
  height: 80px;
}

footer .foot {
  padding-top: 1%;
  /* margin-left: 10%; */
  width: 100%;
}

footer .foot .t {
  color: gray;
  font-family: "Hanna", sans-serif;
  margin: 0;
}

.kenburns-top {
  -webkit-animation: kenburns-top 20s ease-out alternate-reverse infinite both;
  animation: kenburns-top 20s ease-out alternate-reverse infinite both;
}

@-webkit-keyframes kenburns-top {
  0% {
    -webkit-transform: scale(1) translateY(0);
    transform: scale(1) translateY(0);
    -webkit-transform-origin: 50% 16%;
    transform-origin: 50% 16%;
  }
  100% {
    -webkit-transform: scale(1.25) translateY(-15px);
    transform: scale(1.25) translateY(-15px);
    -webkit-transform-origin: top;
    transform-origin: top;
  }
}
@keyframes kenburns-top {
  0% {
    -webkit-transform: scale(1) translateY(0);
    transform: scale(1) translateY(0);
    -webkit-transform-origin: 50% 16%;
    transform-origin: 50% 16%;
  }
  100% {
    -webkit-transform: scale(1.25) translateY(-15px);
    transform: scale(1.25) translateY(-15px);
    -webkit-transform-origin: top;
    transform-origin: top;
  }
}

.actions {
  padding-top: 2vh;
  margin: 0 auto;
}

.scrap {
  display: inline;
  width: 2vw;
  height: 3.8vh;
}

.scrap img {
  display: inline;
  width: 2vw;
  height: 3.8vh;
  cursor: pointer;
}

.scrap img:hover {
  -webkit-filter: opacity(0.3) drop-shadow(0 0 0 red);
  filter: opacity(0.3) drop-shadow(0 0 0 red);
}

.cost {
  display: inline;
  width: 5vw;
  height: 5vh;
  padding-left: 1vw;
}

.cost .cost_info {
  display: inline;
}

.cost .cost_info img {
  width: 2vw;
  height: 3.8vh;
  cursor: pointer;
}

.cost .cost_info img:hover {
  -webkit-filter: opacity(0.5) drop-shadow(0 0 0 yellow);
  filter: opacity(0.5) drop-shadow(0 0 0 yellow);
}

.costCnt {
  display: inline;
  width: 5vw;
  height: 5vh;
  padding-left: 1vw;

  font-family: "S-CoreDream-8Heavy";
}

.modify__btn__works {
  border: 0;
  outline: 0;
  font-size: 16px;
  border-radius: 320px;
  padding: 0.7rem;
  background-color: #ebecf0;
  text-shadow: 1px 1px 0 #fff;
  z-index: 99;
}

.modify__btn__works {
  color: #61677c;
  font-weight: bold;
  box-shadow: -5px -5px 20px #fff, 5px 5px 20px #babecc;
  transition: all 0.2s ease-in-out;
  cursor: pointer;
}
.all__works__modify,
.all__works__delete,
.all__works__back {
  width: 100%;
  height: 100%;
}

.modify__btn__works:hover {
  box-shadow: -2px -2px 5px #fff, 2px 2px 5px #babecc;
}

.modify__btn__works:active {
  box-shadow: inset 1px 1px 2px #babecc, inset -1px -1px 2px #fff;
}

.modify__btn__works.modify__work {
  border-radius: 8px;
  line-height: 0;
  width: 50px;
  height: 50px;
  display: inline-flex;
  justify-content: center;
  align-items: center;
  margin: 0 8px;
  font-size: 19.2px;
}

.modify {
  margin-top: 2vh;
}

.size {
  margin-top: 1.5vh;
}

.hashtags {
  margin-top: 1.5vh;
}

.hashtags .hashtag {
  display: inline;
}
</style>
