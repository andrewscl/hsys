import { Component, inject, OnInit } from '@angular/core';
import { UserAccessService } from '../../../../core/services/user-access-service';
import { UserClientAccess } from '../../../../core/models/user-client-access-model';
import { UserAccessTableComponent } from '../../components/user-access-table/user-access-table';
import { Observable } from 'rxjs';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-user-access-management',
  standalone : true,
  imports: [UserAccessTableComponent, AsyncPipe],
  templateUrl: './user-access-management.html',
  styleUrl: './user-access-management.scss',
})

export class UserAccessManagementComponent implements OnInit{
  private userAccessService = inject(UserAccessService);

  //Lista de datos que contendra la tabla
  public accessList$! : Observable<UserClientAccess[]>;

  ngOnInit(): void {
    this.accessList$! = this.userAccessService.findAll(0, 10);
  }

}
