import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginRequest } from 'src/app/core/model/login-request.model';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  onSubmit(): void {
    const data: LoginRequest = this.loginForm.value;
    console.log(this.loginForm.value);
    this.authService.login(data).subscribe({
      next: (response) => {
        // guardar token, redirigir
        console.log('Login exitoso', response);
        console.log('Token recibido:', response);
        this.router.navigate(['/dashboard']); // redirigir al dashboard
      },
      error: (err) => {
        console.error('Error:', err);
        alert('Credenciales incorrectas');
      },
    });
  }

}
