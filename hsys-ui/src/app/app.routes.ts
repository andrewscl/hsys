import { Routes } from '@angular/router';
import { PublicLayout } from './layouts/public-layout/public-layout';
import { AuthLayout } from './layouts/auth/auth-layout/auth-layout';

import { PublicHome } from './pages/public/public-home/public-home';
import { Login } from './components/auth/login/login';

export const routes: Routes = [

    //Area de autenticación
    {
        path: 'auth',
        component: AuthLayout,
        children: [
            { path: 'login', component: Login }
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
