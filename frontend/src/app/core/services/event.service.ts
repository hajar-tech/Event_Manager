import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';

export interface EventModel {
  id?: number;
  titre: string;
  description: string;
  date: Date;
  placesDisponibles: number;
  category: string;
  location: string;
}

@Injectable({
  providedIn: 'root'
})
export class EventService {

  private apiUrl = 'http://localhost:8080/api/admin'; // À adapter

  constructor(private http: HttpClient) {}

  addEvent(event: Event): Observable<Event> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.post<Event>(`${this.apiUrl}/create-event`, event , {headers});
  }


  getEvents():Observable<EventModel[]>{
    return this.http.get<EventModel[]>(`${this.apiUrl}/getEvents`)
  }
}
