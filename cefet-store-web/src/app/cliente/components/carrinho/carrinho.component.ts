import { Component } from '@angular/core';
import { ClienteService } from '../../services/cliente.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-carrinho',
  standalone: false,
  
  templateUrl: './carrinho.component.html',
  styleUrl: './carrinho.component.scss'
})
export class CarrinhoComponent {

  cartItems: any[] = [];
  order: any;

  constructor(private clienteService: ClienteService,
    private snackbar: MatSnackBar,
    private fb: FormBuilder,
    private dialog: MatDialog,){}

    ngOnInit(): void {
      this.getCart();
    }

    getCart() {
      this.cartItems = [];
      this.clienteService.getCartByUserId().subscribe(res =>{
        this.order = res;
        res.cartItems.forEach(element => {
          element.processedImg = 'data:image/jpeg;base64,' + element.returnedImg;
          this.cartItems.push(element);
        });
      })
    }
}
