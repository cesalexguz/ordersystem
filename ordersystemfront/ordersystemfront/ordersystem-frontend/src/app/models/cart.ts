import { Article } from "./article";

export interface Cart {
  id: number;
  quantity: number;
  article: Article;
}
