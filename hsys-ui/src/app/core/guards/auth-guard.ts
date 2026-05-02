import { inject } from "@angular/core";
import { Router, CanActivateFn } from "@angular/router";
import { AuthService } from "../services/auth-service";

export const authGuard: CanActivateFn = () => {
    const authService = inject(AuthService);
    const router = inject(Router);

    // Si tiene token, se deja pasar
    if (authService.getToken()) {
        return true;
    }

    // Si no tiene token, se envia al login
    console.warn('Acceso denegado: No hay token');
    router.navigate(['/auth/login']);
    return false;
}
