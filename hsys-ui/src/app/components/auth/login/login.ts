import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginCard } from '../../shared/cards/login-card/login-card';

@Component({
  selector: 'app-login',
  imports: [CommonModule, LoginCard],
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class Login {

}
