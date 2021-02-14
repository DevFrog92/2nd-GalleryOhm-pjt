<template>
  <div class="wrap">
    <div class="block">
      <div class="content"></div>
      <div class="door">
        <div class="side"></div>
      </div>
    </div>

    <div class="bgm">
      <audio autoplay controls id="sound" loop src="../../assets/images/bgm.mp3" style="display:none;">
        <!-- <source src="../../assets/images/bgm.mp3" type="audio/mp3" /> -->
      </audio>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  created() {

  },
  mounted() {
    const body = document.querySelector(".wrap");

    body.addEventListener("click", this.play);
  },
  methods: {
    play() {
      const sound = document.querySelector("#sound");
      const body = document.querySelector(".wrap");

      var isIn = sound instanceof HTMLAudioElement;

      if (!isIn) return;
      if (sound.paused) {
        sound.play();
      } else {
        body.removeEventListener("click", this.play);
      }
      console.log(sound.paused);
    },
  },
};
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: sans-serif;
}
h3 {
  margin-bottom: 20px;
}
.wrap {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
  overflow: hidden;
}

.block {
  /* width: 222px;
  height: 455px; */
  width: 30vw;
  height: 70vh;
  perspective: 1500px;
  position: relative;
  margin-top: 10%;
  margin-bottom: 158px;
  box-shadow: inset 0 0 25px rgba(0, 0, 0, 0.25);
  background-color: #e7e7e7;
}
.block:hover .door {
  transform: rotatey(-90deg);
}

.door {
  /* display: none; */
  height: inherit;
  width: inherit;
  position: absolute;
  transform-origin: top left;
  transition: all 0.5s ease-in-out;
  transform-style: preserve-3d;
  top: 0;
  left: 0;
  background-color: red;
}

.door img {
  position: absolute;
  top: 0;
  left: 0;
}

.side {
  height: inherit;
  width: 16px;
  background-color: red;
  position: absolute;
  top: 0;
  right: -8px;
  transform: rotatey(90deg) translatex(8px);
}

.content {
  padding: 20px;
}
.bgm {
  margin-top: 40vw;
}
</style>