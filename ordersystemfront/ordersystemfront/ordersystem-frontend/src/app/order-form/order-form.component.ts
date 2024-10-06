import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { ToastModule } from 'primeng/toast';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { MessageService } from 'primeng/api';
import { InputTextModule } from 'primeng/inputtext';
import { InputNumberModule } from 'primeng/inputnumber';
import { CardModule } from 'primeng/card';
import { OrderService } from '../services/order.service';
import { CalendarModule } from 'primeng/calendar';
import { DropdownModule } from 'primeng/dropdown';
import { Client } from '../models/client';
import { ClientService } from '../services/client.service';

@Component({
  selector: 'app-order-form',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    FormsModule,
    ButtonModule,
    ToastModule,
    RouterModule,
    InputTextModule,
    InputNumberModule,
    CardModule,
    CalendarModule,
    DropdownModule
  ],
  templateUrl: './order-form.component.html',
  styleUrl: './order-form.component.scss'
})
export class OrderFormComponent {

  formOrder!: FormGroup
  isSaveInProgress: boolean = false;
  edit: boolean = false;
  clients: Client[] = [];

  constructor(private fb : FormBuilder,
    private orderService : OrderService,
    private clientService: ClientService,
    private activatedRoute : ActivatedRoute,
    private messageService : MessageService,
    private router : Router
  ){
    this.formOrder = this.fb.group({
      code: ['', Validators.required],
      orderDate: ['', Validators.required],
      articles: [[], Validators.required],
      client: [null, Validators.required],
      totalValueOrder: [0, [Validators.required, Validators.min(0)]]
    });
  }

  ngOnInit(): void{

    let code = this.activatedRoute.snapshot.paramMap.get('code');
    if(code !== 'new' && code !== null){
      this.edit = true;
      this.getOrderByCode(code);
    }

    this.getAllClients();
  }

  getOrderByCode(code : string) {
    this.orderService.getOrderByCode(code).subscribe({
      next:foundOrder=>{
        this.formOrder.patchValue(foundOrder);
      },
      error:()=>{
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'No encontrado' });
        this.router.navigateByUrl('/order-view');
      }
    });
  }

  createOrder() {
    this.isSaveInProgress = true;
    if (this.formOrder.invalid) {
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Por favor revise los campos e intente de nuevo.'
      });
      this.isSaveInProgress = false;
      return;
    }
    this.orderService.createOrder(this.formOrder.value).subscribe({
      next:()=>{
        this.messageService.add({
          severity: 'success',
          summary: 'Guardado',
          detail: 'Orden guardada correctamente.'
        });
        this.router.navigateByUrl('/order-view');
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


  updateOrder() {
    this.isSaveInProgress = true;
    if (this.formOrder.invalid) {
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Por favor revise los campos e intente de nuevo.'
      });
      this.isSaveInProgress = false;
      return;
    }
    this.orderService.updateOrder(this.formOrder.value).subscribe({
      next:()=>{
        this.messageService.add({
          severity: 'success',
          summary: 'Actualizado',
          detail: 'Orden actualizada correctamente.'
        });
        this.router.navigateByUrl('/order-view');
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

  getAllClients() {
    this.clientService.getClients().subscribe(data=>{
      this.clients = data;
    })
  }
}
