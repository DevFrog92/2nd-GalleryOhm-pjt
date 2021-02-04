<template>
 <div class="gallery__render">
   <div id="lamp">
    <input type="radio" name="switch" value="on" checked="checked" />
    <input type="radio" name="switch" value="off"  />
    <div class="lamp">
      <div class="gonna-give-light"></div>
    </div>
  </div>
  <div class="close__btn" @click="goBack">
    <i class="icon-back"></i>
  </div>
  <!-- <div class="qurating">
    <i class="icon-mic-1"></i>
  </div> -->
  <div class="main__carousel">
    <div class="carousel__body">
      <div class="carousel__prev"><i class="icon-left-open"></i></div>
      <div class="carousel__next"><i class="icon-right-open"></i></div>
      <div class="carousel__slider">
        <div class="carousel__slider__item">
          <div class="item__3d-frame">
            <div class="item__3d-frame__box item__3d-frame__box--front">
              <img class="gallery__image" data-index="1" src="../assets/images/1.png" alt="">
            </div>
            <div class="item__3d-frame__box item__3d-frame__box--left"></div>
            <div class="item__3d-frame__box item__3d-frame__box--right"> </div>
          </div>
          <div class="item__title">
          <h1>도라에몽</h1>            
          </div>
        </div>
        <div class="carousel__slider__item">
          <div class="item__3d-frame">
            <div class="item__3d-frame__box item__3d-frame__box--front">
              <img class="gallery__image" data-index="2" src="../assets/images/1.png" alt="">
            </div>
            <div class="item__3d-frame__box item__3d-frame__box--left"></div>
            <div class="item__3d-frame__box item__3d-frame__box--right"> </div>
          </div>
          <div class="item__title">
          <h1>도라에몽</h1>            
          </div>
        </div>
        <div class="carousel__slider__item">
          <div class="item__3d-frame">
            <div class="item__3d-frame__box item__3d-frame__box--front">
              <img class="gallery__image" data-index="3" src="../assets/images/2.png" alt="">

            </div>
            <div class="item__3d-frame__box item__3d-frame__box--left"></div>
            <div class="item__3d-frame__box item__3d-frame__box--right"> </div>
          </div>
                <div class="item__title">
          <h1>도라에몽</h1>            
          </div>
        </div>
        <div class="carousel__slider__item">
          <div class="item__3d-frame">
            <div class="item__3d-frame__box item__3d-frame__box--front">
              <img src="../assets/images/3.png" alt="">

            </div>
            <div class="item__3d-frame__box item__3d-frame__box--left"></div>
            <div class="item__3d-frame__box item__3d-frame__box--right"> </div>
          </div>
                <div class="item__title">
          <h1>도라에몽</h1>            
          </div>
        </div>
        <div class="carousel__slider__item">
          <div class="item__3d-frame">
            <div class="item__3d-frame__box item__3d-frame__box--front">
              <img src="../assets/images/4.png" alt="">

            </div>
            <div class="item__3d-frame__box item__3d-frame__box--left"></div>
            <div class="item__3d-frame__box item__3d-frame__box--right"> </div>
          </div>
                <div class="item__title">
          <h1>도라에몽</h1>            
          </div>
        </div>
        <div class="carousel__slider__item">
          <div class="item__3d-frame">
            <div class="item__3d-frame__box item__3d-frame__box--front">
              <img src="../assets/images/1.png" alt="">

            </div>
            <div class="item__3d-frame__box item__3d-frame__box--left"></div>
            <div class="item__3d-frame__box item__3d-frame__box--right"> </div>
          </div>
                <div class="item__title">
          <h1>도라에몽</h1>            
          </div>
        </div>
        <div class="carousel__slider__item">
          <div class="item__3d-frame">
            <div class="item__3d-frame__box item__3d-frame__box--front">
              <img src="../assets/images/1.png" alt="">

            </div>
            <div class="item__3d-frame__box item__3d-frame__box--left"></div>
            <div class="item__3d-frame__box item__3d-frame__box--right"> </div>
          </div>
                <div class="item__title">
          <h1>도라에몽</h1>            
          </div>
        </div>
        <div class="carousel__slider__item">
          <div class="item__3d-frame">
            <div class="item__3d-frame__box item__3d-frame__box--front">
              <img src="../assets/images/1.png" alt="">

            </div>
            <div class="item__3d-frame__box item__3d-frame__box--left"></div>
            <div class="item__3d-frame__box item__3d-frame__box--right"> </div>
          </div>
                <div class="item__title">
          <h1>도라에몽</h1>            
          </div>
        </div>
        <div class="carousel__slider__item">
          <div class="item__3d-frame">
            <div class="item__3d-frame__box item__3d-frame__box--front">
              <img src="../assets/images/1.png" alt="">

            </div>
            <div class="item__3d-frame__box item__3d-frame__box--left"></div>
            <div class="item__3d-frame__box item__3d-frame__box--right"> </div>
          </div>
                <div class="item__title">
          <h1>도라에몽</h1>            
          </div>
        </div>
      </div>
    </div>
  </div>

</div>
</template>

<script>
import '../assets/css/GalleryRenderPage.css'
import init from '../assets/js/GalleryRenderPage.js'
import '../assets/fontello/css/fontello.css'
export default {
  data (){
    return {
    }
  },
  mounted(){
    init.init();
  },
  methods:{
    goBack(){
      this.$router.go(-1);
    }
  }
}
</script>

<style>

</style>