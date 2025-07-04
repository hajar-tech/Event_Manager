import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import {Router, RouterLink} from '@angular/router';
import {AuthService} from '../../core/services/auth.service';
import {response} from 'express';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;
  errorMessage : string = "";

  constructor(private fb: FormBuilder, private router: Router , private authService : AuthService) {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      console.log(this.loginForm.value);

      const dataLogin = this.loginForm.value;
      this.authService.login(dataLogin).subscribe({
        next: (response) => {
          localStorage.setItem('token', response.token);
          localStorage.setItem('role', response.role);
          if (response.user) {
            localStorage.setItem('user', JSON.stringify(response.user));
          } else {
            console.error("Le champ 'user' est manquant dans la réponse du backend.");
          }

          //rediriger l'utilisateur selon leur role
          if(response.role === 'Admin'){
            alert("welcome admin")
             this.router.navigate(['/admin-dashboard']);
          }else {
            alert("welcome client")
            this.router.navigate(['/client-dashboard']);
          }
        },
        error: (err)=>{
          this.errorMessage = typeof err === 'string' ? err : 'Login failed';
        }
      })
    }
  }

  goToRegister() {
    this.router.navigate(['/register']);
  }
}
