<script setup lang="ts">
import axios from "axios";
import { ref } from "vue";

const input = ref();
const testDatas = ref([]);

const loadStocks = () => {
  axios
    .get("/api/stock")
    .then((res) => {
      console.log("get stock", res);
      testDatas.value = res.data;
    })
    .catch((e) => {
      console.log("get stock err", e);
    });
};

const onClickHandler = () => {
  axios
    .post("/api/stock", null, { params: { name: input.value } })
    .then((res) => {
      console.log("post stock", res);
      res.data === "Success" && loadStocks();
    })
    .catch((e) => {
      console.log("post stock err", e);
    });
};

loadStocks();
</script>

<template>
  <div>
    <p v-for="testData in testDatas" :key="testData">
      {{ testData }}
    </p>
  </div>
  <input v-model="input" />
  <button @click="onClickHandler">추가</button>
</template>
