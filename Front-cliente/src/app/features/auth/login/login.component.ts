import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
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
    const { username, password } = this.loginForm.value;
    console.log(this.loginForm.value);
    console.log("username:", username);
    console.log("password:", password);
    this.authService.login(username, password).subscribe({
      next: (response) => {
        // guardar token, redirigir
        console.log('Login exitoso', response);
        console.log('Token recibido:', response);
        //this.router.navigate(['/dashboard']);
      },
      error: (err) => {
        console.error('Error:', err);
        alert('Credenciales incorrectas');
      },
    });
  }

}
