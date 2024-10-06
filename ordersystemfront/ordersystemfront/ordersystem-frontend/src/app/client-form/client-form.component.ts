import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ClientService } from '../services/client.service';
import { ButtonModule } from 'primeng/button';
import { ToastModule } from 'primeng/toast';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { MessageService } from 'primeng/api';
import { InputTextModule } from 'primeng/inputtext';
import { InputNumberModule } from 'primeng/inputnumber';
import { CardModule } from 'primeng/card';

@Component({
  selector: 'app-client-form',
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
  templateUrl: './client-form.component.html',
  styleUrl: './client-form.component.scss'
})
export class ClientFormComponent {

  formClient!: FormGroup
  isSaveInProgress: boolean = false;
  edit: boolean = false;

  constructor(private fb : FormBuilder,
    private clientService : ClientService,
    private activatedRoute : ActivatedRoute,
    private messageService : MessageService,
    private router : Router
  ){
    this.formClient = this.fb.group({
      id: [null],
      name: ['', Validators.required],
      lastname: ['', Validators.required]
    });
  }

  ngOnInit(): void{

    let id = this.activatedRoute.snapshot.paramMap.get('id');
    if(id !== 'new'){
      this.edit = true;
      this.getClientById(+id!);
    }
  }

  getClientById(id : number) {
    this.clientService.getClientById(id).subscribe({
      next:foundClient=>{
        this.formClient.patchValue(foundClient);
      },
      error:()=>{
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'No encontrado' });
        this.router.navigateByUrl('/client-view');
      }
    });
  }

  createClient() {
    this.isSaveInProgress = true;
    if (this.formClient.invalid) {
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Por favor revise los campos e intente de nuevo.'
      });
      this.isSaveInProgress = false;
      return;
    }
    this.clientService.createClient(this.formClient.value).subscribe({
      next:()=>{
        this.messageService.add({
          severity: 'success',
          summary: 'Guardado',
          detail: 'Cliente guardado correctamente.'
        });
        this.router.navigateByUrl('/client-view');
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


  updateClient() {
    this.isSaveInProgress = true;
    if (this.formClient.invalid) {
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Por favor revise los campos e intente de nuevo.'
      });
      this.isSaveInProgress = false;
      return;
    }
    this.clientService.updateClient(this.formClient.value).subscribe({
      next:()=>{
        this.messageService.add({
          severity: 'success',
          summary: 'Actualizado',
          detail: 'Cliente actualizado correctamente.'
        });
        this.router.navigateByUrl('/client-view');
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
