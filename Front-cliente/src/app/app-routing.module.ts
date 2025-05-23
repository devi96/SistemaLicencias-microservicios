import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';  // Asegúrate de importar el LoginComponent

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },  // Redirige al login por defecto
  { path: 'login', component: LoginComponent },  // Ruta para el login
  // Otras rutas como el dashboard u otras secciones
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
