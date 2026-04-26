import { Routes } from '@angular/router';
import { PublicLayout } from './features/public/layouts/public-layout/public-layout';
import { AuthLayout } from './features/auth/layouts/auth-layout/auth-layout';

import { PublicHome } from './features/public/pages/public-home/public-home';
import { Login } from './features/auth/components/login/login';
import { Register } from './features/auth/components/register/register';

export const routes: Routes = [

    //Area de autenticación
    {
        path: 'auth',
        component: AuthLayout,
        children: [
            { path: 'login', component: Login },
            { path: 'register', component: Register }
        ]
    },

    //Area de administración
    {
        path: 'admin',
        component: AuthLayout,
        children: [
            {
                path: 'user-access',
                loadComponent: () => import
                    ('./features/private/pages/user-access-management/user-access-management')
                    .then(m => m.UserAccessManagementComponent)
            }
        ]
    },

    //Area publica
    {
        path: '',
        component: PublicLayout,
        children: [
            { path: '', component: PublicHome }
        ]
    },

    { path: '**', redirectTo: '' }  //Redirige cualquier ruta no definida a la pagina de inicio

];
