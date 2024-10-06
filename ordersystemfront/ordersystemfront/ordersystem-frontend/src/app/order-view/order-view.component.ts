import { Component } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { RouterModule } from '@angular/router';
import { MessageService } from 'primeng/api';
import { ToastModule } from 'primeng/toast';
import { DataViewModule } from 'primeng/dataview';
import { CommonModule } from '@angular/common';
import { Order } from '../models/order';
import { OrderService } from '../services/order.service';

@Component({
  selector: 'app-order-view',
  standalone: true,
  imports: [
    ButtonModule,
    RouterModule,
    ToastModule,
    DataViewModule,
    CommonModule
  ],
  templateUrl: './order-view.component.html',
  styleUrl: './order-view.component.scss'
})
export class OrderViewComponent {
  orders: Order[] = [];
  isDeleteInProgress: boolean = false;

  constructor(
    private orderService: OrderService,
    private messageService : MessageService
  ) {}

  ngOnInit(): void{
    this.getAllOrders();
  }

  getAllOrders() {
    this.orderService.getOrders().subscribe(data=>{
      this.orders = data;
    })
  }

  deleteOrder(code : string) {
    this.isDeleteInProgress = true;
    this.orderService.deletOrderByCode(code).subscribe({
      next:()=>{
        this.messageService.add({
          severity: 'success',
          summary: 'Eliminado',
          detail: 'Orden eliminada correctamente.'
        });
        this.isDeleteInProgress = false;
        this.getAllOrders();
      },
      error:()=>{
        this.isDeleteInProgress = false;
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'No se pudo eliminar la orden. Intente nuevamente.'
        });
      }
    });
  }
}
