<script setup>
defineProps({
  show: Boolean,
  msg: String
});
</script>

<template>
  <div class="error-box">
    <span class="error" v-show="show">{{ msg }}</span>
  </div>
</template>

<style scoped>
.error-box {
  text-align: end;
  height: 10px;
  font-size: 10px;
  line-height: 1;
  margin-top: 2px;

  .error {
    color: red;
    margin-left: 5px;
  }
}
</style>
