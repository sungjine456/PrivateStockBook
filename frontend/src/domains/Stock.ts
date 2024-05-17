export default interface Stock {
  name: string;
  code: string;
  stockKind: StockKind;
}

export enum StockKind {
  ETF = "0",
  채권 = "1",
  주식 = "2",
  금 = "3",
  달러 = "4"
}

export const stockKindEntries = Object.entries(StockKind).filter(([_, k]) => k);
