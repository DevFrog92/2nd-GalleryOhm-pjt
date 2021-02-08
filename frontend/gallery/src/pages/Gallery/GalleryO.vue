<template>
  <div class="Main_container">
    <div class="main_hooper_wrapper">
      <hooper
        class="hooper1"
        :settings="hooperSettings"
        v-if="workList_odd.length != 0"
      >
        <slide v-for="(work, i) in workList_odd" :key="i" :index="i">
          <div class="img" @click="clickMainGallery">
            <p class="number" v-if="i <= 10">0{{ i + 1 }}</p>
            <div class="content">
              <h1>귀여운 미켈란젤로</h1>
            </div>
            <img
              class="images"
              :src="work.work_piece"
              :alt="work.work_title"
              @click="detailImage(work.work_id, work.work_piece)"
            />
            <p class="name">갤러리이름, 2020.01.03</p>
          </div>
        </slide>
        <hooper-navigation slot="hooper-addons"></hooper-navigation>
      </hooper>
    </div>
    <div class="main_overlay"></div>
    <div class="main_click_works_wrapper">
      <GalleryOWorks />
    </div>
  </div>
</template>

<script>
import http from "../../api/http";
import GalleryOWorks from "../Gallery/GalleryO_works";
export default {
  data() {
    return {
      hooperSettings: {
        infiniteScroll: true,
        breakpoints: {
          2400: {
            itemsToShow: 7,
          },
          1800: {
            itemsToShow: 5,
          },
          1700: {
            itemsToShow: 4.5,
          },
          1600: {
            itemsToShow: 4,
          },
          1500: {
            itemsToShow: 3,
          },
          1400: {
            itemsToShow: 2.7,
          },
          1100: {
            itemsToShow: 2.5,
          },
          0: {
            itemsToShow: 2.5,
          },
        },
      },
      workList_odd: [],
      workList_even: [],
      index: 0,
    };
  },
  components: {
    GalleryOWorks,
    Hooper: window.Hooper.Hooper,
    Slide: window.Hooper.Slide,
    HooperNavigation: window.Hooper.Navigation,
  },
  created() {
    this.getOddList();
    this.getEvenList();
  },
  updated() {},
  methods: {
    detailImage(work_id, work_piece) {
      // var width = document.getElementById("img").width;
      // var height = document.getElementById("img").height;
      console.log("[id:" + work_id + "] " + work_piece.length);
    },
    getOddList() {
      http;
      http.get(`/gallery/getAllSubGallery_odd`).then(
        (response) => {
          var workList = response.data;

          for (var i = 0; i < workList.length; i++) {
            workList[i].work_piece =
              "data:image/jpeg;base64," + workList[i].work_piece;
          }

          this.workList_odd = workList;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getEvenList() {
      http.get(`/gallery/getAdultGallery_even/`).then(
        (response) => {
          var workList = response.data;

          for (var i = 0; i < workList.length; i++) {
            workList[i].work_piece =
              "data:image/jpeg;base64," + workList[i].work_piece;
          }

          this.workList_even = workList;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    clickMainGallery() {
      window.scrollTo({
        behavior: "smooth",
        left: 0,
        top: document.querySelector(".main_click_works_wrapper").offsetTop,
      });
    },
  },
};
</script>

<style scoped>
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);

.main_hooper_wrapper,
.main_overlay {
  width: 100%;
  height: 100vh;
}
.main_overlay {
  background-color: #175711;
}
.main_hooper_wrapper {
  padding-top: 3.5rem;
  display: flex;
  text-align: center;
  background-color: #175711;
}
/* 메인 갤러리 클릭시 밑으로 이동 */
.main_click_works_wrapper {
  display: flex;
  padding-left: 30%;
  
  background-color: #FCF1E7;
}

/* Hooper */
.main_hooper_wrapper .number {
  font-size: 3.5rem;
  padding-left: 18%;
  text-align: left;
  margin: 0;
  -webkit-text-stroke: 2px #FCF1E7;
  -webkit-text-fill-color: transparent;
}
.main_hooper_wrapper .images {
  height: 30em;
  width: 23em;
  padding: 0.1vw 0.1vw;
  margin-bottom: 2%;
  background-size: cover;
  object-fit: cover;
  background-position: center;
}
.main_hooper_wrapper .img {
  position: relative;
  height: 40em;
  background-size: cover;
}

.main_hooper_wrapper .img .content {
  position: absolute;
  top: 50%;
  left: 50%;
  width: 100%;
  transform: translate(-50%, -50%);
  z-index: 2;
  text-align: center;
}
.main_hooper_wrapper .img .content h1 {
  -webkit-text-stroke: 3px black;
  -webkit-text-fill-color: transparent;
  font-size: 4.5rem;
  font-family: "Hanna", sans-serif;
}
.main_hooper_wrapper .hooper1 {
  height: 80vh;
  width: 100vw;
  margin-top: 1.5vh;
  /* transform: scale(1.2); */
}
.main_hooper_wrapper .name {
  text-align: center;
  font-size: 1rem;
  font-family: "Hanna", sans-serif;
  color:  #FCF1E7;
}
.Main_container {
  /* title */
  height: 100%;
  width: 100%;
  left: -10rem;

  /* hooper */
  overflow: hidden;
  text-align: left;
}

/* 지워도 됌 */
.hooper-next,
.hooper-prev {
  top: 95.5%;
  bottom: 8%;
  font-size: 5rem;
}
.hooper-next {
  right: 40%;
}
.hooper-prev {
  left: 40%;
}
.hooper {
  height: 70vh;
  width: 55vw;
}
.hooper-indicator {
  height: 10px;
  width: 10px;
  border-radius: 50%;
  background-color: darkgrey;
}
.hooper-indicator.is-active {
  background-color: white;
}
svg.icon.icon-arrowRight {
  fill: #fff;
}
svg.icon.icon-arrowLeft {
  fill: #fff;
}
.hooper-slide.is-current {
  transform: scale(1);
}
</style>
