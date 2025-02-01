import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ClienteService } from '../../services/cliente.service';

@Component({
  selector: 'app-ver-produtos-pedidos',
  standalone: false,
  
  templateUrl: './ver-produtos-pedidos.component.html',
  styleUrl: './ver-produtos-pedidos.component.scss'
})  
export class VerProdutosPedidosComponent {

pedidoId: number;
produtosPedidosDetalhesList = [];
totalAmount:any;

constructor(private activatedRoute: ActivatedRoute,
  private clienteService: ClienteService,
) {  }

ngOnInit() {
  this.pedidoId = this.activatedRoute.snapshot.params["pedidoId"];
  this.getProdutosPedidosById();
}

getProdutosPedidosById() {
  this.clienteService.getProdutosPedidos(this.pedidoId).subscribe(res=>{
    res.produtoDtoList.forEach(element => {
      element.processedImg = 'data:image/jpeg;base64,' + element.byteImg;
      this.produtosPedidosDetalhesList.push(element);
    });
    this.totalAmount = res.valorPedido;
  })
}


}
