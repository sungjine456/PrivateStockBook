<script setup lang="ts">
import type Stock from "@/domains/Stock";
import axios from "axios";
import { ref } from "vue";

const stocks = ref<Stock[]>([]);

{
  axios
    .get("/api/stock")
    .then((res) => {
      stocks.value = res.data;
    })
    .catch((e) => {
      console.log("get stock err", e);
    });
}
</script>

<template>
  <div class="main">
    <RouterLink to="/stock">추가하기</RouterLink>
    <table>
      <thead>
        <colgroup>
          <col width="34%" />
          <col width="33%" />
          <col width="33%" />
        </colgroup>
        <tr>
          <th>이름</th>
          <th>코드</th>
          <th>종류</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="s in stocks" :key="s.code">
          <td>{{ s.name }}</td>
          <td>{{ s.code }}</td>
          <td>{{ s.stockKind }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
