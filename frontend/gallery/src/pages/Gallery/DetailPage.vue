<template>
  <div>
      <!-- ( 배경 : 어두운색) -->
      <div class="work_view">
    <div class="title">
      <h1 lass="title_text">TITLE</h1>
    </div>
    <div class="image_frame">
        <!-- 흰색 액자 이미지  -->
      <img class="work" src="../../assets/images/4.png"/>
    </div>
    <div class="image">
        <!-- 프레임 없는 큰~~ 이미지 -->
      <img class="work2" src="../../assets/images/4.png"/>
    </div>
    </div>
    <!-- 작품 정보 (배경 : 밝은색)-->
    <div class="info">
      <!-- 왼쪽 -->
      <div class="left">
        <!-- 작은 이미지 -->
        <img />
      </div>
      <!-- 오른쪽 -->
      <div class="right">
          <!-- 작품 이름 -->
          <h1>귀염둥이 발레리나</h1>
          <!-- 작가 이름-->
          <h1>빈</h1>
          <!-- 작품 설명 -->
          <h1>예쁘냐</h1>
          <!-- 작품 사이즈 -->
          <h1>1024 x 1024</h1>
          <!-- $ 버튼 -->
      </div>
    </div>
  </div>
</template>

<style scoped>
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);

.work_view {
    height: 170vh;
    background-color:#175711;
}
.work_view .title {
    padding-top: 20%;
    padding-bottom: 10%;
}
.work_view .title_text {
    font-size: 4rem;
    color: #FCF1E7;
    font-family: "Hanna", sans-serif;
}
.image_frame {
background-size: cover;
  position: relative;
  }
.image_frame img {
    height: 40vh;
    border: 10px white solid;
}
.image {
    padding-top: 20%;
}
.info {
    height: 50vh;
}
.info .left {
    width: 50vw;
}
.info .right {
    left: 50%;
}
</style>