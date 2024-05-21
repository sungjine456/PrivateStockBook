import { createRouter, createWebHistory } from "vue-router";
import MainView from "../views/MainView.vue";
import StockAddView from "@/views/StockAddView.vue";
import BookAddView from "@/views/BookAddView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "main",
      component: MainView
    },
    {
      path: "/stock",
      name: "stockAdd",
      component: StockAddView
    },
    {
      path: "/book",
      name: "bookAdd",
      component: BookAddView
    }
  ]
});

export default router;
