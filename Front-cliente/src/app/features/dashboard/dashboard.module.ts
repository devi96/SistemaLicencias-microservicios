import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../shared/shared.module'; // Importing SharedModule
import { DashboardComponent } from './dashboard.component';
import { LicenciasModule } from '../licencias/licencias.module';
import { UsuariosModule } from '../usuarios/usuarios.module';
import { VehiculosModule } from '../vehiculos/vehiculos.module';


@NgModule({
  declarations: [
    DashboardComponent,
  ],
  imports: [
    CommonModule,
    SharedModule,
    LicenciasModule,
    UsuariosModule,
    VehiculosModule
  ]
})
export class DashboardModule { }
