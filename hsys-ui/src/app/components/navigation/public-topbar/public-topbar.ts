import { Component, inject } from '@angular/core';
import { Router } from '@angular/router'; //Servicio con metodos logicos para navegar entre rutas
import { CommonModule } from '@angular/common';
import { RouterLink, RouterLinkActive} from '@angular/router'; //Extensiones para etiquetas HTML

import { PublicTopbarInterface } from './public-topbar-interface';

@Component({
  selector: 'app-public-topbar',
  standalone: true,
  imports: [CommonModule,
            RouterLink,
            RouterLinkActive],
  templateUrl: './public-topbar.html',
  styleUrl: './public-topbar.scss',
})

export class PublicTopbar {

  //Injeccion del servicio Router para navegar entre rutas
  private router = inject(Router);

  menuLinks: PublicTopbarInterface[] = [
    { label: 'Inicio', url: '/'},
    { label: 'Nosotros', url: '/nosotros'},
    { label: 'Soluciones', url: '/soluciones'},
    { label: 'Servicios', url: '/servicios'},
    { label: 'Contacto', url: '/contacto'},
  ];

  //Funcion que llama al boton (click)
  onLogin(): void {
    this.router.navigate(['/auth/login']);
  }

}