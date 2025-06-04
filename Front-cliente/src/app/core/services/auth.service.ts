import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';
import { RegisterRequest } from '../model/register-request.model';
import { LoginRequest } from '../model/login-request.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth'; // URL del microservicio auth

  constructor(private http: HttpClient, private router: Router) {}

  login(data: LoginRequest): Observable<any> {
    return this.http.post(`${this.apiUrl}/authenticate`, data).pipe(
      tap((response: any) => {
        localStorage.setItem('token', response.token); // Guarda token
      })
    );
  }

  register(data: RegisterRequest): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, data);
  }
  logout(): void {
    localStorage.removeItem('token'); // Elimina token
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isAuthenticated(): boolean {
    return !!this.getToken(); // Retorna true si hay token
  }

  getPayload(): any | null {
    const token = this.getToken();
    if (!token) return null;

    const payload = token.split('.')[1];
    try {
      return JSON.parse(atob(payload));
    } catch (e) {
      console.error('Error decodificando el token', e);
      return null;
    }
  }
  getUsuarioAuthId(): number | null {
    const payload = this.getPayload();
    return payload?.id ?? null; // Cambia a la propiedad correcta si es otro nombre
  }
}
