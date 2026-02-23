import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

//Importa la interfaz para tipar el array de objetos.
import { ServicePillInterface } from
'../../../components/shared/pills/service-pill/service-pill-interface';

@Component({
  selector: 'app-public-home',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './public-home.html',
  styleUrl: './public-home.scss',
})
export class PublicHome {

  //Array de objetos con la información de las apps
  appServices : ServicePillInterface[] = [
    { name: 'Control de Asistencia', icon: 'fa-solid fa-clock-rotate-left' },
    { name: 'Recursos Humanos', icon: 'fa-solid fa-users-gear' },
    { name: 'Ventas y Facturación', icon: 'fa-solid fa-file-invoice-dollar' },
    { name: 'Contro de Rondas', icon: 'fa-solid fa-route' },
    { name: 'Gestión de Inventario', icon: 'fa-solid fa-boxes-stacked' },
    { name: 'CRM Comercial', icon: 'fa-solid fa-chart-line'}

  ];


  
}
