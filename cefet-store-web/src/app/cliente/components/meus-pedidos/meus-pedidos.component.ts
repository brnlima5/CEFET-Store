import { Component } from '@angular/core';
import { ClienteService } from '../../services/cliente.service';

@Component({
  selector: 'app-meus-pedidos',
  standalone: false,
  
  templateUrl: './meus-pedidos.component.html',
  styleUrl: './meus-pedidos.component.scss'
})
export class MeusPedidosComponent {
  
  meusPedidos:any;

  constructor(private clienteService: ClienteService) {  }

  ngOnInit() {
    this.getMeusPedidos();
  }

  getMeusPedidos() {
    this.clienteService.getPedidosByUserId().subscribe(res =>{
      this.meusPedidos = res;
    });
  }
}
