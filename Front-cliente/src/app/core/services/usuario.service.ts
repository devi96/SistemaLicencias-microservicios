import { AuthService } from 'src/app/core/services/auth.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { UsuarioResponse } from '../model/usuario-response.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  private apiUrl = 'http://localhost:8080/api/usuarios'; // URL del microservicio auth

  constructor(
    private http: HttpClient,
    private router: Router,
    private authService: AuthService
  ) { }

  getUsuarios() {
    return this.http.get(`${this.apiUrl}`);
  }

  getUsuarioById(id: number) {
    return this.http.get(`${this.apiUrl}/${id}`);
  }
  getUsuarioByAuthId(authId?: number): Observable<UsuarioResponse> {
    const id = authId ?? this.authService.getUsuarioAuthId();
    if (!id) throw new Error('Usuario no autenticado');
    return this.http.get<UsuarioResponse>(`${this.apiUrl}/auth/${id}`);
  }
  createUsuario(usuario: any) {
    return this.http.post(`${this.apiUrl}`, usuario);
  }
  updateUsuario(id: number, usuario: any) {
    return this.http.put(`${this.apiUrl}/${id}`, usuario);
  }
  deleteUsuario(id: number) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

}
