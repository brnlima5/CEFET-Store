import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';
import { PainelComponent } from './components/painel/painel.component';
import { PostarCategoriaComponent } from './components/postar-categoria/postar-categoria.component';
import { PostarProdutoComponent } from './components/postar-produto/postar-produto.component';
import { PostarCupomComponent } from './components/postar-cupom/postar-cupom.component';
import { CuponsComponent } from './components/cupons/cupons.component';
import { PedidosComponent } from './components/pedidos/pedidos.component';
import { PostarFaqComponent } from './components/postar-faq/postar-faq.component';
import { AtualizarProdutoComponent } from './components/atualizar-produto/atualizar-produto.component';

const routes: Routes = [
  { path: '', component: AdminComponent },
  { path: 'painel', component: PainelComponent },
  { path: 'categoria', component: PostarCategoriaComponent },
  { path: 'produto', component: PostarProdutoComponent },
  { path: 'produto/:produtoId', component: AtualizarProdutoComponent },
  { path: 'postar-cupom', component: PostarCupomComponent },
  { path: 'cupons', component: CuponsComponent },
  { path: 'pedidos', component: PedidosComponent },
  { path: 'faq/:produtoId', component: PostarFaqComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
