<script setup lang="ts">
import type Book from "@/domains/book";
import axios from "axios";
import { ref } from "vue";

const books = ref<Book[]>([]);

{
  axios
    .get("/api/book")
    .then((res) => {
      books.value = res.data;
    })
    .catch((e) => {
      console.log("get book err", e);
    });
}
</script>

<template>
  <div class="main">
    <RouterLink to="/stock">종목 추가하기</RouterLink>
    <RouterLink to="/book">가계부에 추가하기</RouterLink>
    <table>
      <thead>
        <colgroup>
          <col width="25%" />
          <col width="25%" />
          <col width="25%" />
          <col width="25%" />
        </colgroup>
        <tr>
          <th>이름</th>
          <th>갯수</th>
          <th>가격</th>
          <th>구매여부</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="b in books" :key="b.stock.code">
          <td>{{ b.stock.name }}</td>
          <td>{{ b.count }}</td>
          <td>{{ b.price }}</td>
          <td>{{ b.sellFlag }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>
a {
  border: 1px solid black;
}
</style>
