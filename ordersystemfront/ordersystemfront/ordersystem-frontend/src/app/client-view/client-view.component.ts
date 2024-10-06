import { Component } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { Client } from '../models/client';
import { ClientService } from '../services/client.service';
import { RouterModule } from '@angular/router';
import { MessageService } from 'primeng/api';
import { ToastModule } from 'primeng/toast';
import { DataViewModule } from 'primeng/dataview';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-client-view',
  standalone: true,
  imports: [
    ButtonModule,
    RouterModule,
    ToastModule,
    DataViewModule,
    CommonModule
  ],
  templateUrl: './client-view.component.html',
  styleUrl: './client-view.component.scss'
})
export class ClientViewComponent {
  clients: Client[] = [];
  isDeleteInProgress: boolean = false;

  constructor(
    private clientService: ClientService,
    private messageService : MessageService
  ) {}

  ngOnInit(): void{
    this.getAllClients();
  }

  getAllClients() {
    this.clientService.getClients().subscribe(data=>{
      this.clients = data;
    })
  }

  deleteClient(id : number) {
    this.isDeleteInProgress = true;
    this.clientService.deleteClientById(id).subscribe({
      next:()=>{
        this.messageService.add({
          severity: 'success',
          summary: 'Eliminado',
          detail: 'Cliente eliminado correctamente.'
        });
        this.isDeleteInProgress = false;
        this.getAllClients();
      },
      error:()=>{
        this.isDeleteInProgress = false;
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'No se pudo eliminar el cliente. Intente nuevamente.'
        });
      }
    });
  }
}
