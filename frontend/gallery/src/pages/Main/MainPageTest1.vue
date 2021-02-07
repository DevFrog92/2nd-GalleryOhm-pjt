<template>
  <div class="subgallery">
    <hooper
      class="hooper1"
      :settings="hooperSettings"
      v-if="workList_odd.length != 0"
    >
      <slide v-for="(work, i) in workList_odd" :key="i" :index="i">
        <!-- <div
          class="images"
          :style="work.work_piece"
          @click="detailImage(work.work_piece)"
        >
        </div> -->

        <div class="img">
          <p class="number">{{ i }}</p>
          <div class="content">
            <h1>미친</h1>
            <h1>피카소</h1>
          </div>
          <img
            class="images"
            :src="work.work_piece"
            :alt="work.work_title"
            @click="detailImage(work.work_id, work.work_piece)"
          />
          <p></p>
          <p class="name">작가이름, 2020.01.03</p>
        </div>
      </slide>
    </hooper>
  </div>
</template>

<script>
import http from "../../api/http";

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
            itemsToShow: 6,
          },
          1500: {
            itemsToShow: 2.8,
          },
          1100: {
            itemsToShow: 3.5,
          },
          0: {
            itemsToShow: 2.5,
          },
        },
      },
      workList_odd: [],
      workList_even: [],
    };
  },
  components: {
    Hooper: window.Hooper.Hooper,
    Slide: window.Hooper.Slide,
  },
  created() {
    this.getOddList();
    this.getEvenList();
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
  },
};
</script>

<style scoped>
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);
html,
body {
  height: 100%;
}

.subgallery {
  height: 100%;
  /* background-color: #C20A0A; */
  /* transform: rotate(-8deg); */
}
.number {
  font-size: 2rem;
  padding-left: 20%;
  text-align: left;
}
.images {
  height: 65vh;
  width: 23em;
  padding: 0.1vw 0.1vw;
  margin-top: 0px;
  background-size: cover;
  object-fit: cover;
  background-position: center;
  border-image: url("../../assets/images/frame4.png") 1 fill / 1px / 0.8rem
    round space;
  border-image-repeat: round;
}
.img {
  position: relative;
  height: 100vh;
  background-size: cover;
}

.img .content {
  position: absolute;
  top: 40%;
  left: 50%;
  width: 100%;
  transform: translate(-50%, -50%);
  z-index: 2;
  text-align: center;
}
.img .content h1 {
  font-size: 10rem;
  font-family: "Hanna", sans-serif;
  color:rgb(46, 211, 31);
}
.hooper1 {
  height: 80vh;
  width: 110vw;
  padding: 0 0vw;
  margin-top: 7.5vh;
  transform: rotate(-7deg);
  /* transform: scale(1.2); */
}
.img .number{
    font-size: 2rem;
   font-family: "Hanna", sans-serif; 
}
.name{
    font-size:1rem;
    font-family: "Hanna", sans-serif; 
}
</style>
