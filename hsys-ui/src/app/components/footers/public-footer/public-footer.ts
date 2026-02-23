import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-public-footer',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './public-footer.html',
  styleUrl: './public-footer.scss',
})
export class PublicFooter {
    protected readonly appName = 'Hsys';
    protected readonly currentYear = new Date().getFullYear();

    protected readonly quickLinks = [
      { label: 'Inicio', route: '/' },
      { label: 'Nosotros', route: '/about' },
      { label: 'Contacto', route: '/contact' },
      { label: 'Terminos', route: '/terms' }
    ]; 

    protected readonly socialLinks = [
      { name: 'Linkedin', url: 'https://www.linkedin.com', icon: 'linkedin' },
      { name: 'X', url: 'https://www.x.com', icon: 'X' },
    ]

    //Metodo para obtener iconos
    protected getSocialIcon (icon: string): string {
      const icons: { [key: string]: string } = {
        'linkedin': '💼',
        'twitter': '🐦',
        'github': '🐙',
        'facebook': '👍',
        'instagram': '📷'
      };
      return icons[icon] || '🔗';
    }
}
