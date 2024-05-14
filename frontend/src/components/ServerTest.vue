<script setup lang="ts">
import axios from 'axios'

axios
  .get('/api/test')
  .then((res) => {
    console.log(res)
  })
  .catch((e) => {
    console.log(e)
  })
</script>

<template>
  <div>Test</div>
</template>
