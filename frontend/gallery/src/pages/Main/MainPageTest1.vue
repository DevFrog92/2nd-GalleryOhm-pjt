<template>
  <div class="loading">
    <div class="revealer">
      <div class="revealer__inner"></div>
    </div>
    <div id="slider" class="grid grid--slideshow">
      <div class="grid__item grid__item--slide">
        <span class="number">01</span>
        <div class="img-wrap">
          <img
            src="../../assets/images/Main/1.jpg"
            style="
              border-image: url('../../assets/images/frame4.png') 1 fill / 2px /
                0.8rem round space;
              border-image-repeat: round;
            "
          />
        </div>
        <figcaption class="caption">New York City, March 24</figcaption>
      </div>
      <div class="grid__item grid__item--slide">
        <span class="number">02</span>
        <div class="img-wrap">
          <img src="../../assets/images/Main/2.jpg" />
        </div>
        <figcaption class="caption">Acapulco, March 25</figcaption>
      </div>
      <div class="grid__item grid__item--slide">
        <span class="number">03</span>
        <div class="img-wrap">
          <img src="../../assets/images/Main/3.jpg" />
        </div>
        <figcaption class="caption">Acapulco, March 25</figcaption>
      </div>
      <div class="grid__item grid__item--slide">
        <span class="number">04</span>
        <div class="img-wrap">
          <img src="../../assets/images/Main/4.jpg" />
        </div>
        <figcaption class="caption">Acapulco, March 25</figcaption>
      </div>
      <div class="grid__item grid__item--slide">
        <span class="number">04</span>
        <div class="img-wrap">
          <img src="../../assets/images/Main/4.jpg" />
        </div>
        <figcaption class="caption">Acapulco, March 25</figcaption>
      </div>
      <div class="grid__item grid__item--slide">
        <span class="number">04</span>
        <div class="img-wrap">
          <img src="../../assets/images/Main/4.jpg" />
        </div>
        <figcaption class="caption">Acapulco, March 25</figcaption>
      </div>
      <div class="titles-wrap">
        <div class="grid grid--titles">
          <h3 class="grid__item grid__item--title">Kanzu</h3>
          <h3 class="grid__item grid__item--title">Juked</h3>
          <h3 class="grid__item grid__item--title">Colza</h3>
        </div>
      </div>
      <div class="grid grid--interaction">
        <div class="grid__item grid__item--cursor grid__item--left"></div>
        <div class="grid__item grid__item--cursor grid__item--center"></div>
        <div class="grid__item grid__item--cursor grid__item--right"></div>
      </div>
    </div>
    <div class="frame">
      <div class="frame__title-wrap">
        <div class="frame__mode" role="radiogroup">
          <div class="frame__mode-item frame__mode-item--dark">
            <input
              id="mode-1"
              type="radio"
              name="mode"
              class="frame__mode-input"
            />
            <label class="frame__mode-label" for="mode-1">Dark mode</label>
          </div>
          <div class="frame__mode-item">
            <input
              id="mode-2"
              type="radio"
              name="mode"
              class="frame__mode-input"
              checked
            />
            <label
              class="frame__mode-label frame__mode-label--light"
              for="mode-2"
              >Light mode</label
            >
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
//import "../../assets/css/MainPageTest2.css";
import http from "../../api/http.js";
//require('../../assets/js/Main/imagesloaded.pkgd.min.js');
//require('../../assets/js/Main/charming.min.js');
//require("../../assets/js/Main/TweenMax.min.js");
//require("https://cdnjs.cloudflare.com/ajax/libs/gsap/latest/TimelineMax.min.js");
//require('../../assets/js/Main/demo2.js');
//require("https://cdnjs.cloudflare.com/ajax/libs/gsap/2.0.1/TimelineMax.min.js");
export default {
  name: "MainPageTest2",
  data() {
    return {
      mainGalleryList: {},
      mainWorkList: [],
      mainWorkImages: [],
      errored: false,
      errored2: false,
      timeline: null,
    };
  },
  computed: {},
  created() {
    // 메인 갤러리 조회
    http
      .get("/gallery/getAllMainGallery")
      .then((response) => {
        var galleryDatas = response.data;
        for (var i = 0; i < galleryDatas.length; i++) {
          var workId = galleryDatas[i].gallery_mainWorkId;
          // 메인 갤러리의 메인 작품 조회
          http
            .get("/work/getWork/" + workId)
            .then((response) => {
              var workData = response.data;
              workData.work_piece =
                "data:image/jpeg;base64," + workData.work_piece;
              this.mainWorkImages.push(workData.work_piece);
              this.mainWorkList.push(response.data);
            })
            .catch(() => {
              this.errored2 = true;
            });
        }
        this.mainGalleryList = galleryDatas;
      })
      .catch(() => {
        this.errored = true;
      });
  },
  methods: {},
  mounted() {
    var Slider = function (id, _web, _tab, _mobile, spac) {
      var containerWidth = 0;
      var sliderItemWidth = 0;
      var totalCount = 0;
      var spacing = spac || 10;
      var display = _web;
      var left = 0;
      var interval;

      var DOM = {
        container: function (id) {
          var dom = document.querySelector("#" + id);
          dom.className = "grid grid--slideshow";
          return dom;
        },
        slider: function (container) {
          var slider_children = container.getElementsByClassName(
            "grid__item--slide"
          ); // slider 개수
          totalCount = slider_children.length;

          var dom = document.createElement("div");
          dom.className = "gridslider";
          dom.style.position = "relative";
          //dom.style.overflow = 'hidden';
          dom.style.height = "100%";
          dom.style.left = 0;
          dom.style.transition = "left .5s";
          return dom;
        },
      };

      // DOM 만들기
      var container = DOM.container(id);
      var slider = DOM.slider(container);
      var temp = container.innerHTML;
      container.innerHTML = "";
      slider.innerHTML = temp;
      container.appendChild(slider);
      var items = document.querySelector("#" + id + " .slider").children;
      //items = items.getElementsByClassName('.grid__item .grid__item--slide');
      for (var i = 0; i < items.length; i++) {
        //items[i].className="grid__item grid__item--slide";
        items[i].style.float = "left";
        items[i].style.height = "100%";
        items[i].style.width = sliderItemWidth - spacing + "px";
        items[i].style["margin-right"] = spacing + "px"; // 간격
      }

      // 화면 사이즈 수정시 발생하는 이벤트
      function resize() {
        left = 0;
        document.querySelector("#" + id + " .slider").style.left = left + "px";

        var innerWidth = window.innerWidth;
        if (innerWidth >= 1000) {
          setDisplayCount(_web);
        } else if (innerWidth < 1000 && innerWidth >= 768) {
          setDisplayCount(_tab);
        } else if (innerWidth < 768) {
          setDisplayCount(_mobile);
        }

        if (display === 1) {
          spacing = 0;
          var items = document.querySelector("#" + id + " .slider").children;
          for (var i = 0; i < items.length; i++) {
            items[i].style.width = sliderItemWidth + "px";
            items[i].style["margin-right"] = 0 + "px"; // 간격
          }
        }
      }

      // 디스플레이 갯수 설정 함수
      function setDisplayCount(count) {
        display = count;

        containerWidth = container.offsetWidth + spacing;
        sliderItemWidth = containerWidth / display;

        document.querySelector("#" + id + " .slider").style.width =
          totalCount * sliderItemWidth + spacing * totalCount + "px";
        var items = document.querySelector("#" + id + " .slider").children;
        for (var i = 0; i < items.length; i++) {
          items[i].style.width = sliderItemWidth - spacing + "px";
        }
      }

      // 반응형 디스플레이 갯수 조절
      var isResponsive = _tab != undefined && _mobile != undefined;
      if (isResponsive) {
        window.onresize = resize;
      }
      resize();

      return {
        setDisplayCount: setDisplayCount,
        move: function (index) {
          left = -1 * sliderItemWidth * index;
          document.querySelector("#" + id + " .slider").style.left =
            left + "px";
        },
        prev: function () {
          left += sliderItemWidth;
          var limit = 0;
          if (left > limit) {
            left = limit;
          }
          document.querySelector("#" + id + " .slider").style.left =
            left + "px";
        },
        next: function () {
          left -= sliderItemWidth;
          var limit = -1 * sliderItemWidth * (totalCount - display);
          if (left < limit) {
            left = limit;
          }
          document.querySelector("#" + id + " .slider").style.left =
            left + "px";
        },
        auto: function () {
          clearInterval(interval);
          interval = setInterval(function () {
            left -= sliderItemWidth;
            var limit = -1 * sliderItemWidth * (totalCount - display);
            if (left < limit) {
              left = 0;
            }
            document.querySelector("#" + id + " .slider").style.left =
              left + "px";
          }, 2000);
        },
        stop: function () {
          clearInterval(interval);
        },
      };
    };

    var slider = new Slider("slider", 4, 3, 1, 20);
    slider.stop();
  },
  updated() {},
};
</script>

