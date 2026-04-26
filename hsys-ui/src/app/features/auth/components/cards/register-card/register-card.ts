import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms'
import { Location, CommonModule } from '@angular/common';
import { AuthService } from '../../../../../core/services/auth-service';
import { Router } from '@angular/router';
import { RegisterRequest } from '../../../../../core/models/auth-model';

@Component({
  selector: 'app-register-card',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './register-card.html',
  styleUrl: './register-card.scss',
})
export class RegisterCard implements OnInit{

  private fb = inject(FormBuilder);
  private location = inject(Location);
  private authService = inject(AuthService);
  private router = inject(Router);

  registerForm!: FormGroup;
  isSubmitting = false; //estado para el boton

  ngOnInit(): void {

    const userTimezone = Intl.DateTimeFormat().resolvedOptions().timeZone;

    this.registerForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(3),Validators.maxLength(64)]],
      mail: ['', [Validators.required, Validators.email]],
      phone: [''],
      password: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(128)]],
      companyName: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(160)]],
      taxId: ['',  [Validators.required, Validators.minLength(4), Validators.maxLength(20)]],
      timezone: [this.mapTimezone(userTimezone), Validators.required]
    });
  }

  onCreateAccount(): void {

    // Si el formulario es inválido, marca todo y sale
    if(this.registerForm.invalid) {
      this.registerForm.markAllAsTouched();
      return;
    }

    // Formulario valido
    this.isSubmitting = true;

    //Tipar explicitamente el valor del formulario
    const request: RegisterRequest = this.registerForm.value;

    //Log consola navegador
    console.log('Enviando solicitud de registro: ', JSON.stringify(request));

    this.authService.register(request).subscribe({
      next: (response) => {
        console.log('cuenta creada con exito!', response);
        this.router.navigate(['/auth/login']);
      },
      error: (err) => {
        this.isSubmitting = false;
          const errorMessage = err.error?.message || 'Error en el servidor, inténtalo más tarde.';
          console.error('Detalles del error: ', err);
        }
      });

  }

  goBack(): void {
    this.location.back();
  }

  private mapTimezone(tz: string): string {
    if (tz.includes('Bogota') || tz.includes('Lima') || tz.includes('Quito')) return 'UTC-5';
    if (tz.includes('Santiago') || tz.includes('Caracas') || tz.includes('Asuncion')) return 'UTC-4';
    if (tz.includes('Buenos_Aires') || tz.includes('Montevideo')) return 'UTC-3';
    if (tz.includes('Madrid') || tz.includes('Paris') || tz.includes('Berlin')) return 'UTC+1';
    
    return 'UTC-5'; // Valor por defecto si no reconoce la zona
  }

}
