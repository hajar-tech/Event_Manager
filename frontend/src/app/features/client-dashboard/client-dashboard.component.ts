import { Component } from '@angular/core';
import {EventModel, EventService} from '../../core/services/event.service';
import {DatePipe, NgForOf} from '@angular/common';
import {
  MatCard,
  MatCardActions,
  MatCardAvatar,
  MatCardContent,
  MatCardHeader,
  MatCardSubtitle, MatCardTitle
} from '@angular/material/card';
import {MatIcon} from '@angular/material/icon';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-client-dashboard',
  imports: [
    DatePipe,
    MatCard,
    MatCardActions,
    MatCardAvatar,
    MatCardContent,
    MatCardHeader,
    MatCardSubtitle,
    MatCardTitle,
    MatIcon,
    NgForOf,
    RouterLink
  ],
  templateUrl: './client-dashboard.component.html',
  styleUrl: './client-dashboard.component.css'
})
export class ClientDashboardComponent {
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
