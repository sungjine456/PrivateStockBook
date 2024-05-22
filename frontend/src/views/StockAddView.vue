<script setup lang="ts">
import validator from "@/commons/validator";
import type Stock from "@/domains/stock";
import { StockKind, stockKindEntries } from "@/domains/stock";
import router from "@/router";
import axios from "axios";
import { ref } from "vue";

const stock = ref<Stock>({ idx: 0, name: "", code: "", stockKind: StockKind.ETF });
const errors = ref<Map<string, string>>(new Map<string, string>());

const datas = [
  { key: "name", msg: "이름이 없습니다.", func: () => !!stock.value.name },
  { key: "code", msg: "코드가 없습니다.", func: () => !!stock.value.code }
];

const { validateAll, validate } = validator(datas, errors.value);

const addStock = () => {
  if (!validateAll()) return;

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
    <label for="kind">종류</label>
    <select id="kind" v-model="stock.stockKind">
      <option v-for="[value, key] in stockKindEntries" :key="key" :value="key">
        {{ value }}
      </option>
    </select>
  </div>
  <div>
    <label for="name">이름</label>
    <input id="name" v-model="stock.name" @keyup="validate('name')" autocomplete="off" />
  </div>
  <div class="error-box">
    <span class="error" v-show="errors.has('name')">{{ errors.get("name") }}</span>
  </div>
  <div>
    <label for="code">코드</label>
    <input id="code" v-model="stock.code" @keyup="validate('code')" />
  </div>
  <div class="error-box">
    <span class="error" v-show="errors.has('code')">{{ errors.get("code") }}</span>
  </div>
  <div>
    <button @click="addStock">추가</button>
  </div>
</template>

<style scoped>
label {
  margin-right: 5px;
}

button {
  float: right;
  margin-top: 5px;
}

input,
select {
  width: 180px;
}
</style>
