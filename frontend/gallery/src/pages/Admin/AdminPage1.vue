<template>
  <!-- post 빈 -->
  <div class="container">
    <div>
      <b-tabs content-class="mt-3">
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
            <p class="mt-2">Keyword: {{ keywordList }}</p>
          </div>
        </b-tab>
        <b-tab title="작품 해시태그 관리">
          <!-- 작품/전체 해시태그 삭제 -->
          <!-- deleteHashTagFromWork -->
          <!-- deleteHashTagFromTotal -->
          <div class="mt">
            <label for="tags-pills">전체 해시태그</label><br />
            <label for="tags-pills">해시태그 삭제</label>
            <b-form-tags
              input-id="tags-pills"
              v-model="hasgtagList"
              tag-variant="primary"
              tag-pills
              size="lg"
              separator=" "
              placeholder=""
            >
            </b-form-tags>
            <b-button
              variant="outline-primary"
              class="btn"
              @click="deleteHashtag()"
              >저장</b-button
            >
            <p class="mt-2">hasgtagList: {{ hasgtagList }}</p>
          </div>
          <div class="mt">
            <label for="tags-pills">작품 해시태그 삭제</label>

            <div>
              <b-table :items="workHashtag" ref="table">
                <b-table-column field="workHashtag" label="해시태그">
                  <!-- <b-button
                    outlined
                    @click="func(items.hashtags)"
                    >btn</b-button
                  > -->
                  <b-form-tags
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
          </div>
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
                    <b-button @click="giveRating(img.work_id)"
                      >연령 제한</b-button
                    >
                  </div>
                  <div v-else-if="img.work_rating === 19">
                    <b-button @click="clearRating(img.work_id)"
                      >연령 제한 해제</b-button
                    >
                  </div>
                </div>
              </div>
            </div>
          </div>
        </b-tab>
        <b-tab title="회원 관리">
          <b-table :items="userList" ref="table"></b-table>
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
      hasgtagList: [],
      imgList: [],
      items: [],
      workHashtag: [],
      userList: [],
    };
  },
  created() {
    this.getAllKeyword();
    this.getAllHashtag();
    this.getAllWork();
    this.getWorkHashtag();
    this.getAllUser();
  },
  mounted() {},
  methods: {
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
      http.get(`/admin/getAllHashTag/`).then(
        (response) => {
          this.hasgtagList = response.data;
          
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
    deleteHashtag() {
      var formData = new FormData();
      formData.append("hasgtagList", this.hasgtagList);

      http.post(`/admin/deleteHashtagFromTotal`, this.hasgtagList).then(
        (response) => {
          console.log(response.data);
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
.mt {
  margin-top: 5%;
}
.btn {
  margin-top: 2%;
  margin-left: 50%;
}
</style>