import { Component } from '@angular/core';
import { AdminService } from '../../service/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-pedidos',
  standalone: false,
  
  templateUrl: './pedidos.component.html',
  styleUrl: './pedidos.component.scss'
})
export class PedidosComponent {


  pedidos: any;

  constructor(private adminService: AdminService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit() {
    this.getPedidosFeitos();
  }

  getPedidosFeitos() {
    this.adminService.getPedidos().subscribe(res =>{
      this.pedidos = res;
    })
  }

  mudarStatusPedido(pedidoId: number, status: string) {
    this.adminService.mudarStatusPedido(pedidoId,status).subscribe(res =>{
      if(res.id != null) {
        this.snackBar.open("O status do pedido foi alterado com sucesso!", "Fechar", { duration : 5000 });
        this.getPedidosFeitos();
      } else {
        this.snackBar.open("Deu ruim.", "Fechar", { duration: 5000 });
      }
    })
  }

}
