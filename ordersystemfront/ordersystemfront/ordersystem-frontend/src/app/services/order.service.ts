import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Order } from '../models/order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private apiUrl = 'http://localhost:8080/order';

  constructor(private http:HttpClient) { }

  getOrders() : Observable<Order[]> {
    return this.http.get<Order[]>(this.apiUrl);
  }

  getOrderByCode(code : string) : Observable<Order> {
    return this.http.get<Order>(`${this.apiUrl}/${code}`);
  }

  createOrder(order : Order) : Observable<Order> {
    return this.http.post<Order>(this.apiUrl, order);
  }

  updateOrder(order : Order) {
    return this.http.put(this.apiUrl, order);
  }

  deletOrderByCode(code : string) {
    return this.http.delete<Order>(`${this.apiUrl}/${code}`);
  }

}
