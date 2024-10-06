import { Component } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { Client } from '../models/client';
import { ClientService } from '../services/client.service';
import { RouterModule } from '@angular/router';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    ButtonModule,
    RouterModule
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {

  ngOnInit(): void{
  }

}
