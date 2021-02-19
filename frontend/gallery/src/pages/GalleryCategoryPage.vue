<template>
  <div class="gallery__category">
    <button @click="moveToSub">Sub</button>
    <button @click="moveToRed">R-rate</button>
  </div>
</template>

<script>
export default {
  methods: {
    moveToRed() {
      this.$router.push("/RedGallery");
    },
    moveToSub() {
      this.$router.push("/SubGallery");
    },
  },
};
</script>

<style>
</style>