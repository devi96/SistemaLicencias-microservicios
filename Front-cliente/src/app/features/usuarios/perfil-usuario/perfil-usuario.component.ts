import { Component, OnInit } from '@angular/core';
import { UsuarioResponse } from 'src/app/core/model/usuario-response.model';
import { UsuarioService } from 'src/app/core/services/usuario.service';

@Component({
  selector: 'app-perfil-usuario',
  templateUrl: './perfil-usuario.component.html',
  styleUrls: ['./perfil-usuario.component.css']
})
export class PerfilUsuarioComponent implements OnInit{
  usuario: UsuarioResponse | null = null;

  constructor(
    private usuarioService: UsuarioService
  ) {}

  ngOnInit(): void {
    this.usuarioService.getUsuarioByAuthId().subscribe({
      next: (data) => {
        this.usuario = data;
      },
      error: (err) => {
        console.error('Error al obtener el perfil:', err);
      }
    });
  }
}
