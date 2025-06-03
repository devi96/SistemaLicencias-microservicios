import { Routes, RouterModule } from "@angular/router";
import { ListaVehiculosComponent } from "./lista-vehiculos/lista-vehiculos.component";
import { DetalleVehiculoComponent } from "./detalle-vehiculo/detalle-vehiculo.component";
import { NgModule } from "@angular/core";
import { FormVehiculoComponent } from "./form-vehiculo/form-vehiculo.component";

const routes: Routes = [
  { path: 'listar', component: ListaVehiculosComponent },
  { path: 'detalle', component: DetalleVehiculoComponent },
  { path: 'crear', component: FormVehiculoComponent}
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class VehiculoRoutingModule {}
