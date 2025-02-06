import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClienteComponent } from './cliente.component';
import { PainelComponent } from './components/painel/painel.component';
import { CarrinhoComponent } from './components/carrinho/carrinho.component';
import { MeusPedidosComponent } from './components/meus-pedidos/meus-pedidos.component';
import { VerProdutosPedidosComponent } from './components/ver-produtos-pedidos/ver-produtos-pedidos.component';
import { AvaliacaoProdutoPedidoComponent } from './components/avaliacao-produto-pedido/avaliacao-produto-pedido.component';
import { DetalheProdutoComponent } from './components/detalhe-produto/detalhe-produto.component';
import { VerListaDesejosComponent } from './components/ver-lista-desejos/ver-lista-desejos.component';

const routes: Routes = [
  { path: '', component: ClienteComponent },
  { path: 'painel', component: PainelComponent },
  { path: 'carrinho', component: CarrinhoComponent },
  { path: 'meus-pedidos', component: MeusPedidosComponent },
  { path: 'produtos-pedidos/:pedidoId', component: VerProdutosPedidosComponent },
  { path: 'avaliacao/:produtoId', component: AvaliacaoProdutoPedidoComponent },
  { path: 'produto/:produtoId', component: DetalheProdutoComponent },
  { path: 'lista-desejos', component: VerListaDesejosComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClienteRoutingModule { }
