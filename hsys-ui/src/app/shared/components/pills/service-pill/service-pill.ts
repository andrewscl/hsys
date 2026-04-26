import { Component, Input } from '@angular/core';
import { ServicePillInterface } from './service-pill-interface';

@Component({
  selector: 'app-service-pill',
  standalone: true,
  imports: [],
  templateUrl: './service-pill.html',
  styleUrl: './service-pill.scss',
})
export class ServicePill {

  //Los @Input permiten que el padre envie datos a estas variables.
  @Input() data!: ServicePillInterface;

}
