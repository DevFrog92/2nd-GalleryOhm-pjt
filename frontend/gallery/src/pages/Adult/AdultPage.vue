<template >
    <div>
        <div class="welcome">
            <div class="dark">
                <div class="door-open"></div>
                <div class="door"></div>
            </div>
        </div>
        <!-- <div class="content"> -->
<div>
<div class="example-3d">
    <swiper class="swiper" :options="swiperOption">
        <swiper-slide v-for="sl in sampleList" :key="sl.id">
            <img :src="sl.sample"></swiper-slide>
            <div class="swiper-pagination" slot="pagination"></div>
        </swiper>
    </div>

<!-- 이전 페이지 -->
  <!-- <div class="swiper-button-prev"></div>  -->
  <!-- 다음 페이지 -->
    <!-- <div class="swiper-button-next"></div>  -->

  <div class="example-3d">
    <swiper class="swiper" :options="swiperOption">
        <swiper-slide v-for="sl in sampleList2" :key="sl.id">
            <img :src="sl.sample"></swiper-slide>
            <div class="swiper-pagination" slot="pagination"></div>
        </swiper>
  </div>
</div>
        <!-- </div> -->
    </div>
</template>

<script lang="ts">  
  import { Swiper, SwiperSlide } from 'vue-awesome-swiper'
//   import 'swiper/css/swiper.css'
import 'swiper/swiper-bundle.css' 
  export default {
    name: 'swiper-example-3d-coverflow',
    title: '3D Coverflow effect',
    components: {
      Swiper,
      SwiperSlide
    },
    data() {
      return {
        // swiperOption: {
        //   effect: 'coverflow',
        //   grabCursor: true,
        //   centeredSlides: true,
        //   slidesPerView: 'auto',
        //   coverflowEffect: {
        //     rotate: 50,
        //     stretch: 0,
        //     depth: 100,
        //     modifier: 1,
        //     slideShadows : true
        //   },
        //   pagination: {
        //     el: '.swiper-pagination'
        //   }
        // }

        sampleList:[{ sample : require('../../assets/images/d1.png'), }, { sample : require('../../assets/images/d2.png'), }, { sample : require('../../assets/images/d3.png'), }, { sample : require('../../assets/images/d5.png'), }, { sample : require('../../assets/images/d6.jpg'), } , { sample : require('../../assets/images/d7.png'), } , { sample : require('../../assets/images/d6.jpg'), }], sampleList2:[{ sample : require('../../assets/images/d6.jpg'), }, { sample : require('../../assets/images/d7.png'), }, { sample : require('../../assets/images/d5.png'), } ,{ sample : require('../../assets/images/d3.png'), }, { sample : require('../../assets/images/d2.png'), }, { sample : require('../../assets/images/d1.png'), }],


        swiperOption: {
        slidesPerView: 5,
        spaceBetween: 50,
        pagination: {
          el: ".swiper-pagination",
          clickable: true
        },
        breakpoints: {
          1024: {
            slidesPerView: 4,
            spaceBetween: 40
          },
          768: {
            slidesPerView: 3,
            spaceBetween: 30
          },
          640: {
            slidesPerView: 2,
            spaceBetween: 20
          },
          320: {
            slidesPerView: 1,
            spaceBetween: 10
          }
        }
      }
      }
    }
  }
</script>

<style lang="scss" scoped>
  .example-3d {
    width: 100%;
    height: 400px;
    padding-top: 50px;
    padding-bottom: 50px;
    background-color: rgb(158, 22, 22);
  }

  .swiper {
    height: 100%;
    width: 100%;

    .swiper-slide {
      display: flex;
      justify-content: center;
      align-items: center;
      width: 300px;
      height: 300px;
      text-align: center;
      font-weight: bold;
    //   font-size: $font-size-huge * 2;
    font-size: 20px;
      background-color: #2C8DFB;
      background-position: center;
      background-size: cover;
      color: white;
    }

    .swiper-pagination {
      /deep/ .swiper-pagination-bullet.swiper-pagination-bullet-active {
        background-color: white;
      }
    }
  }

  .swiper-container {
    width:100%;
    height:auto;
}
.swiper-slide {
    width:auto;
    height:auto;
}
.swiper-container img {
    width:100%;
    height:auto;
}
</style>

<style lang="sass" scoped>

@import url(https://fonts.googleapis.com/css?family=Open+Sans:600,800)
@import url(https://fonts.googleapis.com/css?family=Montserrat:700)

html, body
	background-color: #36465D
	color: #eee
	font-family: "Open Sans", Arial, sans-serif

p, h1
	margin: 6px 0
	text-shadow: 1px 1px 1px rgba(0,0,0,0.5)

h1
	font-size: 44px
	font-family: "Montserrat", Arial, sans-serif

.content
	text-align: center
	margin: auto
	margin-top: calc(50vh - 50px)
	width: 50%

/* super duper ugly code but it works and its a proof of concept */
$door-time: 2.5s
$zoom-time: 3s
$fadeout-time: 2s
.welcome
	position: absolute
	width: 100%
	height: 100%
	top: 0
	left: 0
	z-index: 2
	opacity: 1
	overflow: hidden
	animation: 0s remove linear 5s
	animation-fill-mode: forwards
	.dark
		position: absolute
		width: 100%
		height: 100%
		z-index: 4
		background: #000
		animation: $fadeout-time fadeout ease $zoom-time
		animation-fill-mode: forwards
		.door-open
			position: absolute
			background: #fff
			height: 500px
			width: 300px
			top: calc(50% - 250px)
			left: calc(50% - 150px)
			z-index: 5
			animation: $zoom-time growfade ease-in $door-time
			animation-fill-mode: forwards
		.door
			position: absolute
			background: #000
			height: 600px
			width: 300px
			top: calc(50% - 250px)
			left: calc(50% - 150px)
			z-index: 6
			transform-origin: 0 0
			animation: $door-time open-door ease-in-out, 0s fadeout linear $door-time
			animation-fill-mode: forwards
	
@-webkit-keyframes growfade
	0%
		opacity: 1
		outline: 0px solid rgba(255,255,255,1)
	33%
		opacity: 1
		outline: 50vw solid rgba(255,255,255,1)
	66%
		opacity: 1
		outline: 50vw solid rgba(255,255,255,1)
	100%
		opacity: 0
		outline: 50vw solid rgba(255,255,255,1)

@-webkit-keyframes open-door
	0%
		transform: rotateX(-20deg) rotateY(0deg)
	100%
		transform: rotateX(-20deg) rotateY(-100deg)

@-webkit-keyframes fadeout
	0%
		opacity: 1
	100%
		opacity: 0

@-webkit-keyframes remove
	0%, 100%
		top: -1000000px
</style>


<style lang="scss" scoped>
.carousel-nav-icon {
   height: 48px;
   width: 48px;
}
.carousel-item {
   .col,
   .col-sm,
   .col-md {
      margin: 8px;
      height: 300px;
      background-size: cover;
      background-position: center center;
   }
}

/* nothing below this point is needed*/
@import url("https://fonts.googleapis.com/css?family=Libre+Franklin:300,400,600,700,800,900&display=swap");
body {
   background-color: #f2f2f2;
   color: #171717;
   height: 100vh;
   min-height: 100%;
   display: flex;
   align-items: center;
   justify-content: center;
}
h1 {
   font-family: "Libre Franklin", sans-serif;
   font-weight: 700;
}



</style>