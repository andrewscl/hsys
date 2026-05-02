import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule, Location } from '@angular/common';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-login-card',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './login-card.html',
  styleUrl: './login-card.scss',
})
export class LoginCard {
  // Recibe el formulario creado en el componente padre
  @Input({ required: true}) loginForm!: FormGroup;

  // Notifica al padre cuando se hace submit para
  // ejecutar la logica de login
  @Output() loginSubmitted = new EventEmitter<void>();

  constructor(private location: Location){}

  goBack(): void {
    this.location.back();
  }

  onSubmit(): void {
    if (this.loginForm.valid){
      this.loginSubmitted.emit();
    }
  }

}
