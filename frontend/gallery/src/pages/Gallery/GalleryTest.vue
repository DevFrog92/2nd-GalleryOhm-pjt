<template>
<div>      
<div class="btn">
  <router-link to="/test/GalleryO"><b-button variant="success">메인 갤러리</b-button></router-link>
</div>

  <router-link to="/test/GalleryB"><b-button class="b3" variant="warning">서브 갤러리</b-button></router-link>
  <router-link to="/test/GalleryJ"><b-button class="b2" variant="danger">레드 갤러리</b-button></router-link>
</div>
    
</template>

<style scoped>
.btn {
    width:300px;
    height:300px;
    position:absolute;
    left:50%;
    top:50%;
    margin-left:-350px;
    margin-top:-150px;
}
.b2 {
    margin-left: 1%;
}
.b3 {
    margin-left: 30%;
}
</style>