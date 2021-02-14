<template>
  <div>
    <div class="detailPage">
      <!-- ( 배경 : 어두운색) -->
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
          <div class="modify" v-if="artistState">
            <!-- 작가 본인이라면 수정, 삭제 -->
          </div>
          <div class="img">
            <img :src="work.work_piece" alt="" />
          </div>
          <div class="actions">
            <!-- 좋아요, 북마크 -->
          </div>
        </div>
        <div class="info_box">
          <div class="info">
            <!-- 작품 이름 -->
            <div class="first">{{ work.work_title }}</div>
            <!-- 작가 이름-->
            <div class="name" @click="moveToArtistPage">
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
          </div>
        </div>
      </div>
      <div class="footer">
        <footer>
          <div class="foot">
            <p class="t">wwater@google.com</p>
            <p class="t">tel.010-4433-212</p>
          </div>
        </footer>
      </div>
    </div>
  </div>
</template>

<script>
import http from "../../api/http";

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
  mounted() {},
  methods: {
    getWorkInfo() {
      http.get("/work/getWork/" + this.prop_workId).then(
        (response) => {
          var work = response.data;
          work.work_piece = "data:image/jpeg;base64," + work.work_piece;
          this.work = work;
          this.costCnt = this.work.work_cost;
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
            if (response.data == 0) {
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
            if (response.data == 0) {
              this.scrap_userId = true;
            }
          },
          (error) => {
            console.log(error);
          }
        );
    },
    moveToArtistPage() {
      if (this.work.work_artistId === localStorage.getItem("user_id")) {
        this.$router.push("/mypage");
      } else {
        this.$router.push({
          name: "UserProfile",
          params: { props_id: this.work.work_artistId },
        });
      }
    },
  },
};
</script>

<style scoped>
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);

.preview {
  /* background-color: #ffe8e8; */
  background-color: #f4f5f9;
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
  font-family: "Hanna", sans-serif;
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
  background-color: #f4f5f9;
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
  font-family: "Hanna", sans-serif;
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
  background-color: #f4f5f9;
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
</style>
