import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginCard } from '../cards/login-card/login-card';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../../../core/services/auth-service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, LoginCard],
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class Login {
  private fb = inject(FormBuilder);
  private router = inject(Router);
  private authService = inject(AuthService);

  // Definición del formulario con validaciones
  public loginForm: FormGroup = this.fb.group({
    username: ['', [Validators.required, Validators.minLength(3)]],
    password: ['', [Validators.required]]
  });

  onLogin() {
    if (this.loginForm.valid) {
      // Llama al AuthService
      this.authService.login(this.loginForm.value).subscribe({
        next: (res) => {
          console.log('token recibido y guardado');
          this.router.navigate(['/admin/dashboard']);
        },
        error: (err) => {
          alert('Credenciales invalidas. El backend te ha rechazado');
        }
      });
    } else {
      this.loginForm.markAllAsTouched();
    }
  }

}
