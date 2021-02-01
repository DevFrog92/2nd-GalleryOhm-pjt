<template>
  <body class="galley-render">
 <header><h1>Carousel</h1></header>
  <div class="carousel">
    <div class="carousel__body">
      <div class="carousel__prev"><i class="icon-left-open"></i></div>
      <div class="carousel__next"><i class="icon-right-open"></i></div>
      <div class="carousel__slider">
        <div class="carousel__slider__item">
          <div class="item__3d-frame">
            <div class="item__3d-frame__box item__3d-frame__box--front">
              <img src="/image/01.jpg" alt="">
            </div>
            <div class="item__3d-frame__box item__3d-frame__box--left"></div>
            <div class="item__3d-frame__box item__3d-frame__box--right"> </div>
          </div>
        </div>
        <div class="carousel__slider__item">
          <div class="item__3d-frame">
            <div class="item__3d-frame__box item__3d-frame__box--front">
              <img src="/image/02.jpg" alt="">

            </div>
            <div class="item__3d-frame__box item__3d-frame__box--left"></div>
            <div class="item__3d-frame__box item__3d-frame__box--right"> </div>
          </div>
        </div>
        <div class="carousel__slider__item">
          <div class="item__3d-frame">
            <div class="item__3d-frame__box item__3d-frame__box--front">
              <img src="/image/03.jpg" alt="">

            </div>
            <div class="item__3d-frame__box item__3d-frame__box--left"></div>
            <div class="item__3d-frame__box item__3d-frame__box--right"> </div>
          </div>
        </div>
        <div class="carousel__slider__item">
          <div class="item__3d-frame">
            <div class="item__3d-frame__box item__3d-frame__box--front">
              <img src="/image/04.jpg" alt="">

            </div>
            <div class="item__3d-frame__box item__3d-frame__box--left"></div>
            <div class="item__3d-frame__box item__3d-frame__box--right"> </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</template>

<script>
import '../assets/css/GalleryRenderPage.css'
import '../assets/fontello/css/fontello.css'
export default {
  data (){
    return {
      width:20,
      height:20,
      totalWidth:20,
      margin:20,
      interval:4000,
      intervalTime:4000,
      currIndex:0,
      classNames:{
        carousel:'',
        items:[],
        slider:'',
        prevBtn:'',
        nextBtn:'',
      }
    }
  },
  methods:{
    init:function(){

      this.classNames.carousel = document.querySelector('.carousel');
      this.classNames.items = document.querySelectorAll('.carousel__slider__item');
      this.classNames.slider = document.querySelector('.carousel__slider');
      this.classNames.prevBtn = document.querySelectorAll('.carousel__prev');
      this.classNames.nextBtn = document.querySelectorAll('.carousel__next');
      this.resize();
      this.move(Math.floor(this.classNames.items.length / 2));
      this.bindEvents();
      this.timer();
    },
    resize:function(){ 
      this.width = window.innerHeight *.5
//    width=275;
      // width : 275px
      this.height = window.innerHeight *.5
//    height=275;
      // height : 585px
      this.totalWidth = this.width * this.classNames.items.length;
      //  1100px
      this.classNames.slider.style.width = this.totalWidth + "px";
      // 
 
      for(var i = 0; i < this.classNames.items.length; i++) {
          let item = this.classNames.items[i];
          item.style.width = (this.width - (this.margin * 2)) + "px";
          item.style.height = this.height + "px";
      }
      // console.log('resize pass');
    },
    move:function(index){
      if(index < 1) index = this.classNames.items.length;
      if(index > this.classNames.items.length) index = 1;
      
      this.currIndex = index;
 
      for(var i = 0; i < this.classNames.items.length; i++) {
          let item = this.classNames.items[i],
              box = item.getElementsByClassName('item__3d-frame')[0];
          if(i == (index - 1)) {
              item.classList.add('carousel__slider__item--active');
              box.style.transform = "perspective(1200px)"; 
          } else {
            item.classList.remove('carousel__slider__item--active');
              box.style.transform = "perspective(1200px) rotateY(" + (i < (index - 1) ? 40 : -40) + "deg)";
          }
      }
      
      this.classNames.slider.style.transform = "translate3d(" + ((index * -this.width) + (this.width / 2) + window.innerWidth / 2) + "px, 0, 0)";
      // console.log('move pass')
    },
    timer:function(){
      clearInterval(this.interval);    
      this.interval = setInterval(() => {
        this.move(++this.currIndex);
      }, this.intervalTime);  
    },
    prev:function(){
      this.move(--this.currIndex);
      this.timer();
    },
    next:function(){
      this.move(++this.currIndex);
      this.timer();
    },
    bindEvents:function(){
      window.onresize = this.resize;
      this.classNames.prevBtn[0].addEventListener('click', () => { this.prev(); });
      this.classNames.nextBtn[0].addEventListener('click', () => { this.next(); }); 
    }

  },
  mounted(){
    this.init();
  }
}
</script>

<style>

</style>