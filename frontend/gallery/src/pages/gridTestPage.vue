<template>
  <div class="section__works">
    <div class="grid_works">
      <div class="item_grid_works item--large" v-for="(image,index) of imgList" :key="index" :style="{backgroundImage:'url('+image.work_piece+')'}">
        <div class="item__details">
          jelly-o brownie sweet
        </div>
      </div>
    </div>
    </div>
</template>

<script>
import '../assets/css/gridTest.css';
import init from '../assets/js/gridtest'
  import http from "../api/http.js";
export default {
  data(){
    return {
      imgList:[],
    }
  },
  mounted(){
    setTimeout(()=>{
      init.init()
    },1000);
  },
  methods:{
     getAllWorks() {
        this.spinner_state = true;
        http.get(`/work/getAllWork/` + 1).then(
          (response) => {
            const data = response.data;
            // array 변환
            for (var i = 0; i < data.length; i++) {
              data[i].work_piece = "data:image/jpeg;base64," + data[i].work_piece;
            }

            // data.forEach(item => {
            //   const tempArr = [];
            //   for (let work in item) {
            //     tempArr.push([work, item[work]]);
            //   }
            //   this.most_like_work.push(tempArr);
            // })
            // this.most_like_work.sort(function (a, b) {
            //   return -a[5][1] + b[5][1]
            // });
            // this.most_like_work.forEach(items => {
            //   let most = {};
            //   for (let item of items) {
            //     most[item[0]] = item[1];
            //   }
            //   this.most_like.push(most);
            // })
            this.imgList = data;
            // console.log(this.imgList);
            // this.render_image = data;
            // this.spinner_state = false;

            // console.log('filtering end')
            // this.getMyFollowings();

          },
          (error) => {
            console.log(error);
          }
        );
      },

  },
  created(){
      this.getAllWorks();
    
  }
}
</script>

<style>

</style>