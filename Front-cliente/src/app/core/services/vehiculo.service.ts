import { AuthService } from './auth.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { VehiculoCreateRequest, VehiculoUpdateRequest } from '../model/vehiculo-request.model';
import { VehiculoResponse } from '../model/vehiculo-response.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VehiculoService {
    private apiUrl = 'http://localhost:8080/api/vehiculos'; // URL del microservicio auth

  constructor(
    private http: HttpClient,
    private route: Router,
    private authService: AuthService) { }

  getVehiculos() {
    return this.http.get(`${this.apiUrl}`);
  }
  getVehiculoById(id: number) {
    return this.http.get(`${this.apiUrl}/${id}`);
  }
  getVehiculosByUsuarioId(usuarioId?: number): Observable<VehiculoResponse[]>{
    const id = usuarioId ?? this.authService.getUsuarioAuthId();
    if (!id) throw new Error('Usuario no autenticado');
    return this.http.get<VehiculoResponse[]>(`${this.apiUrl}/usuario/${id}`);
  }
  createVehiculo(vehiculo: VehiculoCreateRequest) {
    return this.http.post(`${this.apiUrl}`, vehiculo);
  }
  updateVehiculo(id: number, vehiculo: VehiculoUpdateRequest) {
    return this.http.put(`${this.apiUrl}/${id}`, vehiculo);
  }
  deleteVehiculo(id: number) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

}
