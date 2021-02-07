<template>
  <div class="subgallery">
    <hooper
      class="hooper1"
      :settings="hooperSettings"
      v-if="imgList1.length != 0"
    >
      <slide v-for="(img, i) in imgList1" :key="i">
        <div class="images">
          <img :src="img.work_piece" :alt="img.work_title" />
          <div class="slide_body" @click="detailImage()">
            <h1 v-text="img.work_title"></h1>
            <p>By : {{ img.work_artistId }}</p>
          </div>
        </div>
      </slide>
    </hooper>
    <hooper
      :settings="hooperSettings"
      class="hooper2"
      v-if="imgList2.length != 0"
    >
      <slide v-for="(img, i) in imgList2" :key="i">
        <div class="images">
          <img :src="img.work_piece" :alt="img.work_title" />
          <div class="slide_body" @click="detailImage()">
            <h1 v-text="img.work_title"></h1>
            <p>By : {{ img.work_artistId }}</p>
          </div>
        </div>
      </slide>
    </hooper>
  </div>
</template>

<script>
import http from "../../api/http.js";

export default {
  data() {
    return {
      imgList1: [],
      imgList2: [],
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
            itemsToShow: 4.6,
          },
          1100: {
            itemsToShow: 3.5,
          },
          0: {
            itemsToShow: 2.5,
          },
        },
      },
    };
  },
  components: {
    Hooper: window.Hooper.Hooper,
    Slide: window.Hooper.Slide,
  },
  created() {
    http.get(`/gallery/getAllSubGallery_odd`).then(
      (response) => {
        var data = response.data;

        for (var i = 0; i < data.length; i++) {
          data[i].work_piece = "data:image/jpeg;base64," + data[i].work_piece;
        }

        this.imgList1 = data;
      },
      (error) => {
        console.log(error);
      }
    );

    http.get(`/gallery/getAllSubGallery_even`).then(
      (response) => {
        var data = response.data;

        for (var i = 0; i < data.length; i++) {
          data[i].work_piece = "data:image/jpeg;base64," + data[i].work_piece;
        }

        this.imgList2 = data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    detailImage() {
      console.log("image Click");
    },
  },
};
</script>

<style scoped>
.images {
  height: 40vh;
  width: 16em;
  /* padding: 0 1vw; */
  margin: 13px;
  background-size: cover;
  background-position: center center;
  border-image: url("../../assets/images/frame4.png") 1 fill / 1px / 0.8rem
    round space;
  border-image-repeat: round;
  overflow: hidden;
  position: relative;
  transform: scale(1);
  /* display: inline-block; */
}

/* .images:hover {
  transform: scale(1.05);
} */

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
  filter: brightness(40%)
  /* transform: scale(1); */
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
  padding: 0 1vw;
  /* margin-top: 1vh; */
}
.hooper2 {
  height: 45vh;
  padding: 0 1vw;
  margin-top: 3vh;
}
</style>
