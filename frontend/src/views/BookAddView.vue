<script setup lang="ts">
import validator, { type Data } from "@/commons/validator";
import ErrorMsg from "@/components/commons/validator/ErrorMsg.vue";
import type Book from "@/domains/book";
import type Stock from "@/domains/stock";
import { StockKind } from "@/domains/stock";
import router from "@/router";
import axios from "axios";
import { ref } from "vue";

const book = ref<Book>({
  stock: { idx: 0, name: "", code: "", stockKind: StockKind.ETF },
  count: 0,
  price: 0,
  sellFlag: false
});

const stocks = ref<Stock[]>([]);
const errors = ref<Map<string, string>>(new Map<string, string>());

const datas: Map<string, Data> = new Map([
  ["count", { msg: "개수를 입력하세요.", func: () => book.value.count > 0 }],
  ["price", { msg: "가격을 입력하세요.", func: () => book.value.price > 0 }],
  ["stock", { msg: "종목을 선택하세요.", func: () => book.value.stock.idx > 0 }]
]);

const { validateAll, validate } = validator(datas, errors.value);

axios
  .get("/api/stock")
  .then((res) => {
    stocks.value = res.data;
  })
  .catch((e) => {
    console.log("get stock err", e);
  });

const addBook = () => {
  if (!validateAll()) return;

  axios
    .post("/api/book", book.value)
    .then((res) => {
      if (res.data === "Success") {
        router.push("/");
      }
    })
    .catch((e) => {
      console.log("post book err", e);
    });
};
</script>

<template>
  <div>
    <label for="stock">종목</label>
    <select id="stock" v-model="book.stock" @change="validate('stock')">
      <option v-for="s in stocks" :key="s.code" :value="s">
        {{ s.name }}
      </option>
    </select>
  </div>
  <ErrorMsg :show="errors.has('stock')" :msg="errors.get('stock')"></ErrorMsg>
  <div>
    <label for="count">갯수</label>
    <input id="count" type="number" v-model="book.count" @keyup="validate('count')" />
  </div>
  <ErrorMsg :show="errors.has('count')" :msg="errors.get('count')"></ErrorMsg>
  <div>
    <label for="price">가격</label>
    <input id="price" type="number" v-model="book.price" @keyup="validate('price')" />
  </div>
  <ErrorMsg :show="errors.has('price')" :msg="errors.get('price')"></ErrorMsg>
  <div>
    <label for="sell_flag">판매여부</label>
    <input id="sell_flag" type="checkbox" v-model="book.sellFlag" />
  </div>
  <div>
    <button @click="addBook">추가</button>
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

input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}
</style>
