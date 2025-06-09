import { Component } from '@angular/core';
import {RouterLink} from '@angular/router';
import {EventModel, EventService} from '../../core/services/event.service';
import {data} from 'autoprefixer';
import {MatCard, MatCardActions, MatCardContent, MatCardHeader,MatCardModule} from '@angular/material/card';
import {CommonModule, DatePipe} from '@angular/common';
import {MatIcon} from '@angular/material/icon';

@Component({
  selector: 'app-admin-dashboard',
  imports: [RouterLink, MatCard, MatCardHeader, MatCardContent, MatCardActions, DatePipe, CommonModule, MatCardModule, MatIcon],
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css'
})
export class AdminDashboardComponent {

  events: EventModel[]= [];

  constructor(private eventService : EventService) {
  }

  ngOnInit(): void{
    this.eventService.getEvents().subscribe({
      next: data => this.events = data,
      error : err => alert("Erreur chargement événements: " + err.message)
    })
  }
}
