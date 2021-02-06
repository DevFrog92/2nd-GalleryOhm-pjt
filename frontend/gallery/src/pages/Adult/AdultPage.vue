<template>
  <div class="subgallery">
    <hooper class="hooper1" :settings="hooperSettings" v-if="workList_odd.length != 0">
      <slide v-for="(work, i) in workList_odd" :key="i" :index="i">
        <div
          class="images"
          :style="work.work_piece"
          @click="detailImage()"
        >
        </div>
      </slide>
    </hooper>

    <hooper class="hooper2" :settings="hooperSettings" v-if="workList_even.length != 0">
      <slide v-for="(work, i) in workList_even" :key="i">
        <div
          class="images"
          :style="work.work_piece"
        >
        </div>
      </slide>
    </hooper>
  </div>
</template>

<script>
import http from '../../api/http';

export default {
    data() {
        return {
            hooperSettings: {
                infiniteScroll: true,
                breakpoints: {
                    2400: {
                        itemsToShow: 7
                    },
                    1800: {
                        itemsToShow: 6
                    },
                    1500: {
                        itemsToShow: 5
                    },
                    1100: {
                        itemsToShow: 3.5
                    },
                    0: {
                        itemsToShow: 2.5
                    }
                }
            },
            workList_odd: [],
            workList_even: []
        };
    },
    components: {
        Hooper: window.Hooper.Hooper,
        Slide: window.Hooper.Slide
    },
    created() {
        this.getOddList();
        this.getEvenList();

    },
    methods: {
        detailImage() {
            console.log("go to image detail");
        },
        getOddList() {
            http
                .get(`/gallery/getAdultGallery_odd/`)
                .then((response) => {
                    var workList = response.data;

                    for (var i = 0; i < workList.length; i++) {
                        workList[i].work_piece = "background-image: url(data:image/jpeg;base64," +
                                workList[i].work_piece + ")";
                    }

                    this.workList_odd = workList;
                }, (error) => {
                    console.log(error);
                });
        },
        getEvenList() {
            http
                .get(`/gallery/getAdultGallery_even/`)
                .then((response) => {
                    var workList = response.data;

                    for (var i = 0; i < workList.length; i++) {
                        workList[i].work_piece = "background-image: url(data:image/jpeg;base64," +
                                workList[i].work_piece + ")";
                    }

                    this.workList_even = workList;
                }, (error) => {
                    console.log(error);
                });
        }
    }
};
</script>

<style scoped>
.subgallery {
  height: 100%;
  background-color: #C20A0A;
}

.images {
  height: 40vh;
  width: 18em;
  padding: 0 1vw;
  margin: 13px;
  /* border-radius: 5px; */
  background-size: cover;
  background-position: center;
  border-image: url('../../assets/images/frame4.png') 1 fill / 1px / 0.8rem round space ;
  /* border-image: url('../../assets/images/frame4.png') 1 fill / 1px / 1px space; */
  border-image-repeat: round;
}

.hooper1 {
  height: 45vh;
  padding: 0 1vw;
  margin-top: 1vh;
}
.hooper2 {
  height: 45vh;
  padding: 0 1vw;
  margin-top: 3vh;
}
</style>