<style>
article,
figcaption,
figure {
  display: block;
}
figure {
  margin: 0;
}

/** 헤더를 없앰. **/
.revealer {
  position: fixed;
  width: 100vw;
  height: 100vh;
  top: 0;
  left: 0;
  transform: rotate(-8deg);
  display: flex;
  align-items: center;
  justify-content: center;
  pointer-events: none;
}

.revealer__inner {
  background-color: var(--color-reveal-bg);
  width: 200%;
  height: 200%;
  position: relative;
  flex: none;
}
.loading .img-wrap {
  width: 100%;
  position: relative;
  height: 100%;
  height: 35vw;
}

.loading img {
  background-color: black;
  width: calc(100% + 40px);
  height: 100%;
  left: -20px;
  top: 0;
  background-size: cover;
  background-position: 50% 50%;
  position: absolute;
  pointer-events: none;
}
.grid {
  position: absolute;
  display: grid;
  height: 400px;
  width: 100%;
  left: 0;
  top: 0;
  grid-template-columns: 30% 30% 30%;
  grid-column-gap: 5%;
  grid-template-areas: "griditem-left griditem-center griditem-right";
}

.grid--slideshow,
.grid--interaction {
  left: -5%;
  width: 110%;
  pointer-events: none;
}

.grid--slideshow {
  top: 17rem;
  transform: rotate(-8deg);
}

