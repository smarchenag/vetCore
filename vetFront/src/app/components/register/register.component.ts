import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { RegisterService, UserRegistrationData } from 'src/app/services/register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  nombre: string = '';
  apellido: string = '';
  email: string = '';
  telefono: string = '';
  password: string = '';

  constructor(private service: RegisterService, private router: Router){}

  register(): void {

    const userData: UserRegistrationData = {
      nombre: this.nombre,
      apellido: this.apellido,
      email: this.email,
      telefono: this.telefono,
      password: this.password,
    };

    this.service.register(userData).subscribe(
      (response) => {
        console.log('Registro exitoso', response);
        this.router.navigate(['/login']); // Redirige al login después del registro
      },
      (error) => {
        console.error('Error en el registro', error);
      }
    );
  }

}
