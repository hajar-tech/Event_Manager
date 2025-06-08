import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import {AdminDashboardComponent} from './features/admin-dashboard/admin-dashboard.component';
import {AddEventComponent} from './features/add-event/add-event.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  {path: 'admin-dashboard' , component : AdminDashboardComponent},
  {path : 'admin-dashboard/create-event' , component : AddEventComponent}
];
