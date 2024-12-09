import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AdminService } from '../../service/admin.service';
import { Router } from '@angular/router';
import { validateHorizontalPosition } from '@angular/cdk/overlay';

@Component({
  selector: 'app-postar-produto',
  standalone: false,
  
  templateUrl: './postar-produto.component.html',
  styleUrl: './postar-produto.component.scss'
})
export class PostarProdutoComponent {

  produtoForm: FormGroup;
  listOfCategories: any = [];
  selectedFile: File | null;
  imagePreview: string | ArrayBuffer | null;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
    private adminService: AdminService
  ) { }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    this.previewImage();
  }

  previewImage() {
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
    }
    reader.readAsDataURL(this.selectedFile);
  }

  ngOnInit(): void {
    this.produtoForm = this.fb.group({
      categoryId: [null, [Validators.required]], 
      name: [null, [Validators.required]],
      price: [null, [Validators.required]],
      description: [null, [Validators.required]], 
    });
    this.getAllCategories();
  }

  getAllCategories() {
    this.adminService.getAllCategorias().subscribe(res => {
      this.listOfCategories = res;
    })
  }

  addProduct(): void {
    if(this.produtoForm.valid) {
      const formData: FormData = new FormData();
      formData.append('img', this.selectedFile);
      formData.append('categoryId', this.produtoForm.get('categoryId').value);
      formData.append('name', this.produtoForm.get('name').value);
      formData.append('description', this.produtoForm.get('description').value);
      formData.append('price', this.produtoForm.get('price').value);

      this.adminService.addProduto(formData).subscribe((res) => {
        if(res.id != null) {
          this.snackBar.open('Produto cadastrado com sucesso!', 'Fechar', {
            duration: 5000
          });
          this.router.navigateByUrl('admin/painel');
        } else {
          this.snackBar.open(res.message, 'Erro', {
            duration: 5000
          });
        }
      })

    } else {
      for (const i in this.produtoForm.controls) {
        this.produtoForm.controls[i].markAsDirty();
        this.produtoForm.controls[i].updateValueAndValidity();
      }
    }
  }


}
