import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-postar-cupom',
  standalone: false,
  
  templateUrl: './postar-cupom.component.html',
  styleUrl: './postar-cupom.component.scss'
})
export class PostarCupomComponent {

  cupomForm!: FormGroup;

  constructor( private fb: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
    private adminService: AdminService){}

  ngOnInit() {
    this.cupomForm = this.fb.group({
      name: [null, [Validators.required]],
      code: [null, [Validators.required]],
      discount: [null, [Validators.required]],
    expirationDate: [null, [Validators.required]],
    })
  }

  addCupom() {
    console.log(this.cupomForm.value);
    if(this.cupomForm.valid) {
      this.adminService.addCupom(this.cupomForm.value).subscribe(res =>{
        if(res.id != null) {
          this.snackBar.open('Cupom cadastrado com sucesso!', 'Fechar', {
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
      this.cupomForm.markAllAsTouched();
    }
  }

}
