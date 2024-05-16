<script setup lang="ts">
import axios from "axios";
import { ref } from "vue";

const stocks = ref([]);

{
  axios
    .get("/api/stock")
    .then((res) => {
      console.log("get stock", res);
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
          <col width="100%" />
        </colgroup>
        <tr>
          <th>이름</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="s in stocks" :key="s">
          <td>{{ s }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
