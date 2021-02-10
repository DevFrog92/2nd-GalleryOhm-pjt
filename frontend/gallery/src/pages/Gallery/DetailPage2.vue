<template>
  <div class="detailPage">
    <!-- ( 배경 : 어두운색) -->
    <div class="preview">
      <div class="container">
        <div class="work_view">
          <!-- 첫 div 정보 -->
          <div class="title">
            <!-- title -->
            <h1 class="title_text">TITLE</h1>
            <!-- 액자 이미지 -->
            <div class="title_img">
              <img class="work" :src="imgSrc" />
            </div>
          </div>
        </div>
      </div>
    </div>
    <div
      class="pullImg type-b"
      :style="'background-image: url(' + imgSrc + ');'"
    ></div>
    <div class="detail">
      <div class="img_box">
        <div class="img">
          <img :src="imgSrc" alt="" />
        </div>
      </div>
      <div class="info_box">
        <div class="info">
          <!-- 작품 이름 -->
          <div class="first">그 여름 버스에서</div>
          <!-- 작가 이름-->
          <div class="name">By 신모래</div>
          <!-- 작품 설명 -->
          <div class="desc">
            Giclée fine art print on premium archival smooth paper,
          </div>
          <div class="desc">
            290 gsm, 21.5 mil. Print includes a white border.
          </div>
          <div class="desc">
            Solid-wood framing options available View more information
          </div>
          <!-- 작품 사이즈 -->
          <div class="size">1024 x 1024</div>
          <div class="btn">
            <div class="button-container-3">
              <span class="mas">scrap</span>
              <button type="button" name="Hover">scrap</button>
            </div>
            <div class="lbtn button-container-3bb">
              <span class="mas">cost</span>
              <button type="button" name="Hover">cost</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      imgSrc: "",
    };
  },
  mounted() {
    this.imgSrc = "https://source.unsplash.com/random/1";
  },
};
</script>

<style scoped>
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);

.preview {
  background-color: gray;
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
}

.pullImg {
  height: 90vh;
  width: 100%;
  overflow: hidden;

  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
}

.detail {
  height: 100vh;
  background-color: gray;
  display: flex;
  justify-content: space-around;
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
  /* padding-right: 5%; */
}

.detail .img_box .img img {
  display: block;
  margin-left: auto;
  margin-right: auto;
  border: 10px white solid;
  height: 50vh;
  margin-top: 25vh;
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
}

.detail .info_box .info .desc {
  margin-top: 1vh;
  font-size: 15px;
}

.detail .info_box .btn {
  margin-top: 3vh;
}
</style>

<style lang="scss" scoped>
@import url("https://fonts.googleapis.com/css?family=Lato:100,300,400");
@import url("https://fonts.googleapis.com/css?family=Roboto:100");
@import url("https://fonts.googleapis.com/earlyaccess/hanna.css");

@mixin button($bcolor, $url, $x1, $y1, $bor, $col) {
  background: $bcolor;
  -webkit-mask: url($url);
  mask: url($url);
  -webkit-mask-size: $x1 $y1;
  mask-size: $x1 $y1;
  border: $bor;
  color: $col;
}

.mas {
  position: absolute;
  color: #000;
  text-align: center;
  width: 101%;
  font-family: "Hanna", sans-serif;
  font-weight: 300;
  position: absolute;
  font-size: 22px;
  margin-top: 8px;
  overflow: hidden;
  font-weight: bold;
}

@-webkit-keyframes ani {
  from {
    -webkit-mask-position: 0 0;
    mask-position: 0 0;
  }

  to {
    -webkit-mask-position: 100% 0;
    mask-position: 100% 0;
  }
}

@keyframes ani {
  from {
    -webkit-mask-position: 0 0;
    mask-position: 0 0;
  }

  to {
    -webkit-mask-position: 100% 0;
    mask-position: 100% 0;
  }
}

@-webkit-keyframes ani2 {
  from {
    -webkit-mask-position: 100% 0;
    mask-position: 100% 0;
  }

  to {
    -webkit-mask-position: 0 0;
    mask-position: 0 0;
  }
}

@keyframes ani2 {
  from {
    -webkit-mask-position: 100% 0;
    mask-position: 100% 0;
  }

  to {
    -webkit-mask-position: 0 0;
    mask-position: 0 0;
  }
}

.button-container-3 {
  position: absolute;
  width: 100px;
  height: 50px;
  margin-right: auto;
  margin-left: 50%;
  overflow: hidden;
  border: 1px solid #000;
  font-family: "Hanna", sans-serif;
  font-weight: 300;
  transition: 0.5s;
  letter-spacing: 1px;
  border-radius: 8px;

  button {
    width: 101%;
    height: 100%;
    font-family: "Hanna", sans-serif;
    font-weight: 300;
    font-size: 22px;
    letter-spacing: 1px;
    font-weight: bold;

    @include button(
      #000,
      "https://raw.githubusercontent.com/pizza3/asset/master/natureSmaller.png",
      7100%,
      100%,
      none,
      #fff
    );
    cursor: pointer;
    -webkit-animation: ani2 0.7s steps(70) forwards;
    animation: ani2 0.7s steps(70) forwards;

    &:hover {
      -webkit-animation: ani 0.7s steps(70) forwards;
      animation: ani 0.7s steps(70) forwards;
    }
  }
}

.button-container-3bb {
  position: absolute;
  width: 100px;
  height: 50px;
  margin-right: auto;
  margin-left: 25%;
  overflow: hidden;
  border: 1px solid #000;
  font-family: "Hanna", sans-serif;
  font-weight: 300;
  transition: 0.5s;
  letter-spacing: 1px;
  border-radius: 8px;

  button {
    width: 101%;
    height: 100%;
    font-family: "Hanna", sans-serif;
    font-weight: 300;
    font-size: 22px;
    letter-spacing: 1px;
    font-weight: bold;

    @include button(
      #000,
      "https://raw.githubusercontent.com/pizza3/asset/master/natureSmaller.png",
      7100%,
      100%,
      none,
      #fff
    );
    cursor: pointer;
    -webkit-animation: ani2 0.7s steps(70) forwards;
    animation: ani2 0.7s steps(70) forwards;

    &:hover {
      -webkit-animation: ani 0.7s steps(70) forwards;
      animation: ani 0.7s steps(70) forwards;
    }
  }
}
</style>