import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClienteComponent } from './cliente.component';
import { PainelComponent } from './components/painel/painel.component';
import { CarrinhoComponent } from './components/carrinho/carrinho.component';
import { MeusPedidosComponent } from './components/meus-pedidos/meus-pedidos.component';

const routes: Routes = [
  { path: '', component: ClienteComponent },
  { path: 'painel', component: PainelComponent },
  { path: 'carrinho', component: CarrinhoComponent },
  { path: 'meus-pedidos', component: MeusPedidosComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClienteRoutingModule { }
