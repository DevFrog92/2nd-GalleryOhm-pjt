<template>
  <div class="subgallery">
    <hooper
      class="hooper1"
      :settings="hooperSettings"
      v-if="imgList1.length != 0"
    >
      <slide v-for="(img, i) in imgList1" :key="i">
        <div
          class="images"
          :style="img.work_piece"
          @click="detailImage()"
        ></div>
      </slide>
    </hooper>
    <hooper
      :settings="hooperSettings"
      class="hooper2"
      v-if="imgList2.length != 0"
    >
      <slide v-for="(img, i) in imgList2" :key="i">
        <div
          class="images"
          :style="img.work_piece"
          @click="detailImage()"
        ></div>
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
          data[i].work_piece =
            "background-image: url(data:image/jpeg;base64," +
            data[i].work_piece +
            ")";
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
          data[i].work_piece =
            "background-image: url(data:image/jpeg;base64," +
            data[i].work_piece +
            ")";
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
  padding: 0 1vw;
  margin: 13px;
  /* border-radius: 5px; */
  background-size: cover;
  /* background-repeat: no-repeat; */
  background-position: center center;
  /* border-image: url("../../assets/images/frame4.png") 1 fill / 1px / 1px space; */
  /* border-image: url("../../assets/images/frame4.png") round; */
  border-image: url("../../assets/images/frame4.png") 1 fill / 1px / 0.8rem
    round space;
  /* border-image-slice: 1 fill; */
  border-image-repeat: round;
  /* border-image-width: 1; */
  /* border-image-outset: 0; */
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
