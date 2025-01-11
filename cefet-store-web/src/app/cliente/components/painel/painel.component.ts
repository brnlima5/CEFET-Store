import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AdminService } from '../../../admin/service/admin.service';
import { ClienteService } from '../../services/cliente.service';

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
      private clienteService: ClienteService,
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
      this.clienteService.getAllProdutos().subscribe(res => {
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
      this.clienteService.getAllProdutosByName(title).subscribe(res => {
        res.forEach(element => {
          element.processedImg = 'data:image/jpeg;base64,' + element.byteImg;
          this.products.push(element);
        });
        console.log(this.products);
      })
    }

    addToCart(id:any) {
      this.clienteService.addToCart(id).subscribe(res => {
        this.snackBar.open("Produto adicionado ao carrinho com sucesso!", "Fechar", { duration: 5000})
      })
    }
}