.grid--titles {
  align-items: center;
  text-align: center;
  cursor: default;
}

.titles-wrap {
  position: absolute;
  width: 100%;
  height: 100%;
  z-index: 1000;
  pointer-events: none;
  transform: rotate(16deg);
}

.grid__item {
  display: flex;
  justify-content: center;
  position: relative;
  pointer-events: none;
  /**grid-area: griditem-center;모든 사진이 중간으로 옴.**/
}

.grid__item--slide {
  background-color: yellow;
  flex-direction: column;
  width: 100%;
}

.grid__item--title {
  font-size: 13vw;
  margin: 0;
  font-family: ivymode, sans-serif;
  font-weight: 600;
  color: var(--color-title);
}

.grid__item--title span {
  display: inline-block;
}

.grid__item--center,
.grid__item--left,
.grid__item--right {
  opacity: 1;
  cursor: pointer;
}

.grid__item--left {
  grid-area: griditem-left;
}

.grid__item--center {
  grid-area: griditem-center;
}

.grid__item--right {
  grid-area: griditem-right;
}

.grid__item--cursor {
  pointer-events: auto;
}

.content-open .grid__item--cursor {
  display: none;
}

.number {
  font-size: 2rem;
  -webkit-text-stroke: 1.5px var(--color-number);
  text-stroke: 1.5px var(--color-number);
  -webkit-text-fill-color: transparent;
  text-fill-color: transparent;
  color: transparent;
  line-height: 1;
  margin: 0 0 0.5rem 0;
  font-weight: 700;
}
.grid__item--slide:nth-child(1) {
  background-color: rgb(152, 255, 121);
}
.grid__item--slide:nth-child(2) {
  background-color: rgb(121, 255, 237);
}
.grid__item--slide:nth-child(3) {
  background-color: rgb(121, 168, 255);
}
.grid__item--slide:nth-child(4) {
  background-color: rgb(195, 121, 255);
}

