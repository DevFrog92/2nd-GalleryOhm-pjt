<template>
  <div class="gallery__render">
    <div class="gallery__desc__front">
      <div class="gallery__desc__section">
        <h1>{{ gallery_info.gallery_name }}</h1>
        <div class="gallery_article">
          <div class="gallery__desc__aside">
            <span></span>
            <div class="created_by">Curated by</div>
            <div class="gallery_director">
              {{ gallery_info.gallery_artistId }}
            </div>
            <div class="gallery__desc__show">Explore</div>
          </div>
          <div class="gallery__desc__aside_desc">
            <p>
              {{ gallery_info.gallery_desc }}
            </p>
          </div>
        </div>
      </div>
    </div>
    <div id="lamp">
      <input type="radio" name="switch" value="on" checked="checked" />
      <input type="radio" name="switch" value="off" />
      <div class="lamp">
        <div class="gonna-give-light"></div>
      </div>
    </div>
    <button
      class="back__gallery back__btn__gallery"
      type="button"
      @click="goBack"
    >
      <img class="back__btn" src="../../assets/images/back.png" />
    </button>
    <div class="qurating" @click="qurating = !qurating">
      <i class="icon-mic-1" v-if="!qurating"></i>
      <i class="icon-mic-1" v-else></i>
      <audio v-show="qurating" class="player" controls ref="player">
        <source src="" ref="source" />
      </audio>
    </div>
    <div class="foot_print" @click="giveFootprint">
      <img src="../../assets/images/foot1.png" v-if="!footprint" />
      <img src="../../assets/images/foot2.png" v-else />
    </div>
    <div class="main__carousel">
      <div class="carousel__body">
        <div class="move__button">
          <div class="carousel__prev"><i class="icon-left-open"></i></div>
          <div class="carousel__next"><i class="icon-right-open"></i></div>
        </div>
        <div class="carousel__slider">
          <div
            class="carousel__slider__item"
            v-for="(item, index) of work_list"
            :key="index"
          >
            <img
              class="gallery__image"
              :src="item.work_piece"
              :alt="item.work_title"
              :data-value="item.work_id"
            />
            <div class="item__title">
              <h1>{{ item.work_title }}</h1>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import "../../assets/css/GalleryRenderPage.css";
import init from "../../assets/js/GalleryRenderPage.js";
import "../../assets/fontello/css/fontello.css";
import http from "../../api/http";
import router from "../../router";
export default {
  data() {
    return {
      work_list: [],
      gallery_desc: "",
      qurating: false,
      footprint: false,
      gallery_id: "",
      gallery_info: {},
    };
  },
  props: ["props_id"],
  mounted() {
    setTimeout(() => {
      init.init();
      const slider = document.querySelector(".carousel__slider");
      slider.addEventListener("click", this.clickHandler);
    }, 1000),
      setTimeout(() => {
        this.tts();
      }, 500);
  },
  created() {
    console.log("gallery render page");
    this.getArtistGallery();
    this.getGAllery();
    this.getFootPrint();
  },
  methods: {
    clickHandler(e) {
      if (e.target.classList.contains("gallery__image")) {
        console.log("===> " + e.target.dataset.value);
        router.push({
          name: "DetailPage2",
          params: { work_id: e.target.dataset.value },
        });
      }
    },
    getFootPrint() {
      http
        .post(
          `/gallery/isFootPrintToGallery/${this.props_id}`,
          localStorage.getItem("user_id")
        )
        .then((response) => {
          const flag = response.data.count;
          if (flag == 0) {
            this.footprint = false;
          } else {
            this.footprint = true;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    giveFootprint() {
      if (!this.footprint) {
        // 발자국 X
        http
          .post(
            `/gallery/giveFootPrintToGallery/${this.props_id}`,
            localStorage.getItem("user_id")
          )
          .then((response) => {
            console.log("====> " + response.data.state);
          })
          .catch((err) => {
            console.log(err);
          });
        this.footprint = !this.footprint;
      } else {
        // 발자국 O
        http
          .post(
            `/gallery/cleanFootPrintToGallery/${this.props_id}`,
            localStorage.getItem("user_id")
          )
          .then((response) => {
            console.log("====> " + response.data.state);
          })
          .catch((err) => {
            console.log(err);
          });
        this.footprint = !this.footprint;
      }
    },
    getArtistGallery() {
      http
        .get(`/gallery/getArtistGallery/${this.props_id}`)
        .then((response) => {
          console.log("Get gallery data to render page");
          const workList = response.data;
          for (let i = 0; i < workList.length; i++) {
            workList[i].work_piece =
              "data:image/jpeg;base64," + workList[i].work_piece;
          }
          this.work_list = workList;
          console.log("gallery work list", this.work_list);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getGAllery() {
      http.get(`/gallery/getGallery/${this.props_id}`).then((response) => {
        console.log("get gellery info", response.data);
        this.gallery_info = response.data;
        this.gallery_desc = this.gallery_info.gallery_desc;
      });
    },
    tts: function () {
      return http
        .post(
          `/kakao/makeVoice_byteArr`,
          {
            content: this.gallery_desc,
          },
          {
            responseType: "blob",
          }
        )
        .then(
          (response) => {
            var blob = new Blob([response.data], {
              type: "audio/mp3",
            });
            var url = window.URL.createObjectURL(blob);
            this.audioSrc = url;

            this.$refs.source.src = url;
            this.$refs.player.load(); //업로드완료 후 파일로딩
          },
          (error) => {
            console.log(error);
          }
        );
    },
    goBack() {
      this.$router.go(-1);
    },
  },
};
</script>

<style></style>
