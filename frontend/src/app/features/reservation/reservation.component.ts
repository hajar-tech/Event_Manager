import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {MatFormField, MatLabel} from '@angular/material/input';

@Component({
  selector: 'app-reservation',
  imports: [
    ReactiveFormsModule,
    MatFormField,
    MatLabel
  ],
  templateUrl: './reservation.component.html',
  styleUrl: './reservation.component.css'
})
export class ReservationComponent implements  OnInit{
  reservationForm : FormGroup;
  eventId! : number;
  userId! : number;

  constructor(private route : ActivatedRoute,
              private fb : FormBuilder,
              private http : HttpClient,
              private router :Router) {
    this.reservationForm = this.fb.group({
      numberOfSeats: ['', [Validators.required, Validators.min(1)]]
    })
  }

  ngOnInit() : void {

  //récuperation de id  event
    this.eventId = Number(this.route.snapshot.paramMap.get('id'));
    console.log('Event ID:', this.eventId);

    const storedUser = localStorage.getItem('user');
    if(storedUser){
      try{
        const user = JSON.parse(storedUser);
        this.userId = user.id;
      }catch (e){
        console.error('Erreur de parsing Json : ', e);
        alert('Utilisateur invalide. Veuillez vous reconnecter.');
        this.router.navigate(['/login']);
      }
    }else{
      alert('Utilisateur non connecté. Veuillez vous authentifier.');
      this.router.navigate(['/login']);
    }
  }

  onSubmit():void{
    const data = {
      numberOfSeats: this.reservationForm.value.numberOfSeats,
      userId: this.userId,
      eventId: this.eventId
    };

    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization',`Bearer ${token}`);

    this.http.post('http://localhost:8080/api/client/reserver-event',data,{headers}).subscribe({
      next: () => alert('Réservation effectuée !'),
      error: err => alert('Erreur : ' + err.message)
    })
  }

}
