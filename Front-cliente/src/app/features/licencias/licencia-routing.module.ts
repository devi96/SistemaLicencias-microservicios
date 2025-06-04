import { Routes, RouterModule } from "@angular/router";

import { NgModule } from "@angular/core";
import { LicenciaComponent } from "./licencia/licencia.component";
import { ListaLicenciaComponent } from "./lista-licencia/lista-licencia.component";
import { DetalleLicenciaComponent } from "./detalle-licencia/detalle-licencia.component";
import { FormLicenciaComponent } from "./form-licencia/form-licencia.component";

const routes: Routes =[{
  path: '',
  component: LicenciaComponent,
  children: [
    { path: 'listar', component: ListaLicenciaComponent },
    { path: 'detalle', component: DetalleLicenciaComponent },
    { path: 'crear', component: FormLicenciaComponent }, // Asumiendo que el formulario de creación es el mismo componente
    { path: '', redirectTo: 'listar', pathMatch: 'full' } // ruta por defecto
  ]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class LicenciaRoutingModule {}
