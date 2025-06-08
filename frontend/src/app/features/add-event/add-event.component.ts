import { Component } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {EventService} from '../../core/services/event.service';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatSelectModule} from '@angular/material/select';
import {MatNativeDateModule} from '@angular/material/core';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {CommonModule} from '@angular/common';
import {MatCard} from '@angular/material/card';
//import {HttpHeaders} from '@angular/common/module.d-CnjH8Dlt';




@Component({
  selector: 'app-add-event',
  imports: [CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule, MatCard,],
  templateUrl: './add-event.component.html',
  styleUrl: './add-event.component.css'
})



export class AddEventComponent {

  eventForm: FormGroup;

  constructor(private fb: FormBuilder, private eventService: EventService) {
    this.eventForm = this.fb.group({
      titre: ['', Validators.required],
      description: [''],
      date: ['', Validators.required],
      placesDisponibles: [0, [Validators.required, Validators.min(1)]],
      category: ['', Validators.required],
      location: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.eventForm.valid) {
      const newEvent = this.eventForm.value;
      this.eventService.addEvent(newEvent).subscribe({
        next: res => {
          console.log('Événement ajouté avec succès');
          this.eventForm.reset();
        },
        error: err => console.error('Erreur lors de l\'ajout', err)
      });
    }
  }

}
