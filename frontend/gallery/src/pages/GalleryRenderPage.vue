<template>
  <div class="gallery__render">
    <div class="gallery__desc__front">
      <div class="gallery__desc__section">
        <h1>{{ gallery_info.gallery_name }}</h1>
        <div class="gallery_article">
          <div class="gallery__desc__aside">
            <span></span>
            <div class="created_by">
              Curated by
            </div>
            <div class="gallery_director">
              {{ gallery_info.gallery_artistId }}
            </div>
            <div class="gallery__desc__show">
              Explore
            </div>
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
    <div class="close__btn" @click="goBack">
      <i class="icon-back"></i>
    </div>
    <div class="qurating" @click="qurating = !qurating">
      <i class="icon-mic-1" v-if="!qurating"></i>
      <i class="icon-mic-off" v-else></i>
      <audio v-show="qurating" class="player" controls ref="player">
        <source src="" ref="source" />
      </audio>
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
            <img :src="item.work_piece" alt="" class="gallery__image" />
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
import "../assets/css/GalleryRenderPage.css";
import init from "../assets/js/GalleryRenderPage.js";
import "../assets/fontello/css/fontello.css";
import http from "../api/http";
export default {
  data() {
    return {
      work_list: [],
      gallery_desc: "",
      qurating: false,
      gallery_id: "",
      gallery_info: {},
    };
  },
  props: ["props_id"],
  mounted() {
    setTimeout(() => {
      init.init();
    }, 1000),
      setTimeout(() => {
        this.tts();
      }, 500);
  },
  created() {
    console.log("gallery render page");
    this.getArtistGallery();
    this.getGAllery();
  },
  methods: {
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
    tts: function() {
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
