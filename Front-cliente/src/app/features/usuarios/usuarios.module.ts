import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListaUsuariosComponent } from './lista-usuarios/lista-usuarios.component';
import { PerfilUsuarioComponent } from './perfil-usuario/perfil-usuario.component';
import { UsuarioComponent } from './usuario/usuario.component';



@NgModule({
  declarations: [
    ListaUsuariosComponent,
    PerfilUsuarioComponent,
    UsuarioComponent
  ],
  imports: [
    CommonModule
  ]
})
export class UsuariosModule { }
