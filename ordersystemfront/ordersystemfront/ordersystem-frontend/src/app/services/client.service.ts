import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Client } from '../models/client';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  private apiUrl = 'http://localhost:8080/client';

  constructor(private http:HttpClient) { }

  getClients() : Observable<Client[]> {
    return this.http.get<Client[]>(this.apiUrl);
  }

  getClientById(id : number) : Observable<Client> {
    return this.http.get<Client>(`${this.apiUrl}/${id}`);
  }

  createClient(client : Client) : Observable<Client> {
    return this.http.post<Client>(this.apiUrl, client);
  }

  updateClient(client : Client) {
    return this.http.put(this.apiUrl, client);
  }

  deleteClientById(id : number) {
    return this.http.delete<Client>(`${this.apiUrl}/${id}`);
  }

}
