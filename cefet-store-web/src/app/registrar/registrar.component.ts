import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth/auth.service';

@Component({
  selector: 'app-registrar',
  standalone: false,
  templateUrl: './registrar.component.html',
  styleUrl: './registrar.component.scss'
})
export class RegistrarComponent {

  registrarForm!: FormGroup;
  hidePassword = true;

  constructor( private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private authService: AuthService,
    private router: Router){

  }

  ngOnInit(): void {
    this.registrarForm = this.fb.group({
      name: [null, [Validators.required]],
      email: [null, [Validators.required, Validators.email]],
      password: [null, [Validators.required]],
      confirmPassword: [null, [Validators.required]],
    })
  }


  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword;
  }

  onSubmit(): void {
    const password = this.registrarForm.get('password')?.value;
    const confirmPassword = this.registrarForm.get('confirmPassword')?.value;

    if(password != confirmPassword) {
      this.snackBar.open('As senhas não correspondem.', 'Fechar', { duration: 5000, panelClass: 'error-snackbar' });
      return;
    }

    this.authService.registro(this.registrarForm.value).subscribe(
      (response) => {
        this.snackBar.open('Registro realizado com sucesso!', 'Fechar', {duration: 5000});
        this.router.navigateByUrl("/login");
      },
      (error)=> {
        this.snackBar.open('Falha ao se registrar, tente novamente.', 'Fechar', { duration: 5000, panelClass: 'error-snackbar' });
      }
    )
  }
}
