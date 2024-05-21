<script setup lang="ts">
import type Book from "@/domains/Book";
import type Stock from "@/domains/Stock";
import { StockKind } from "@/domains/Stock";
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

axios
  .get("/api/stock")
  .then((res) => {
    stocks.value = res.data;
  })
  .catch((e) => {
    console.log("get stock err", e);
  });

const validaters = () => {
  const stock = validater("stock");
  const count = validater("count");
  const price = validater("price");

  return stock && count && price;
};

const validater = (type: string) => {
  let value = 0;
  let msg = "";

  if (type === "count") {
    value = book.value.count;
    msg = "개수를 입력하세요.";
  } else if (type === "price") {
    value = book.value.price;
    msg = "가격을 입력하세요.";
  } else if (type === "stock") {
    value = book.value.stock.idx;
    msg = "종목을 선택하세요.";
  }

  if (!value) {
    errors.value.set(type, msg);
    return false;
  } else {
    errors.value.delete(type);
    return true;
  }
};

const addBook = () => {
  if (!validaters()) return;

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
    <select id="stock" v-model="book.stock" @change="validater('stock')">
      <option v-for="s in stocks" :key="s.code" :value="s">
        {{ s.name }}
      </option>
    </select>
  </div>
  <div class="error-box">
    <span class="error" v-show="errors.has('stock')">{{ errors.get("stock") }}</span>
  </div>
  <div>
    <label for="count">갯수</label>
    <input id="count" v-model="book.count" @keyup="validater('count')" />
  </div>
  <div class="error-box">
    <span class="error" v-show="errors.has('count')">{{ errors.get("count") }}</span>
  </div>
  <div>
    <label for="price">가격</label>
    <input id="price" v-model="book.price" @keyup="validater('price')" />
  </div>
  <div class="error-box">
    <span class="error" v-show="errors.has('price')">{{ errors.get("price") }}</span>
  </div>
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

.error-box {
  text-align: end;
  height: 10px;
  font-size: 10px;
  line-height: 1;
  margin-top: 2px;
}

.error {
  color: red;
  margin-left: 5px;
}
</style>
