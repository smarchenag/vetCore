import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UsuariosService } from 'src/app/services/usuarios.service';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css'],
})
export class UsuariosComponent implements OnInit {

  nombre = '';
  apellido = '';
  email = '';

  constructor(private authService: UsuariosService, private router: Router) {}


  ngOnInit(): void {
    this.authService.getUsers().subscribe(
      (data) => {
        this.nombre = data[0].nombre;
        this.apellido = data[0].apellido;
        this.email = data[0].email;
        console.log(data);
      },
      (error) => {
        console.error('Error al obtener los usuarios', error);
      }
    );
  }

  
}
