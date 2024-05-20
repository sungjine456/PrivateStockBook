<script setup lang="ts">
import type Stock from "@/domains/Stock";
import { StockKind, stockKindEntries } from "@/domains/Stock";
import router from "@/router";
import axios from "axios";
import { ref } from "vue";

const stock = ref<Stock>({ name: "", code: "", stockKind: StockKind.ETF });

const addStock = () => {
  if (stock.value.name === "" || stock.value.code === "") {
    // TODO 저장되지 않는 이유 사용자에게 안내하기
    return;
  }

  axios
    .post("/api/stock", stock.value)
    .then((res) => {
      if (res.data === "Success") {
        router.push("/");
      } else if (res.data === "Failure") {
        alert("코드 중복");
      }
    })
    .catch((e) => {
      console.log("post stock err", e);
    });
};
</script>

<template>
  <div>
    <label>종류</label>
    <select v-model="stock.stockKind">
      <option v-for="[value, key] in stockKindEntries" :key="key" :value="key">
        {{ value }}
      </option>
    </select>
  </div>
  <div>
    <label>이름</label>
    <input v-model="stock.name" />
  </div>
  <div>
    <label>코드</label>
    <input v-model="stock.code" />
  </div>
  <button @click="addStock">추가</button>
</template>
