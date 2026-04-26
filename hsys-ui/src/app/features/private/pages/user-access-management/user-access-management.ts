import { Component, inject, OnInit } from '@angular/core';
import { UserAccessService } from '../../../../core/services/user-access-service';
import { UserClientAccess } from '../../../../core/models/user-client-access-model';
import { UserAccessTableComponent } from '../../components/user-access-table/user-access-table';

@Component({
  selector: 'app-user-access-management',
  standalone : true,
  imports: [UserAccessTableComponent],
  templateUrl: './user-access-management.html',
  styleUrl: './user-access-management.scss',
})

export class UserAccessManagementComponent implements OnInit{
  private userAccessService = inject(UserAccessService);

  //Lista de datos que contendra la tabla
  public accessList : UserClientAccess[] = [];

  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    this.userAccessService.findAll(0, 10).subscribe({
      next: (response) => {
        this.accessList = response.content;
        console.log('Datos cargados:', this.accessList);
      },
      error: (err) => console.error('Error al cargar datos:', err)
    });
  }

}
