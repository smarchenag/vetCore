import { Component } from '@angular/core';
import { UsuariosService } from '../../services/usuarios.service';  
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  username = '';
  password = '';

  constructor(private authService:UsuariosService, private router: Router) { }

  login() : void {
      this.authService.login(this.username, this.password).subscribe(
          (token:any) => {
              this.authService.storeToken(token);
              this.router.navigate(['/']);
              console.log('Login exitoso',token); 
          },
          (error) => {
              console.error('Login fallido',error);
          }
      );

  }

}
