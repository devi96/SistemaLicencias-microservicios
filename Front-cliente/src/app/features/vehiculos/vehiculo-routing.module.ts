import { Routes, RouterModule } from "@angular/router";
import { ListaVehiculosComponent } from "./lista-vehiculos/lista-vehiculos.component";
import { DetalleVehiculoComponent } from "./detalle-vehiculo/detalle-vehiculo.component";
import { NgModule } from "@angular/core";
import { FormVehiculoComponent } from "./form-vehiculo/form-vehiculo.component";
import { VehiculoComponent } from "./vehiculo/vehiculo.component";

const routes: Routes =[{
  path: '',
  component: VehiculoComponent,
  children: [
    { path: 'listar', component: ListaVehiculosComponent },
    { path: 'detalle/:id', component: FormVehiculoComponent },
    { path: 'crear', component: FormVehiculoComponent},
    { path: '', redirectTo: 'listar', pathMatch: 'full' } // ruta por defecto
  ]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class VehiculoRoutingModule {}
