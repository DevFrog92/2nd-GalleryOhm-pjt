<template>
  <div>
    <!-- ( 배경 : 어두운색) -->
    <div class="work_view">
      <div class="title">
        <h1 class="title_text">그 여름 버스에서</h1>
      </div>
      <div class="image_frame">
        <!-- 흰색 액자 이미지  -->
        <img class="work" src="../../assets/images/t.jpeg" />
      </div>
      <div class="image">
        <!-- 프레임 없는 큰~~ 이미지 -->
        <img class="work2" src="../../assets/images/t.jpeg" />
      </div>
    </div>
    <!-- 작품 정보 (배경 : 밝은색)-->
    <div class="info">
      <!-- 왼쪽 -->
      <div class="left">
        <!-- 작은 이미지 -->
        <img class="frame" src="../../assets/images/t.jpeg" />
      </div>
      <!-- 오른쪽 -->
      <div class="right">
        <!-- 작품 이름 -->
        <h1 class="first">그 여름 버스에서</h1>
        <!-- 작가 이름-->
        <h1 class="name">By 신모래</h1>
        <!-- 작품 설명 -->
        <h1 class="desc">
          Giclée fine art print on premium archival smooth paper,
        </h1>
        <h1 class="desc">290 gsm, 21.5 mil. Print includes a white border.</h1>
        <h1 class="desc">
          Solid-wood framing options available View more information
        </h1>
        <!-- 작품 사이즈 -->
        <h1 class="size">1024 x 1024</h1>
        <!-- $ 버튼 -->
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
</template>

<style scoped>
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);

.work_view {
  height: 296.3vh;
  width: 100%;
  background-color: #fcf1e7;
}
.work_view .title {
  padding-top: 10%;
  padding-bottom: 10%;
}
.work_view .title_text {
  /* transform: scale(6); */
  /* padding-top: 10%; */
  font-size: 200px;
  color: black;
  font-family: "Hanna", sans-serif;
}
.work {
}
.work2 {
  width: 100%;
}
.image_frame {
  background-size: cover;
  position: relative;
}
.image_frame img {
  height: 70vh;
  border: 15px white solid;
}
.image {
  padding-top: 20%;
}
.info {
  height: 55vh;
  padding-top: 5%;
}
.info .left {
  display: inline-block;
  float: left;
  padding-left: 10%;
  width: 50%;
  height: 100%;
}
.frame {
  margin-top: 5%;
  width: 60%;
  border: 15px white solid;
}
.info {
  background-color: #faf2f2;

}
.info .right {
  display: inline-block;
  float: right;
  padding-right: 20%;
  width: 50%;
  height: 100%;
    position: relative;
}
.info .first {
  margin-top: 12%;
  font-size: 40px;
}
.info .nickname {
}
.btn {
    position:relative;
}
.info .btn {
  position: absolute;
  margin-top: 7%;
  margin-left: 5%;
  /* background-color: red; */
  width: 50%;
  /* height: 15%; */
}
.info .right .lbtn {
margin-left: 10%;
}
.lbtn {
    position: relative;
    /* margin-left: 20%; */
}
.info .right .btn .scrap {
    margin-left: 20%;
}
.info .name {
    padding-top: 2%;
    padding-bottom: 2%;
  font-size: 20px;
}
.info .desc {
    padding-bottom: 1%;
  font-size: 15px;
}
.info .size {
    margin-top: 1%;
  font-size: 15px;
}
.info h1 {
  /* transform: scale(1.1); */
  color: black;
  font-family: "Hanna", sans-serif;
}
</style>

<style lang="scss" scoped>
* {
 margin: 0;
 padding: 0;
 box-sizing: border-box;
}

@import url('https://fonts.googleapis.com/css?family=Lato:100,300,400');
@import url('https://fonts.googleapis.com/css?family=Roboto:100');
@import url('https://fonts.googleapis.com/earlyaccess/hanna.css');

@mixin button($bcolor,
$url,
$x1,
$y1,
$bor,
$col) {
 background: $bcolor;
 -webkit-mask: url($url);
 mask: url($url);
 -webkit-mask-size: $x1 $y1;
 mask-size: $x1 $y1;
 border: $bor;
 color: $col;
}

.header {
 text-align: center;
 font-family: 'Roboto', sans-serif;
 font-size: 34px;
 margin-top: 12vh;
}

.footer {
 text-align: center;
 font-family: 'Lato', sans-serif;
 font-weight: 300;
 font-size: 20px;
 margin-top: 15vh;
}

