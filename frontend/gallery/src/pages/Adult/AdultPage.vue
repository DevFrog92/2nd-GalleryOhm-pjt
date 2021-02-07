<template>
  <div class="subgallery">
    <hooper
      class="hooper1"
      :settings="hooperSettings"
      v-if="workList_odd.length != 0"
    >
      <slide v-for="(work, i) in workList_odd" :key="i" :index="i">
        <div class="images">
          <img :src="work.work_piece" :alt="work.work_title" />
          <div
            class="slide_body"
            @click="detailImage(work.work_id, work.work_piece)"
          >
            <h1 v-text="work.work_title"></h1>
            <p>By : {{ work.work_artistId }}</p>
          </div>
        </div>
      </slide>
    </hooper>

    <hooper
      class="hooper2"
      :settings="hooperSettings"
      v-if="workList_even.length != 0"
    >
      <slide v-for="(work, i) in workList_even" :key="i">
        <div class="images">
        <img :src="work.work_piece" :alt="work.work_title" />
        <div
            class="slide_body"
            @click="detailImage(work.work_id, work.work_piece)"
          >
            <h1 v-text="work.work_title"></h1>
            <p>By : {{ work.work_artistId }}</p>
          </div>
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
            itemsToShow: 5,
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
      http.get(`/gallery/getAdultGallery_odd/`).then(
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
.subgallery {
  height: 100%;
  background-color: #c20a0a;
}

.images {
  height: 40vh;
  width: 18em;
  padding: 0.1vw 0.1vw;
  margin-top: 15px;
  margin-left: 10%;
  background-size: cover;
  object-fit: cover;
  background-position: center center;
  border-image: url("../../assets/images/frame4.png") 1 fill / 1px / 0.8rem
    round space;
  border-image-repeat: round;
  overflow: hidden;
  position: relative;
  transform: scale(1);
}

.images img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  /* transform: scale(1.2); */
  /* transition: transform 0.3s ease-in-out; */
  display: block;
}

.images .slide_body {
  position: absolute;
  width: 90%;
  height: 90%;
  top: 5%;
  left: 5%;
  display: grid;
  align-content: center;
  text-align: center;
  border: 1px solid #fff;
  color: #fff;
  text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.3);
  transform: scale(1.2);
  transition: all 0.3s ease-in-out;
  pointer-events: visible;
}

.images .slide_body h1,
.images .slide_body p {
  opacity: 0;
  transition: all 0.4s;
}

.images .slide_body h1 {
  margin: 0;
  font-size: 1.5rem;
  transform: translate(0, -0.7rem);
}

.images .slide_body p {
  font-size: 0.8rem;
  transform: translate(0, 0.7rem);
}

.images:hover img {
  /* filter: grayscale(100%); */
  /* filter: invert(100%); */
  /* filter: opacity(70%); */
  /* filter: blur(5px); */
  filter: brightness(40%);
}

.images:hover .slide_body {
  transform: scale(1);
}

.images .slide_body:hover h1,
.images .slide_body:hover p {
  opacity: 1;
  transform: scale(1);
}

.hooper1 {
  height: 45vh;
  padding: 0 0vw;
  margin-top: 1vh;
}
.hooper2 {
  height: 45vh;
  padding: 0 0vw;
  margin-top: 3vh;
}
</style>
