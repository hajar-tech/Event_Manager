import { Injectable } from '@angular/core';
import {catchError, Observable, throwError} from 'rxjs';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import * as http from 'node:http';

export interface User {
  id: number;
  username: string;
  name: string;
  role: string;
}



export interface RegisterRequest{
  name: String;
  username : String;
  password : string;
  role : "CLIENT";
}

export interface LoginRequest {
  username: string;
  password: string;
}

export interface LoginResponse {
  token: string;
  type: string;
  role: string;
  user: {
    id: number;
    username: string;
    name:string;
    role: string
    // ajoute d'autres champs si ton backend les retourne
  };
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private  apiUrl = 'http://localhost:8080/api/auth';

  constructor(private http : HttpClient) { }

  register (data: RegisterRequest): Observable<any>{
   return this.http.post(`${this.apiUrl}/register`,data)
     .pipe(
       catchError(this.handleError)
     );
  }

  login(data : LoginRequest):Observable<LoginResponse>{
    return this.http.post<LoginResponse>(`${this.apiUrl}/login`, data)
      .pipe(catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse){
    return throwError(()=>
    error.error instanceof ErrorEvent ? error.error.message : error.error
    );
  }
}