.img-wrap,
.img--content,
.caption,
.number,
.grid__item--title,
.grid__item--title span,
.revealer__inner,
.content__item-header-title {
  will-change: transform;
}

*,
*::after,
*::before {
  box-sizing: border-box;
}

.debug {
  transform: scale(0.5);
}

.debug * {
  outline: 1px solid red;
  opacity: 0.9;
}

body {
  --color-text: #5d5d5d;
  --color-bg: #e6e6ea;
  --color-link: #838288;
  --color-link-hover: #000;
  --color-number: #000;
  --color-title: #000;
  --color-caption: #000;
  --color-content: #000;
  --color-content-bg: var(--color-bg);
  --color-reveal-bg: var(--color-bg);
  color: var(--color-text);
  background-color: var(--color-bg);
  font-family: transat-text, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-rendering: optimizeLegibility;
}

.dark-mode {
  --color-text: #424242;
  --color-bg: #101010;
  --color-link: #bb3a3a;
  --color-link-hover: #fff;
  --color-number: #484848;
  --color-title: #bb3a3a;
  --color-caption: #484848;
  --color-content: #dadada;
  --color-content-title: #bb3a3a;
  --color-content-meta: #fff;
}

/** FRAME **/
@media screen and (min-width: 53em) {
  body {
    overflow: hidden;
  }
  .frame {
    position: fixed;
    bottom: 0;
    right: 0;
    padding: 1rem;
    transition: 0.3s opacity;
  }
  .content-open + .frame {
    opacity: 0;
    pointer-events: none;
  }
  .frame__title-wrap {
    display: flex;
    align-items: center;
    justify-content: flex-end;
  }
  .frame__title {
    margin: 0;
  }
  .frame__links {
    margin: 0 1.5rem;
  }
  .frame__mode {
    margin: 0;
  }
  .frame a {
    pointer-events: auto;
  }
  .grid {
    height: 100vh;
    grid-template-columns: repeat(3, calc((100% - 36vw) / 3));
    grid-column-gap: 18vw;
  }
  .grid--slideshow {
    top: 0;
  }
  .grid--interaction {
    grid-template-columns: repeat(3, calc(100% / 3));
    grid-column-gap: 0;
  }
  .number {
    font-size: 4.25vw;
  }
  .img-wrap {
    height: 35vw;
  }
  .img-wrap--content {
    height: 100%;
    grid-area: 1 / 2 / 2 / 3;
  }
  .content {
    top: 0;
    position: absolute;
    height: 100vh;
    width: 100%;
  }
  .content__item {
    height: 100vh;
    display: grid;
    align-items: center;
    grid-template-columns: 30% 40% 30%;
    grid-column-gap: 0;
  }

  .grid__item--cursor.grid__item--left::after,
  .grid__item--cursor.grid__item--right::after,
  .img-wrap--content::after {
    display: none;
  }
  .grid__item--cursor.grid__item--left {
    cursor: url("data:image/svg+xml,%3Csvg version='1.1' xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink' x='0px' y='0px' width='61px' height='44px' viewBox='0 0 61 44' style='enable-background:new 0 0 61 44;' xml:space='preserve'%3E %3Cpath d='M.613 21.671L21.584.7l5.642 5.642-11.74 11.74H60.45v7.978H15.487l11.74 11.739-5.643 5.642L.613 22.469a.57.57 0 0 1 0-.798z'/%3E %3C/svg%3E")
        30 22,
      sw-resize;
  }
  .grid__item--cursor.grid__item--center {
    cursor: url("data:image/svg+xml,%3Csvg version='1.1' xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink' x='0px' y='0px' width='52px' height='52px' viewBox='0 0 52 52' style='enable-background:new 0 0 52 52;' xml:space='preserve'%3E %3Cpath d='M29.889 30.05l-.036 21.361c-.222.213-7.654.213-7.876 0l-.007-21.358-21.52.007v-7.978l21.518.036L21.96.571h7.978l-.037 21.56 21.388.037c.213.222.213 7.654 0 7.876l-21.401.007z'/%3E %3C/svg%3E")
        26 26,
      crosshair;
  }
  .grid__item--cursor.grid__item--right {
    cursor: url("data:image/svg+xml,%3Csvg version='1.1' xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink' x='0px' y='0px' width='61px' height='44px' viewBox='0 0 61 44' style='enable-background:new 0 0 61 44;' xml:space='preserve'%3E %3Cpath d='M60.287 21.671L39.316.7l-5.642 5.642 11.74 11.74H.45v7.978h44.963l-11.74 11.739 5.643 5.642 20.971-20.972a.57.57 0 0 0 0-.798z'/%3E %3C/svg%3E")
        30 22,
      ne-resize;
  }
  .dark-mode .grid__item--cursor.grid__item--left {
    cursor: url("data:image/svg+xml,%3Csvg version='1.1' xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink' x='0px' y='0px' width='61px' height='44px' viewBox='0 0 61 44' style='enable-background:new 0 0 61 44;' xml:space='preserve'%3E %3Cpath fill='%23bb3a3a' d='M.613 21.671L21.584.7l5.642 5.642-11.74 11.74H60.45v7.978H15.487l11.74 11.739-5.643 5.642L.613 22.469a.57.57 0 0 1 0-.798z'/%3E %3C/svg%3E")
        30 22,
      sw-resize;
  }
  .dark-mode .grid__item--cursor.grid__item--center {
    cursor: url("data:image/svg+xml,%3Csvg version='1.1' xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink' x='0px' y='0px' width='52px' height='52px' viewBox='0 0 52 52' style='enable-background:new 0 0 52 52;' xml:space='preserve'%3E %3Cpath fill='%23bb3a3a' d='M29.889 30.05l-.036 21.361c-.222.213-7.654.213-7.876 0l-.007-21.358-21.52.007v-7.978l21.518.036L21.96.571h7.978l-.037 21.56 21.388.037c.213.222.213 7.654 0 7.876l-21.401.007z'/%3E %3C/svg%3E")
        26 26,
      crosshair;
  }
  .dark-mode .grid__item--cursor.grid__item--right {
    cursor: url("data:image/svg+xml,%3Csvg version='1.1' xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink' x='0px' y='0px' width='61px' height='44px' viewBox='0 0 61 44' style='enable-background:new 0 0 61 44;' xml:space='preserve'%3E %3Cpath fill='%23bb3a3a' d='M60.287 21.671L39.316.7l-5.642 5.642 11.74 11.74H.45v7.978h44.963l-11.74 11.739 5.643 5.642 20.971-20.972a.57.57 0 0 0 0-.798z'/%3E %3C/svg%3E")
        30 22,
      ne-resize;
  }
  .img-wrap--content {
    cursor: url("data:image/svg+xml,%3Csvg version='1.1' xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink' x='0px' y='0px' width='52px' height='52px' viewBox='0 0 52 52' style='enable-background:new 0 0 52 52;' xml:space='preserve'%3E %3Cpath  d='M20.921 26.67L5.791 41.75C5.485 41.743.23 36.488.224 36.18L15.32 21.073.098 5.86 5.74.22l15.19 15.24L36.161.22l5.64 5.64-15.27 15.22 15.097 15.15c-.006.307-5.262 5.562-5.569 5.568L20.921 26.67z'/%3E %3C/svg%3E")
        21 21,
      pointer;
  }
  .dark-mode .img-wrap--content {
    cursor: url("data:image/svg+xml,%3Csvg version='1.1' xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink' x='0px' y='0px' width='52px' height='52px' viewBox='0 0 52 52' style='enable-background:new 0 0 52 52;' xml:space='preserve'%3E %3Cpath fill='%23bb3a3a' d='M20.921 26.67L5.791 41.75C5.485 41.743.23 36.488.224 36.18L15.32 21.073.098 5.86 5.74.22l15.19 15.24L36.161.22l5.64 5.64-15.27 15.22 15.097 15.15c-.006.307-5.262 5.562-5.569 5.568L20.921 26.67z'/%3E %3C/svg%3E")
        21 21,
      pointer;
  }
}
</style>