import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { RegisterRequest, LoginResponse } from '../models/auth-model';

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private http = inject(HttpClient);
    //Gracias al proxy es posible utilizar api.
    private readonly API_URL = '/api/auth';

    register(data: RegisterRequest): Observable<LoginResponse> {
        return this.http.post<LoginResponse>
                            (`${this.API_URL}/register`, data); 
    }

    // Dashboard
    login(data: any): Observable<LoginResponse> {
        return this.http.post<LoginResponse>
                            (`${this.API_URL}/login`, data).pipe(
            tap(res => {
                //Guardar token para el interceptor
                localStorage.setItem('token', res.accessToken);
            })
        );
    }

    //Metodo para saber si hay alguien logeado
    getToken(): string | null {
        return localStorage.getItem('token');
    }

    logout(): void {
        localStorage.removeItem('token');
    }

}