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

    <div class="main_move_to_top">TOP</div>
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
          2900:{
            itemsToShow: 9,
          },
          2600: {
            itemsToShow: 7,
          },
          1800: {
            itemsToShow: 5,
          },
          1700: {
            itemsToShow: 4,
          },
          1600: {
            itemsToShow: 4,
          },
          1500: {
            itemsToShow: 3,
          },
          1400: {
            itemsToShow: 3,
          },
          1100: {
            itemsToShow: 3,
          },
          900: {
            itemsToShow: 2,
          },
          500: {
            itemsToShow: 2
          },
          0: {
            itemsToShow: 1,
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
  updated() {
    // Move To Top
    const topBtn = document.querySelector(".main_move_to_top");
    function scrollIt(ele) {
      console.log(ele);
      topBtn.classList.add("main_move_to_top_show");
      window.scrollTo({
        behavior: "smooth",
        left: 0,
        top: ele.offsetTop,
      });
    }

    function topclickHandler() {
      const first = document.querySelector(".main_hooper_wrapper");
      scrollIt(first);
    }

    topBtn.addEventListener("click", topclickHandler);

    window.addEventListener("scroll", function () {
      let position_2 = document
        .querySelector(".main_hooper_wrapper")
        .getBoundingClientRect().top;
      if (position_2 <= 0) {
        topBtn.classList.add("main_move_to_top_show");
      }
      if (position_2 === window.innerHeight) {
        topBtn.classList.remove("main_move_to_top_show");
      }
    });
  },
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
    moveToTop() {
      window.scrollTo({
        behavior: "smooth",
        left: 0,
        top: document.querySelector(".main_hooper_wrapper").offsetTop,
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

  background-color: #fcf1e7;
}

/* Hooper */
.main_hooper_wrapper .number {
  font-size: 3.5rem;
  padding-left: 18%;
  text-align: left;
  margin: 0;
  -webkit-text-stroke: 2px #fcf1e7;
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
  color: #fcf1e7;
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

/* Move To Top */
.main_move_to_top {
  position: absolute;
  font-size: 1rem;
  padding: 0.4rem;
  font-weight: 900;
  right: 50%;
  bottom: 3%;
  border: 4px solid #222;
  border-radius: 1rem;
  color: white;
  opacity: 0;
  transition: 2s;
  cursor: pointer;
  background: #222;
  transform: translateX(50%);
}
.main_move_to_top_show {
  opacity: 1;
  z-index: 999;
  position: fixed;
}
</style>
