import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-postar-categoria',
  standalone: false,
  
  templateUrl: './postar-categoria.component.html',
  styleUrl: './postar-categoria.component.scss'
})
export class PostarCategoriaComponent {

  categoriaForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
    private adminService: AdminService
  ) { }

  ngOnInit(): void {
    this.categoriaForm = this.fb.group({
      name: [null, [Validators.required]],
      description: [null, [Validators.required]],
    })
  }

  addCategoria(): void {
    if(this.categoriaForm.valid) {
      this.adminService.addCategoria(this.categoriaForm.value).subscribe((res) => {
        if(res.id != null) {
          this.snackBar.open('Categoria criada com sucesso!', 'Fechar', {
            duration: 5000
          });
          this.router.navigateByUrl('/admin/painel');
        } else {
          this.snackBar.open(res.message, 'Fechar', {
            duration: 5000,
            panelClass: 'error-snackbar'
          });
        }
      })
    } else {
      this.categoriaForm.markAllAsTouched();
    }
  }
  
}
