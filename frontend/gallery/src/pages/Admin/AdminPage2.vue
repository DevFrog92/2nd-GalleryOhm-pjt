<template>
  <!-- get 유정 -->
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
            <!-- <b-table striped hover :items="items"></b-table> -->
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
            <div class="gallery-page-content" v-if="updateMain">
              갱신완료
            </div>
          </div>
        </b-tab>
        <b-tab title="서브 갤러리 갱신">
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
        </b-tab>
        <b-tab title="성인용 메인갤러리 반환">
          <div class="mt">
            <b-button
              variant="outline-primary"
              class="btn"
              @click="getAllMainAdultGallery()"
              >반환</b-button
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
        </b-tab>
        <b-tab title="미성년자용 메인 갤러리 반환">
          <div class="mt">
            <b-button
              variant="outline-primary"
              class="btn"
              @click="getAllMainGallery()"
              >갱신</b-button
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
        </b-tab>
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
      hasgtagList: [],
      items: [],
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
  },
  methods: {
    artistExpUp() {
      http.post(`/admin/renewArtistExp/`).then(
        () => {
          // console.log(response.data);
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
      http.get(`/work/getAllWork/` + 1).then(
        (response) => {
          // console.log(response.data);
          this.items = response.data;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    saveKeyword() {
      http.post(`/admin/addMainGalleryKeyword/`).then(
        () => {
          // console.log(response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    saveHashtag() {},
    updateMainGallery() {
      http.get(`/admin/renewMainGallery`).then(
        (response) => {
          // console.log(response);

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

.gallery-page-content {
  display: grid;
  grid-gap: 1.3rem;
  padding: 0.6rem;
  max-width: 1000px;
  margin: 0 auto;
}

@media (min-width: 600px) {
  .gallery-page-content {
    grid-template-columns: repeat(3, 1fr);
  }
}

@media (min-width: 800px) {
  .gallery-page-content {
    grid-template-columns: repeat(3, 1fr);
    max-width: 1000px;
  }
}
@media (min-width: 1240px) {
  .gallery-page-content {
    grid-template-columns: repeat(3, 1fr);
    max-width: 1100px;
  }
}

.gallery-card {
  position: relative;
  display: flex;
  align-items: flex-end;
  overflow: hidden;
  padding: 1rem;
  width: 100%;
  text-align: center;
  color: whitesmoke;
  background-color: whitesmoke;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1), 0 2px 2px rgba(0, 0, 0, 0.1),
    0 4px 4px rgba(0, 0, 0, 0.1), 0 8px 8px rgba(0, 0, 0, 0.1),
    0 16px 16px rgba(0, 0, 0, 0.1);
  background-position: center;
  background-size: cover;
}

@media (min-width: 600px) {
  .gallery-card {
    height: 450px;
  }
}
@media (min-width: 800px) {
  .gallery-card {
    height: 300px;
  }
}
</style>
