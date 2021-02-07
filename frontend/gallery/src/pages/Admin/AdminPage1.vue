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
              @click="saveHashtag()"
              >저장</b-button
            >
          </div>
          <div class="mt">
            <label for="tags-pills">작품 해시태그 삭제</label>
          </div>
        </b-tab>
        <b-tab title="작품 연령등급 관리">
          <!-- 작품 연령등급 부여/해제 -->
          <!-- giveRating -->
          <!-- clearRating -->
          <div>
            <b-table striped hover :items="items"></b-table>
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
      hasgtagList: [],
      items: []
    };
  },
  created() {
    this.getAllKeyword();
    this.getAllHashtag();
    this.getAllWork();
  },
  methods: {
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
        http.get(`/work/getAllWork/`+ 1).then(
        (response) => {
            console.log(response.data);
          this.items = response.data;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    saveKeyword() {
      http.post(`/admin/addMainGalleryKeyword/`).then(
        (response) => {
         console.log(response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    saveHashtag() {},
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