import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegisterCard } from '../cards/register-card/register-card';

@Component({
  selector: 'app-register',
  imports: [CommonModule, RegisterCard],
  templateUrl: './register.html',
  styleUrl: './register.scss',
})
export class Register {

}