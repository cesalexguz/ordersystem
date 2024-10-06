import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ClientFormComponent } from './client-form/client-form.component';
import { ClientViewComponent } from './client-view/client-view.component';
import { ArticleFormComponent } from './article-form/article-form.component';
import { ArticleViewComponent } from './article-view/article-view.component';
import { OrderFormComponent } from './order-form/order-form.component';
import { OrderViewComponent } from './order-view/order-view.component';

export const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    title: 'Sistema de Ordenes'
  },
  {
    path: 'client-view',
    component: ClientViewComponent,
    title: 'Vista de Clientes'
  },
  {
    path: 'client-form/:id',
    component: ClientFormComponent,
    title: 'Registro de Clientes'
  },
  {
    path: 'article-view',
    component: ArticleViewComponent,
    title: 'Vista de Articulos'
  },
  {
    path: 'article-form/:code',
    component: ArticleFormComponent,
    title: 'Registro de Articulos'
  },
  {
    path: 'order-view',
    component: OrderViewComponent,
    title: 'Vista de Ordenes'
  },
  {
    path: 'order-form/:code',
    component: OrderFormComponent,
    title: 'Registro de Ordenes'
  },
  {
    path: '**',
    redirectTo: '',
    pathMatch: 'full'
  }
];
