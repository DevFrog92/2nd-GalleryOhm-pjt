<template>
  <div>
    <div class="btn">
      <router-link to="/test/GalleryO"
        ><b-button variant="success">메인 갤러리</b-button></router-link
      >
    </div>

    <router-link to="/test/GalleryB"
      ><b-button class="b3" variant="warning"
        >서브 갤러리</b-button
      ></router-link
    >
    <router-link to="/test/GalleryJ"
      ><b-button class="b2" variant="danger">레드 갤러리</b-button></router-link
    >

    <div class="btn2">
      <router-link to="/test/resize1"
        ><b-button>리사이징 1</b-button></router-link
      >
    </div>
    <router-link to="/test/resize2"
      ><b-button class="b4">리사이징 2</b-button></router-link
    >
    <router-link to="/test/resize3"
      ><b-button class="b5">리사이징 3</b-button></router-link
    >
  </div>
</template>

<style scoped>
.btn2 {
  width: 10px;
  height: 10px;
  position: absolute;
  left: 50%;
  top: 10%;
  margin-left: -200px;
}
.btn {
  width: 300px;
  height: 300px;
  position: absolute;
  left: 50%;
  top: 50%;
  margin-left: -350px;
  margin-top: -150px;
}
.b2 {
  margin-left: 1%;
}
.b3 {
  margin-left: 30%;
}
.b4 {
  margin-left: 1%;
  top: 10%;
}
.b5 {
  margin-left: 30%;
  top: 10%;
}
</style>