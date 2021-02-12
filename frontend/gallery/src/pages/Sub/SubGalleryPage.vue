<template>
  <div class="wrap">
    <div class="block">
      <div class="content"></div>
      <div class="door">
        <div class="side"></div>
      </div>
    </div>
  </div>
</template>


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
</style>