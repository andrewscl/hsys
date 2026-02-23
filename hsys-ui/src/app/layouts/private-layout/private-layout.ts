import { Component, signal, OnInit } from '@angular/core';
import { RouterOutlet, Router, NavigationStart,
  NavigationEnd, } from '@angular/router';
import { CommonModule } from '@angular/common';

import { PrivateTopbar } from
'../../components/navigation/private-topbar/private-topbar';

import { PrivateSidebar} from
'../../components/navigation/private-sidebar/private-sidebar';

import { PrivateFooter} from
'../../components/footers/private-footer/private-footer';

import { LoadingSpinner} from
'../../components/shared/loaders/loading-spinner/loading-spinner';

import { Breadcrumbs } from
'../../components/shared/navigation/breadcrumbs/breadcrumbs';

import { Notifications } from
'../../components/shared/feedback/notifications/notifications';

@Component({
  selector: 'app-private-layout',
  standalone: true,
  imports: [
    RouterOutlet,
    CommonModule,
    PrivateTopbar,
    PrivateSidebar,
    PrivateFooter,
    LoadingSpinner,
    Breadcrumbs,
    Notifications],

  templateUrl: './private-layout.html',
  styleUrl: './private-layout.scss',
})
export class PrivateLayout implements OnInit {

  protected isLoading = signal(false);
  protected showSidebar = signal(true);

  constructor (private router: Router) {}

  ngOnInit() {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationStart) {
        this.isLoading.set(true);
      } else if (event instanceof NavigationEnd) {
        this.isLoading.set(false);
      }
    });
  }

  toggleSidebar() {
    this.showSidebar.update(value => !value);
  }


}
