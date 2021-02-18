<template>
  <div>
    <main id="banksy">
      <div id="piece">
        <div id="holder" @click="click">
          <div id="painting">
            <div id="shredded">
              <div class="shred"></div>
              <div class="shred"></div>
              <div class="shred"></div>
              <div class="shred"></div>
              <div class="shred"></div>
              <div class="shred"></div>
              <div class="shred"></div>
              <div class="shred"></div>
              <div class="shred"></div>
              <div class="shred"></div>
              <div class="shred"></div>
            </div>
            <div id="original"></div>
          </div>
        </div>
        <div id="frame" @click="click"></div>
      </div>
    </main>
  </div>
</template>

<script>
import router from "../../router";
import anime from "animejs/lib/anime.es.js";

export default {
  data() {
    return { shredding: null, dropping: null };
  },
  methods: {
    shred() {
      this.shredding = anime({
        targets: "#original",
        height: 0,
        duration: 10000,
        easing: "linear",
      });

      this.dropping = anime({
        targets: "#painting",
        translateY: "101%",
        duration: 10000,
        easing: "linear",
      });
    },
    click() {
      console.log("click!!");
      router.push("/RedGallery/AdultPage");
    },
  },
  mounted() {
    this.shred();
  },
};
</script>

<style scoped="scoped">
html,
body,
main {
  height: 100%;
  overflow: hidden;
  position: fixed;
  width: 100%;
}

main {
  align-items: center;
  display: flex;
  justify-content: center;
}

#piece {
  cursor: pointer;
  height: 50vh;
  max-height: 820px;
  position: relative;
  width: calc(48vh * 0.774);
  display: flex;
  justify-content: center;
  align-items: center;
}

#frame {
  background-size: cover;
  height: 100%;
  position: absolute;
  width: 100%;
  border-image: url("../../assets/images/frame4.png") 1 fill / 1px / 1px space;
  border-image-repeat: round;
}

#holder {
  background-image: url(../../assets/images/s1.png);
  position: relative;
  background-size: cover;

  cursor: pointer;
  height: 45vh;
  max-height: 820px;
  position: absolute;
  width: calc(45vh * 0.774);
  justify-content: center;
  align-items: center;
  background-color: #c20a0a;
}

#painting {
  height: 100%;
  position: absolute;
  transform: translateY(0%);
  width: 100%;
}

#original,
.shred {
  background-image: url(../../assets/images/w5.jpg);
  /* background-image: url(../../assets/images/s5.png);
  background-color: black; */
  background-size: cover;
}

#original {
  height: 100%;
  position: absolute;
  top: 0;
  width: 100%;
}

#shredded {
  bottom: 0;
  display: grid;
  grid-column-gap: 3px;
  grid-template-columns: repeat(11, 1fr);
  height: 100%;
  position: absolute;
  width: 100%;
}

#shredded .shred {
  background-position: top center;
}

#shredded .shred:nth-child(1) {
  background-position: 0% 0%;
}

#shredded .shred:nth-child(2) {
  background-position: 10% 0%;
}

#shredded .shred:nth-child(3) {
  background-position: 20% 0%;
}

#shredded .shred:nth-child(4) {
  background-position: 30% 0%;
}

#shredded .shred:nth-child(5) {
  background-position: 40% 0%;
}

#shredded .shred:nth-child(6) {
  background-position: 50% 0%;
}

#shredded .shred:nth-child(7) {
  background-position: 60% 0%;
}

#shredded .shred:nth-child(8) {
  background-position: 70% 0%;
}

#shredded .shred:nth-child(9) {
  background-position: 80% 0%;
}

#shredded .shred:nth-child(10) {
  background-position: 90% 0%;
}

#shredded .shred:nth-child(11) {
  background-position: 100% 0%;
}

input {
  display: none;
}

header {
  position: absolute;
  top: 2em;
}

header a {
  color: black;
}
</style>