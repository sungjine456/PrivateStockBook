<script setup lang="ts">
import router from "@/router";
import axios from "axios";
import { ref } from "vue";

const input = ref();
const testDatas = ref([]);

const add = () => {
  axios
    .post("/api/stock", null, { params: { name: input.value } })
    .then((res) => {
      console.log("post stock", res);
      res.data === "Success" && router.push("/");
    })
    .catch((e) => {
      console.log("post stock err", e);
    });
};
</script>

<template>
  <div>
    <p v-for="testData in testDatas" :key="testData">
      {{ testData }}
    </p>
  </div>
  <input v-model="input" />
  <button @click="add">추가</button>
</template>
