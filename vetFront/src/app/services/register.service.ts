import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  private registerUrl = 'http://localhost:9090/api/vet/register';

  constructor(private http: HttpClient) { }

  register(userData: UserRegistrationData): Observable<any>{
    return this.http.post(this.registerUrl, userData, { responseType: 'text' });
  }

  
}

export interface UserRegistrationData {
  nombre: string;
  apellido: string;
  email: string;
  telefono: string;
  password: string;}
