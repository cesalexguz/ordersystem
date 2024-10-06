import { Cart } from "./cart";
import { Client } from "./client";

export interface Order {
  code: string;
  orderDate: Date;
  articlesCart: Cart[];
  client: Client;
  totalValueOrder: number;
}
