import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DetalleVehiculoComponent } from './detalle-vehiculo/detalle-vehiculo.component';
import { ListaVehiculosComponent } from './lista-vehiculos/lista-vehiculos.component';
import { VehiculoComponent } from './vehiculo/vehiculo.component';
import { FormVehiculoComponent } from './form-vehiculo/form-vehiculo.component';



@NgModule({
  declarations: [
    DetalleVehiculoComponent,
    ListaVehiculosComponent,
    VehiculoComponent,
    FormVehiculoComponent
  ],
  imports: [
    CommonModule
  ]
})
export class VehiculosModule { }
