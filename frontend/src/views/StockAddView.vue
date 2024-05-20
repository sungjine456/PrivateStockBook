<script setup lang="ts">
import type Stock from "@/domains/Stock";
import { StockKind, stockKindEntries } from "@/domains/Stock";
import router from "@/router";
import axios from "axios";
import { ref } from "vue";

const stock = ref<Stock>({ name: "", code: "", stockKind: StockKind.ETF });

const errors = ref<Map<string, string>>(new Map<string, string>());

const validaters = () => {
  const name = validater("name");
  const code = validater("code");

  return name && code;
};

const validater = (type: string) => {
  let value = "";
  let msg = "";

  if (type === "name") {
    value = stock.value.name;
    msg = "이름이 없습니다.";
  } else if (type === "code") {
    value = stock.value.code;
    msg = "코드가 없습니다.";
  }

  if (value === "") {
    errors.value.set(type, msg);
    return false;
  } else {
    errors.value.delete(type);
    return true;
  }
};

const addStock = () => {
  if (!validaters()) return;

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
    <label for="name">이름</label>
    <input id="name" v-model="stock.name" @keyup="validater('name')" />
  </div>
  <div class="error-box">
    <span class="error" v-show="errors.has('name')">{{ errors.get("name") }}</span>
  </div>
  <div>
    <label for="code">코드</label>
    <input id="code" v-model="stock.code" @keyup="validater('code')" />
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
