<template>
  <div>
      <!-- ( 배경 : 어두운색) -->
      <div class="work_view">
    <div class="title">
      <h1 class="title_text">TITLE</h1>
    </div>
    <div class="image_frame">
        <!-- 흰색 액자 이미지  -->
      <img class="work" src="../../assets/images/t.jpeg"/>
    </div>
    <div class="image">
        <!-- 프레임 없는 큰~~ 이미지 -->
      <img class="work2" src="../../assets/images/t.jpeg"/>
    </div>
    </div>
    <!-- 작품 정보 (배경 : 밝은색)-->
    <div class="info">
      <!-- 왼쪽 -->
      <div class="left">
        <!-- 작은 이미지 -->
        <img class="frame" src="../../assets/images/t.jpeg"/>
      </div>
      <!-- 오른쪽 -->
      <div class="right">
          <!-- 작품 이름 -->
          <h1 class="first">귀염둥이 발레리나</h1>
          <!-- 작가 이름-->
          <h1 class="name">빈</h1>
          <!-- 작품 설명 -->
          <h1 class="desc">Giclée fine art print on premium archival smooth paper, </h1>
              <h1 class="desc">290 gsm, 21.5 mil. Print includes a white border. </h1>
             <h1 class="desc">Solid-wood framing options available View more information</h1>
          <!-- 작품 사이즈 -->
          <h1 class="size">1024 x 1024</h1>
          <!-- $ 버튼 -->
      </div>
    </div>
  </div>
</template>

<style scoped>
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);

.work_view {
    height: 297.2vh;
    width: 100%;
    background-color:#f1d1d1;
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
}
.info .first {
    margin-top: 17%;
    font-size: 40px;
}
.info .nickname {
}
.info .name {
    font-size: 20px;
}
.info .desc {
    font-size: 15px;
}
.info .size {
    font-size: 10px;
}
.info h1 {
    /* transform: scale(1.1); */
    color: black;
    font-family: "Hanna", sans-serif;
}
</style>