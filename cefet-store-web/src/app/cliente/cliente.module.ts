import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClienteRoutingModule } from './cliente-routing.module';
import { ClienteComponent } from './cliente.component';
import { PainelComponent } from './components/painel/painel.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AngularMaterialModulos } from '../AngularMaterialModulos';
import { CarrinhoComponent } from './components/carrinho/carrinho.component';
import { FazerPedidoComponent } from './components/fazer-pedido/fazer-pedido.component';
import { MeusPedidosComponent } from './components/meus-pedidos/meus-pedidos.component';
import { VerProdutosPedidosComponent } from './components/ver-produtos-pedidos/ver-produtos-pedidos.component';
import { AvaliacaoProdutoPedidoComponent } from './components/avaliacao-produto-pedido/avaliacao-produto-pedido.component';


@NgModule({
  declarations: [
    ClienteComponent,
    PainelComponent,
    CarrinhoComponent,
    FazerPedidoComponent,
    MeusPedidosComponent,
    VerProdutosPedidosComponent,
    AvaliacaoProdutoPedidoComponent
  ],
  imports: [
    CommonModule,
    ClienteRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AngularMaterialModulos
  ]
})
export class ClienteModule { }
