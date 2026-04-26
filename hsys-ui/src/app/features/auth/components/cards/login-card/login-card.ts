import { Component } from '@angular/core';
import { Location } from '@angular/common';

@Component({
  selector: 'app-login-card',
  imports: [],
  templateUrl: './login-card.html',
  styleUrl: './login-card.scss',
})
export class LoginCard {

  constructor(private location: Location){}

  goBack(): void {
    this.location.back();
  }

}
