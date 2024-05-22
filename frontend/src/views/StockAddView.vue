<script setup lang="ts">
import validator, { type Data } from "@/commons/validator";
import ErrorMsg from "@/components/commons/validator/ErrorMsg.vue";
import type Stock from "@/domains/stock";
import { StockKind, stockKindEntries } from "@/domains/stock";
import router from "@/router";
import axios from "axios";
import { ref } from "vue";

const stock = ref<Stock>({ idx: 0, name: "", code: "", stockKind: StockKind.ETF });
const errors = ref<Map<string, string>>(new Map<string, string>());

const datas: Map<string, Data> = new Map([
  ["name", { msg: "이름이 없습니다.", func: () => !!stock.value.name }],
  ["code", { msg: "코드가 없습니다.", func: () => !!stock.value.code }]
]);

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
  <div class="form">
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
    <ErrorMsg :show="errors.has('name')" :msg="errors.get('name')"></ErrorMsg>
    <div>
      <label for="code">코드</label>
      <input id="code" v-model="stock.code" @keyup="validate('code')" />
    </div>
    <ErrorMsg :show="errors.has('code')" :msg="errors.get('code')"></ErrorMsg>
    <div class="buttons">
      <button @click="addStock">추가</button>
    </div>
  </div>
</template>
