import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LicenciaService {
  private apiUrl = 'http://localhost:8080/api/licencias'; // URL del microservicio de licencias
  constructor(private http: HttpClient, private router: Router) { }
  getLicencias() {
    return this.http.get(`${this.apiUrl}`);
  }
  getLicenciaById(id: number) {
    return this.http.get(`${this.apiUrl}/${id}`);
  }
  createLicencia(licencia: any) {
    return this.http.post(`${this.apiUrl}`, licencia);
  }
  updateLicencia(id: number, licencia: any) {
    return this.http.put(`${this.apiUrl}/${id}`, licencia);
  }
  deleteLicencia(id: number) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
  getLicenciasByUsuarioId(usuarioId: number) {
    return this.http.get(`${this.apiUrl}/usuario/${usuarioId}`);
  }
}
