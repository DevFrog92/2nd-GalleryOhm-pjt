<template>
  <!-- post 빈 -->
  <div class="container">
    <div class="admin">
      <b-tabs class="admin_tabs">
        <b-tab title="메인 갤러리 키워드 관리" active>
          <!-- 메인 갤러리 키워드 추가/삭제 -->
          <!-- addMainGalleryHashTag -->
          <!-- deleteMainGalleryHashTag -->
          <div class="mt">
            <label for="tags-pills">메인 갤러리 키워드 추가/삭제</label>
            <b-form-tags
              input-id="tags-pills"
              v-model="keywordList"
              tag-variant="primary"
              tag-pills
              size="lg"
              separator=" "
              placeholder="메인 갤러리 작품 선정 키워드를 입력하세요."
            >
            </b-form-tags>
            <b-button
              variant="outline-primary"
              class="btn"
              @click="saveKeyword()"
              >저장</b-button
            >
          </div>
        </b-tab>
        <b-tab title="해시태그 관리">
          <!-- 작품/전체 해시태그 삭제 -->
          <!-- deleteHashTagFromWork -->
          <!-- deleteHashTagFromTotal -->
          <div class="mt">
            <label for="tags-pills">전체 해시태그</label><br />
            <label for="tags-pills">해시태그 목록</label>
            <b-form-tags
              input-id="tags-pills"
              v-model="hashtagList"
              tag-variant="primary"
              tag-pills
              size="lg"
              separator=" "
              placeholder="삭제만"
            >
            </b-form-tags>

            <!-- <b-list-group horizontal>
              <b-container class="bv-example-row">
                <b-row>
                  <b-list-group-item
                    v-for="(hashtag, index) in hashtagList"
                    :key="index"
                  >
                    <b-col>
                      <b-button @click="deleteHashtag(hashtag.hashtag_name)">{{
                        hashtag.hashtag_name
                      }}</b-button>
                    </b-col>
                  </b-list-group-item>
                </b-row>
              </b-container>
            </b-list-group> -->

            <!-- <b-button
              variant="outline-primary"
              class="btn"
              @click="deleteHashtag()"
              >저장</b-button
            > -->
            <!-- <p class="mt-2">hasgtagList: {{ hashtagList }}</p> -->
          </div>
          <!-- <div class="mt">
            <label for="tags-pills">작품 해시태그 삭제</label>

            <div>
              <b-table :items="workHashtag" ref="table">
                <b-table-column field="workHashtag" label="해시태그"> -->
          <!-- <b-button
                    outlined
                    @click="func(items.hashtags)"
                    >btn</b-button
                  > -->
          <!-- <b-form-tags
                    input-id="tags-pills"
                    v-model="workHashtag"
                    tag-variant="primary"
                    tag-pills
                    size="lg"
                    separator=" "
                  >
                  </b-form-tags>
                </b-table-column>
              </b-table>
            </div>
          </div> -->
        </b-tab>
        <b-tab title="작품 연령등급 관리">
          <!-- 작품 연령등급 부여/해제 -->
          <!-- giveRating -->
          <!-- clearRating -->
          <div>
            <div class="item-lists">
              <div class="grid" v-for="(img, i) in imgList" :key="i">
                <img :src="img.work_piece" alt="DB이미지" />
                <div class="grid__body" :data-value="img.work_id">
                  <h1 v-text="img.work_rating"></h1>
                  <!-- <p>Title : {{ img.work_title }}</p>
                  <p>By : {{ img.work_artistId }}</p> -->
                  <div v-if="img.work_rating === 0">
                    <b-button class="btn19" @click="giveRating(img.work_id)"
                      >연령 제한</b-button
                    >
                  </div>
                  <div v-else-if="img.work_rating === 19">
                    <b-button class="btn19" @click="clearRating(img.work_id)"
                      >연령 제한 해제</b-button
                    >
                  </div>
                </div>
              </div>
            </div>
          </div>
        </b-tab>
        <b-tab title="회원 관리">
          <div class="mt">
            <b-table :items="userList" ref="table"></b-table>
          </div>
        </b-tab>
        <b-tab title="작가 관리">
          <div class="mt">
            <b-table :items="artistList" ref="table"></b-table>
          </div>
        </b-tab>
        <b-tab title="메인 갤러리 갱신">
          <div class="mt">
            <b-button
              variant="outline-primary"
              class="btn"
              @click="updateMainGallery()"
              >갱신</b-button
            >
          </div>
          <div class="mt">
            <div class="gallery-page-content" v-if="updateMain">갱신완료</div>
          </div>
        </b-tab>
        <!-- <b-tab title="서브 갤러리 갱신">
          <div class="mt">
            <b-button
              variant="outline-primary"
              class="btn"
              @click="updateSubGallery()"
              >갱신</b-button
            >
          </div>
          <div class="mt">
            <div class="gallery-page-content" v-if="subGallery.length != 0">
              <div
                class="gallery-card"
                v-for="(items, i) in subGallery"
                :key="i"
                :style="{
                  backgroundImage:
                    'url(data:image/jpeg;base64,' + items.work_piece + ')',
                }"
              >
                <div class="gallery-content">
                  <h2 class="gallery-title">{{ items.work_id }}</h2>
                </div>
              </div>
            </div>
          </div>
        </b-tab> -->
        <b-tab title="메인갤러리 반환">
          <div class="mt">
            <b-button
              variant="outline-primary"
              class="btn"
              @click="getAllMainAdultGallery()"
              >성인용 메인 갤러리 반환</b-button
            >
            <b-button
              variant="outline-primary"
              class="btn"
              @click="getAllMainGallery()"
              >미성년자용 메인 갤러리 반환</b-button
            >
          </div>
          <div class="mt">
            <div
              class="gallery-page-content"
              v-if="mainAdultGallery.length != 0"
            >
              <div
                class="gallery-card"
                v-for="(items, i) in mainAdultGallery"
                :key="i"
              >
                <div class="gallery-content">
                  <h2 class="gallery-title">{{ items.gallery_id }}</h2>
                </div>
              </div>
            </div>
          </div>
           <div class="mt">
            <div class="gallery-page-content" v-if="mainGallery.length != 0">
              <div
                class="gallery-card"
                v-for="(items, i) in mainGallery"
                :key="i"
              >
                <div class="gallery-content">
                  <h2 class="gallery-title">{{ items.gallery_id }}</h2>
                </div>
              </div>
            </div>
          </div>
        </b-tab>
        <!-- <b-tab title="미성년자용 메인 갤러리 반환">
          <div class="mt">
            <b-button
              variant="outline-primary"
              class="btn"
              @click="getAllMainGallery()"
              >반환</b-button
            >
          </div>
          <div class="mt">
            <div class="gallery-page-content" v-if="mainGallery.length != 0">
              <div
                class="gallery-card"
                v-for="(items, i) in mainGallery"
                :key="i"
              >
                <div class="gallery-content">
                  <h2 class="gallery-title">{{ items.gallery_id }}</h2>
                </div>
              </div>
            </div>
          </div>
        </b-tab> -->
        <b-tab title="신인작가 Exp 갱신">
          <div class="mt">
            <b-button
              variant="outline-primary"
              class="btn"
              @click="artistExpUp()"
              >작가 Exp Up</b-button
            >
          </div>
        </b-tab>
      </b-tabs>
    </div>
  </div>
