import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ClienteService } from '../../services/cliente.service';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-fazer-pedido',
  standalone: false,
  
  templateUrl: './fazer-pedido.component.html',
  styleUrl: './fazer-pedido.component.scss'
})
export class FazerPedidoComponent {

  pedidoForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private clienteService: ClienteService,
    private router: Router,
    public dialog: MatDialog
  ) {}

  ngOnInit() {
    this.pedidoForm = this.fb.group({
      address: [null, [Validators.required]],
      orderDescription: [null],
  })
}

  fazerPedido() {
    this.clienteService.fazerPedido(this.pedidoForm.value).subscribe(res =>{
      if(res.id != null){ 
        this.snackBar.open("Pedido realizado com sucesso!", "Fechar", {duration: 5000});
        this.router.navigateByUrl("/cliente/meus-pedidos")
        this.closeForm();
      } else {
        this.snackBar.open("Deu ruim", "Fechar", { duration: 5000})
      }
    })
  }

  closeForm() {
    this.dialog.closeAll();
  }

}
