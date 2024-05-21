import type Stock from "./stock";

export default interface Book {
  stock: Stock;
  count: number;
  price: number;
  sellFlag: boolean;
}
