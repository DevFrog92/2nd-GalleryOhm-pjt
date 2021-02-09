<template>
  <div class="detailPage">
    <!-- ( 배경 : 어두운색) -->
    <div class="preview">
      <div class="container">
        <div class="work_view">
          <!-- 첫 div 정보 -->
          <div class="title">
            <!-- title -->
            <h1 lass="title_text">TITLE</h1>
            <!-- 액자 이미지 -->
            <div class="title_img">
              <img class="work" src="https://source.unsplash.com/random/1" />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);

.preview {
  background-color: gray;
}

.container {
  margin: 0 auto;
  padding: 0;
  width: 100%;
}

.work_view {
  padding-top: 35vh;
  padding-bottom: 35vh;
}

.work_view .title_img {
  margin-top: 35vh;
  width: 100%;
  height: auto;
}

.work_view .title_img img {
  border: 10px white solid;
}

/* .work_view .title {
  padding-top: 20%;
  padding-bottom: 10%;
}
.work_view .title_text {
  font-size: 4rem;
  color: #fcf1e7;
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
} */
</style>
