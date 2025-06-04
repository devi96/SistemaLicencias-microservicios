import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard.component';
import { NgModule } from '@angular/core';
import { UsuariosModule } from '../usuarios/usuarios.module';
import { UsuarioComponent } from '../usuarios/usuario/usuario.component';

const routes: Routes = [
  {
    path: '',
    component: DashboardComponent, // el layout principal con navbar, sidebar
    children: [
      {
        path: 'licencias',
        loadChildren: () =>
          import('../licencias/licencias.module').then(m => m.LicenciasModule),
      },
      {
        path: 'vehiculos',
        loadChildren: () =>
          import('../vehiculos/vehiculos.module').then(m => m.VehiculosModule),
      },
      {
        path: 'usuarios',
        loadChildren: () => import('../usuarios/usuarios.module').then(m => m.UsuariosModule),
      },
      {
        path: '', redirectTo: 'usuarios', pathMatch: 'full'
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule {}
