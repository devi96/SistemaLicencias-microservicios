import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DetalleVehiculoComponent } from './detalle-vehiculo/detalle-vehiculo.component';
import { ListaVehiculosComponent } from './lista-vehiculos/lista-vehiculos.component';
import { VehiculoComponent } from './vehiculo/vehiculo.component';
import { FormVehiculoComponent } from './form-vehiculo/form-vehiculo.component';
import { VehiculoRoutingModule } from './vehiculo-routing.module';
import { ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    DetalleVehiculoComponent,
    ListaVehiculosComponent,
    FormVehiculoComponent,
    VehiculoComponent,
  ],
  imports: [
    CommonModule,
    VehiculoRoutingModule,
    ReactiveFormsModule
  ]
})
export class VehiculosModule { }