</template>

<script>
import http from "../../api/http";

export default {
  data() {
    return {
      keywordList: [],
      hashtagList: [],
      imgList: [],
      items: [],
      workHashtag: [],
      userList: [],
      artistList:[],
      subGallery: [],
      mainAdultGallery: [],
      mainAdultGalleryThum: [],
      mainGallery: [],
      mainGalleryThum: [],
      updateMain: false,
    };
  },
  created() {
    this.getAllKeyword();
    this.getAllHashtag();
    this.getAllWork();
    this.getWorkHashtag();
    this.getAllUser();
    this.getAllArtist();
  },
  mounted() {},
  methods: {
    
    getAllArtist() {
  http.get(`/admin/getAllArtist`).then(
        (response) => {
          const data = response.data;
          for (var i = 0; i < data.length; i++) {
            if (data[i].artist_exp != 0) {
              let a = {
                id: data[i].artist_id,
                bank: data[i].artist_bank,
                account: data[i].artist_account,
                nickName: data[i].artist_nickName,
                exp: data[i].artist_exp,
              };
              this.artistList.push(a);
            }
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getAllMainAdultGallery() {
      http.get(`/admin/getAllMainAdultGallery`).then(
        (response) => {
          this.mainAdultGallery = response.data;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getAllMainGallery() {
      http.get(`/admin/getAllMainGallery`).then(
        (response) => {
          this.mainGallery = response.data;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getAllSubGallery() {
      http.get(`/gallery/getAllSubGallery`).then((response) => {
        this.subGallery = response.data;
      });
    },
    updateMainGallery() {
      http.get(`/admin/renewMainGallery`).then(
        (response) => {
          console.log(response);

          if (response.status == 200) {
            // this.getAllMainAdultGallery();
            this.updateMain = true;
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    updateSubGallery() {
      http.get(`/admin/renewSubGallery`).then(
        (response) => {
          //   console.log(response.data);
          if (response.status == 200) {
            this.getAllSubGallery();
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getAllUser() {
      http.get(`/admin/getAllUser`).then(
        (response) => {
          const data = response.data;
          for (var i = 0; i < data.length; i++) {
            if (data[i].user_id != "admin") {
              let u = {
                id: data[i].user_id,
                name: data[i].user_name,
                nickName: data[i].user_nickName,
                email: data[i].user_email,
                tel: data[i].user_tel,
                birth: data[i].user_birth,
                createDate: data[i].user_createDate,
                about: data[i].user_about,
              };
              this.userList.push(u);
            }
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getWorkHashtag() {
      http.get(`/admin/getAllWork`).then(
        (response) => {
          const data = response.data;
          for (var i = 0; i < data.length; i++) {
            let w = {
              work_id: data[i].work_id,
              work_title: data[i].work_title,
              hashtags: data[i].hashtags,
            };
            this.items.push(w);
            this.workHashtag.push(w);
          }
          console.log(this.items);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getAllKeyword() {
      http.get(`/admin/getMainGalleryKeyword/`).then(
        (response) => {
          this.keywordList = response.data;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getAllHashtag() {
      this.hashtagList = [];

      http.get(`/admin/getAllHashTag/`).then(
        (response) => {
          const data = response.data;
          for (var i = 0; i < data.length; i++) {
            // this.hashtagList.push(data[i].hashtag_name);
            let h = {
              hashtag_name: data[i].hashtag_name,
            };
            this.hashtagList.push(h);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getAllWork() {
      http.get(`/admin/getAllWork`).then(
        (response) => {
          const data = response.data;
          for (var i = 0; i < data.length; i++) {
            data[i].work_piece = "data:image/jpeg;base64," + data[i].work_piece;
          }
          this.imgList = data;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    giveRating(work_id) {
      http.post(`/admin/giveRating`, work_id).then(
        (response) => {
          console.log(response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    clearRating(work_id) {
      http.post(`/admin/clearRating`, work_id).then(
        (response) => {
          console.log(response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    saveKeyword() {
      var formData = new FormData();
      formData.append("keywordList", this.keywordList);

      http.post(`/admin/addMainGalleryKeyword`, formData).then(
        (response) => {
          console.log(response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    deleteHashtag(hashtag) {
      var formData = new FormData();
      formData.append("hashtag_name", hashtag);

      console.log(formData);

      http.post(`/admin/deleteHashTagFromTotal`, formData).then(
        (response) => {
          console.log(response.data);

          this.getAllHashtag();
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style scoped>
* {

}

.container {
 /* margin: 0 auto; */
width: 100vw;
height: 100vh;
}
.admin {
  position: relative;
 margin: 0 auto;
}
.admin_tabs {
  left: -50%;
 /* margin: 0 auto; */
}
.mt {
  margin-top: 10%;
  padding-top: 10%;
}
.btn {
  margin-top: 2%;
  margin-left: 50%;
}
.btn19 {
  margin: 0 auto;
}
</style>
