import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Article } from '../models/article';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  private apiUrl = 'http://localhost:8080/article';

  constructor(private http:HttpClient) { }

  getArticles():Observable<Article[]> {
    return this.http.get<Article[]>(this.apiUrl);
  }

  getArticleByCode(code : string) : Observable<Article> {
    return this.http.get<Article>(`${this.apiUrl}/${code}`);
  }

  createArticle(article : Article) : Observable<Article> {
    return this.http.post<Article>(this.apiUrl, article);
  }

  updateArticle(article : Article) {
    return this.http.put(this.apiUrl, article);
  }

  deleteArticleByCode(code : string) {
    return this.http.delete<Article>(`${this.apiUrl}/${code}`);
  }

}
