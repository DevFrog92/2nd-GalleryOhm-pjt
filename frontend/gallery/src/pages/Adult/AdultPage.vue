<template>
  <div class="main_title_grid">
    <div class="left">left</div>
    <div class="right">right
      <div class="right_child1">right_child1</div>
      <div class="right_child2">right_child2
        <div class="right_child2_child1">right_child2_child1
          <p class="title" @click="onClick">선오브비취</p>
        </div>
        <div class="right_child2_child2">right_child2_child2
           <p class="title">오른손잡이의 <br>
                   &nbsp;&nbsp;왼손그림</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      
    }
  },methods: {
    onClick() {
      alert("image");
    }
  },
}
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Sura:wght@400;700&display=swap");

.main_title_grid {
  display: flex;
  background-color: wheat;
}
.main_title_grid .left {
  background-color: yellow;
  flex: 4;
  height: 100vh;
}
.main_title_grid .right {
  background-color: yellowgreen;
  flex: 5;
  height: 100vh;
}
.main_title_grid .right .right_child1 {
  background-color: turquoise;
  height: 50vh;

}

.main_title_grid .right .right_child2 {
  background-color: teal;
  height: 50vh;
}
.main_title_grid .right .right_child2 .right_child2_child1 {
  background-color:tomato;
  height: 18vh;

}
.main_title_grid .right .right_child2 .right_child2_child1 .title {
 color: black;
  font-family: "Hanna", sans-serif;
  transform: scale(13);
}
.main_title_grid .right .right_child2 .right_child2_child2 {
background-color: violet;
height: 32vh;
}
.main_title_grid .right .right_child2 .right_child2_child2 .title {
 color: black;
  font-family: "Hanna", sans-serif;
  font-size: 4rem;
}
</style>