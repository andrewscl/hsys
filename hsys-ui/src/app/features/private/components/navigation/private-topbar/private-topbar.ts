import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-private-topbar',
  imports: [],
  templateUrl: './private-topbar.html',
  styleUrl: './private-topbar.scss',
})
export class PrivateTopbar {

  @Output() toggleSidebar = new EventEmitter<void>();

  onToggle() {
    this.toggleSidebar.emit();
  }

}
