import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UsuarioObject } from './model/usuarioObject';

@Injectable({
  providedIn: 'root',
})
export class UsuariosService {
  private apiUrl = 'http://localhost:9090/api/vet/login';

  private apiUsuarios = 'http://localhost:9090/api/vet/list-users';

  constructor(private http: HttpClient) {}

  login(username: string, password: string) {
    return this.http.post(this.apiUrl, {
      username: username,
      password: password,
    },
      { responseType: 'text' });
  }

  getUsers() {
    return this.http.get<Array<UsuarioObject>>(this.apiUsuarios);
  }

  storeToken(token: string) {
    localStorage.setItem('jwtToken', token);
  }

  getToken(): string | null {
    return localStorage.getItem('jwtToken');
  }
}