.button-container-1 {
 position: relative;
 width: 100px;
 height: 50px;
 margin-left: auto;
 margin-right: auto;
 margin-top: 6vh;
 overflow: hidden;
 border: 1px solid;
 font-family: 'Lato', sans-serif;
 font-weight: 300;
 font-size: 20px;
 transition: 0.5s;
 letter-spacing: 1px;
  border-radius: 8px;

 button {
  width: 101%;
  height: 100%;
  font-family: 'Lato', sans-serif;
  font-weight: 300;
  font-size: 22px;
  letter-spacing: 1px;
  font-weight: bold;

  @include button(#000,
  "https://raw.githubusercontent.com/robin-dela/css-mask-animation/master/img/nature-sprite.png",
  2300%,
  100%,
  none,
  #fff);
  cursor: pointer;
  -webkit-animation: ani2 0.7s steps(22) forwards;
  animation: ani2 0.7s steps(22) forwards;

  &:hover {
   -webkit-animation: ani 0.7s steps(22) forwards;
   animation: ani 0.7s steps(22) forwards;
  }
 }
}

.button-container-2 {
 position: relative;
 width: 100px;
 height: 50px;
 margin-left: auto;
 margin-right: auto;
 margin-top: 7vh;
 overflow: hidden;
 border: 1px solid #000;
 font-family: 'Lato', sans-serif;
 font-weight: 300;
 transition: 0.5s;
 letter-spacing: 1px;
 border-radius: 8px;

 button {
  width: 101%;
  height: 100%;
  font-family: 'Lato', sans-serif;
  font-weight: 300;
  font-size: 22px;
  letter-spacing: 1px;
  font-weight: bold;

  @include button(#000, "https://raw.githubusercontent.com/robin-dela/css-mask-animation/master/img/urban-sprite.png",
  3000%,
  100%,
  none,
  #fff);
  cursor: pointer;
  -webkit-animation: ani2 0.7s steps(29) forwards;
  animation: ani2 0.7s steps(29) forwards;

  &:hover {
   -webkit-animation: ani 0.7s steps(29) forwards;
   animation: ani 0.7s steps(29) forwards;
  }
 }
}


.mas {
    position: absolute;
    color: #000;
    text-align: center;
    width: 101%;
    // font-family: 'Lato', sans-serif;
      font-family: "Hanna", sans-serif;
    font-weight: 300;
    position: absolute;
    font-size: 22px;
    margin-top: 8px;
    overflow: hidden;
    font-weight: bold;

}
.mas2 {
    position: absolute;
    color: #000;
    text-align: center;
    width: 101%;
    // font-family: 'Lato', sans-serif;
      font-family: "Hanna", sans-serif;
    font-weight: 300;
    position: absolute;
    font-size: 22px;
    margin-top: 8px;
    overflow: hidden;
    font-weight: bold;
    margin-left: 20%;
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

a{
  color:#00ff95;
}

.button-container-3 {
 position: absolute;
 width: 100px;
 height: 50px;
 margin-left: 68%;
 margin-right: auto;
//  margin-top: 8vh;
 overflow: hidden;
 border: 1px solid #000;
//  font-family: 'Lato', sans-serif;
  font-family: "Hanna", sans-serif;
 font-weight: 300;
 transition: 0.5s;
 letter-spacing: 1px;
 border-radius: 8px;

 button {
  width: 101%;
  height: 100%;
//   font-family: 'Lato', sans-serif;
    font-family: "Hanna", sans-serif;
  font-weight: 300;
  font-size: 22px;
  letter-spacing: 1px;
  font-weight: bold;


  @include button(#000, "https://raw.githubusercontent.com/pizza3/asset/master/natureSmaller.png",
  7100%,
  100%,
  none,
  #fff);
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
 margin-left: 40%;
 margin-right: auto;
//  margin-top: 8vh;
 overflow: hidden;
 border: 1px solid #000;
//  font-family: 'Lato', sans-serif;
  font-family: "Hanna", sans-serif;
 font-weight: 300;
 transition: 0.5s;
 letter-spacing: 1px;
 border-radius: 8px;

 button {
  width: 101%;
  height: 100%;
//   font-family: 'Lato', sans-serif;
  font-family: "Hanna", sans-serif;
  font-weight: 300;
  font-size: 22px;
  letter-spacing: 1px;
  font-weight: bold;


  @include button(#000, "https://raw.githubusercontent.com/pizza3/asset/master/natureSmaller.png",
  7100%,
  100%,
  none,
  #fff);
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