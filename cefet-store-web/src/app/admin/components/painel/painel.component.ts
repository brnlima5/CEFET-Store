import { Component } from '@angular/core';
import { AdminService } from '../../service/admin.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-painel',
  standalone: false,
  
  templateUrl: './painel.component.html',
  styleUrl: './painel.component.scss'
})
export class PainelComponent {

  products: any[] = [];
  searchProductForm!: FormGroup;

  constructor(
    private adminService: AdminService,
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
  ) {}

  ngOnInit(): void {
    this.getAllProducts(); 
    this.searchProductForm = this.fb.group({
      title: [null, [Validators.required]]
    })
  }

  getAllProducts() {
    this.products = [];
    this.adminService.getAllProdutos().subscribe(res => {
      res.forEach(element => {
        element.processedImg = 'data:image/jpeg;base64,' + element.byteImg;
        this.products.push(element);
      });
      console.log(this.products);
    })
  }

  submitForm() {
    this.products = [];
    const title = this.searchProductForm.get('title')!.value;
    this.adminService.getAllProdutosByName(title).subscribe(res => {
      res.forEach(element => {
        element.processedImg = 'data:image/jpeg;base64,' + element.byteImg;
        this.products.push(element);
      });
      console.log(this.products);
    })
  }

  deleteProduct(productId:any) {
    this.adminService.deleteProduto(productId).subscribe(res=>{
      if(res == null) {
        this.snackBar.open('Produto deletado com sucesso!', 'Fechar', {
          duration: 5000
        });
        this.getAllProducts();
      } else {
        this.snackBar.open(res.message, 'Fechar', {
          duration: 5000,
          panelClass: 'error-snackbar'
        });
      }
    })
  }
}
