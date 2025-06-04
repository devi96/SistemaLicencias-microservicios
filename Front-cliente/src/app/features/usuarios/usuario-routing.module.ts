import { Routes, RouterModule } from "@angular/router";

import { NgModule } from "@angular/core";
import { UsuarioComponent } from "./usuario/usuario.component";
import { ListaUsuariosComponent } from "./lista-usuarios/lista-usuarios.component";
import { PerfilUsuarioComponent } from "./perfil-usuario/perfil-usuario.component";
import { FormUsuarioComponent } from "./form-usuario/form-usuario.component";

const routes: Routes =[{
  path: '',
  component: UsuarioComponent,
  children: [
    { path: 'listar', component: ListaUsuariosComponent },
    { path: 'perfil', component: PerfilUsuarioComponent },
    { path: 'crear', component: FormUsuarioComponent }, // Asumiendo que el formulario de creación es el mismo componente
    { path: '', redirectTo: 'perfil', pathMatch: 'full' } // ruta por defecto
  ]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UsuarioRoutingModule {}
