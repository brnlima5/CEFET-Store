import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-atualizar-produto',
  standalone: false,
  
  templateUrl: './atualizar-produto.component.html',
  styleUrl: './atualizar-produto.component.scss'
})
export class AtualizarProdutoComponent {

  produtoId: number;

produtoForm: FormGroup;
  listOfCategories: any = [];
  selectedFile: File | null;
  imagePreview: string | ArrayBuffer | null;

  existingImage: string | null = null;
  imgChanged = false;


  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
    private adminService: AdminService,
    private activatedroute: ActivatedRoute,
  ) { }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    this.previewImage();
    this.imgChanged = true;

    this.existingImage = null;
  }

  previewImage() {
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
    }
    reader.readAsDataURL(this.selectedFile);
  }

  ngOnInit(): void {
    this.produtoId = this.activatedroute.snapshot.params['produtoId'];
    this.produtoForm = this.fb.group({
      categoryId: [null, [Validators.required]], 
      name: [null, [Validators.required]],
      price: [null, [Validators.required]],
      description: [null, [Validators.required]], 
    });
    this.getAllCategories();
    this.getProductById();
  }

  getAllCategories() {
    this.adminService.getAllCategorias().subscribe(res => {
      this.listOfCategories = res;
    })
  }

  getProductById() {
    this.adminService.getProdutosById(this.produtoId).subscribe(res=>{
      this.produtoForm.patchValue(res);
      this.existingImage = 'data:image/jpeg;base64,' + res.byteImg;
    })
  }

  updateProduct(): void {
    if(this.produtoForm.valid) {
      const formData: FormData = new FormData();

      if(this.imgChanged && this.selectedFile) {
        formData.append('img', this.selectedFile);
      }

      formData.append('categoryId', this.produtoForm.get('categoryId').value);
      formData.append('name', this.produtoForm.get('name').value);
      formData.append('description', this.produtoForm.get('description').value);
      formData.append('price', this.produtoForm.get('price').value);

      this.adminService.updateProduto(this.produtoId,formData).subscribe((res) => {
        console.log(res)
        if(res.id != null) {
          this.snackBar.open('Produto atualizado com sucesso!', 'Fechar', {
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
