import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ArticleService } from '../services/article.service';
import { ButtonModule } from 'primeng/button';
import { ToastModule } from 'primeng/toast';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { MessageService } from 'primeng/api';
import { InputTextModule } from 'primeng/inputtext';
import { InputNumberModule } from 'primeng/inputnumber';
import { CardModule } from 'primeng/card';

@Component({
  selector: 'app-article-form',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    FormsModule,
    ButtonModule,
    ToastModule,
    RouterModule,
    InputTextModule,
    InputNumberModule,
    CardModule
  ],
  templateUrl: './article-form.component.html',
  styleUrl: './article-form.component.scss'
})
export class ArticleFormComponent {

  formArticle!: FormGroup
  isSaveInProgress: boolean = false;
  edit: boolean = false;

  constructor(private fb : FormBuilder,
    private articleService : ArticleService,
    private activatedRoute : ActivatedRoute,
    private messageService : MessageService,
    private router : Router
  ){
    this.formArticle = this.fb.group({
      code: ['', Validators.required],
      name: ['', Validators.required],
      unitPrice: [0, [Validators.required, Validators.min(0)]]
    });
  }

  ngOnInit(): void{

    let code = this.activatedRoute.snapshot.paramMap.get('code');
    if(code !== 'new' && code !== null){
      this.edit = true;
      this.getArticleByCode(code);
    }
  }

  getArticleByCode(code : string) {
    this.articleService.getArticleByCode(code).subscribe({
      next:foundArticle=>{
        this.formArticle.patchValue(foundArticle);
      },
      error:()=>{
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'No encontrado' });
        this.router.navigateByUrl('/article-view');
      }
    });
  }

  createArticle() {
    this.isSaveInProgress = true;
    if (this.formArticle.invalid) {
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Por favor revise los campos e intente de nuevo.'
      });
      this.isSaveInProgress = false;
      return;
    }
    this.articleService.createArticle(this.formArticle.value).subscribe({
      next:()=>{
        this.messageService.add({
          severity: 'success',
          summary: 'Guardado',
          detail: 'Articulo guardado correctamente.'
        });
        this.router.navigateByUrl('/article-view');
      },
      error:()=>{
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'Por favor revise los campos e intente de nuevo.'
        });
        this.isSaveInProgress = false;
      }
    });
  }


  updateArticle() {
    this.isSaveInProgress = true;
    if (this.formArticle.invalid) {
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Por favor revise los campos e intente de nuevo.'
      });
      this.isSaveInProgress = false;
      return;
    }
    this.articleService.updateArticle(this.formArticle.value).subscribe({
      next:()=>{
        this.messageService.add({
          severity: 'success',
          summary: 'Actualizado',
          detail: 'Articulo actualizado correctamente.'
        });
        this.router.navigateByUrl('/article-view');
      },
      error:()=>{
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'Por favor revise los campos e intente de nuevo.'
        });
        this.isSaveInProgress = false;
      }
    });
  }
}
