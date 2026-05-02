import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserClientAccess } from '../models/user-client-access-model';

@Injectable({ providedIn: 'root'})
export class UserAccessService{
    private http = inject(HttpClient);
    private readonly API_URL = '/api/v1/admin/user-access';

    // Obtener lista paginada de la tabla del dashboard
    findAll(page: number = 0, size: number = 10): 
                        Observable<UserClientAccess[]> {
        const params = new HttpParams()
            .set('page', page.toString())
            .set('size', size.toString());
        return this.http.get<UserClientAccess[]>
                        (this.API_URL, {params});
    }

    // Obtener los accesos de un usuario especifico
    findByUserId(userId: string, page: number = 0):
                        Observable<UserClientAccess[]> {
        const params = new HttpParams()
            .set('page', page.toString());
        return this.http.get<UserClientAccess[]>
                        (`${this.API_URL}/user/${userId}`, {params});
    }

}