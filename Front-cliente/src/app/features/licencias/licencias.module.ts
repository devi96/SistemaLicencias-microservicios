import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DetalleLicenciaComponent } from './detalle-licencia/detalle-licencia.component';
import { ListaLicenciaComponent } from './lista-licencia/lista-licencia.component';
import { LicenciaComponent } from './licencia/licencia.component';



@NgModule({
  declarations: [
    DetalleLicenciaComponent,
    ListaLicenciaComponent,
    LicenciaComponent
  ],
  imports: [
    CommonModule
  ]
})
export class LicenciasModule { }
