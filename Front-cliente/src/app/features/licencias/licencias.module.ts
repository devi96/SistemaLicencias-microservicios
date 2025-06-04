import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DetalleLicenciaComponent } from './detalle-licencia/detalle-licencia.component';
import { ListaLicenciaComponent } from './lista-licencia/lista-licencia.component';
import { LicenciaComponent } from './licencia/licencia.component';
import { FormLicenciaComponent } from './form-licencia/form-licencia.component';
import { LicenciaRoutingModule } from './licencia-routing.module';



@NgModule({
  declarations: [
    DetalleLicenciaComponent,
    ListaLicenciaComponent,
    LicenciaComponent,
    FormLicenciaComponent
  ],
  imports: [
    CommonModule,
    LicenciaRoutingModule
  ]
})
export class LicenciasModule { }
