import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';

import { PublicTopbar } from
'../../components/public-topbar/public-topbar';

import { PublicFooter } from
'../../components/public-footer/public-footer';

@Component({
  selector: 'app-public-layout',
  standalone: true,
  imports: [
    RouterOutlet,
    CommonModule,
    PublicTopbar,
    PublicFooter],
  templateUrl: './public-layout.html',
  styleUrl: './public-layout.scss',
})
export class PublicLayout {}
