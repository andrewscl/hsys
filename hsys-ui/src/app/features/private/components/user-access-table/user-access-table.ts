import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { UserClientAccess } from '../../../../core/models/user-client-access-model';

@Component({
  selector: 'app-user-access-table',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './user-access-table.html',
  styleUrl: './user-access-table.scss',
})
export class UserAccessTableComponent {

  @Input() dataSource: UserClientAccess[] = [];

}