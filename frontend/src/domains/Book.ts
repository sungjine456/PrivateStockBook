import type Stock from "./Stock";

export default interface Book {
  stock: Stock;
  count: number;
  price: number;
  sellFlag: boolean;
}
