import { Component } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { Article } from '../models/article';
import { ArticleService } from '../services/article.service';
import { RouterModule } from '@angular/router';
import { MessageService } from 'primeng/api';
import { ToastModule } from 'primeng/toast';
import { DataViewModule } from 'primeng/dataview';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-article-view',
  standalone: true,
  imports: [
    ButtonModule,
    RouterModule,
    ToastModule,
    DataViewModule,
    CommonModule
  ],
  templateUrl: './article-view.component.html',
  styleUrl: './article-view.component.scss'
})
export class ArticleViewComponent {

  articles: Article[] = [];
  isDeleteInProgress: boolean = false;

  constructor(
    private articleService: ArticleService,
    private messageService : MessageService
  ) {}

  ngOnInit(): void{
    this.getAllArticles();
  }

  getAllArticles() {
    this.articleService.getArticles().subscribe(data=>{
      this.articles = data;
    })
  }

  deleteArticle(code : string) {
    this.isDeleteInProgress = true;
    this.articleService.deleteArticleByCode(code).subscribe({
      next:()=>{
        this.messageService.add({
          severity: 'success',
          summary: 'Eliminado',
          detail: 'Articulo eliminado correctamente.'
        });
        this.isDeleteInProgress = false;
        this.getAllArticles();
      },
      error:()=>{
        this.isDeleteInProgress = false;
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'No se pudo eliminar el articulo. Intente nuevamente.'
        });
      }
    });
  }
}
