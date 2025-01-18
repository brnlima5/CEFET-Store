import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';
import { PainelComponent } from './components/painel/painel.component';
import { PostarCategoriaComponent } from './components/postar-categoria/postar-categoria.component';
import { PostarProdutoComponent } from './components/postar-produto/postar-produto.component';
import { PostarCupomComponent } from './components/postar-cupom/postar-cupom.component';
import { CuponsComponent } from './components/cupons/cupons.component';

const routes: Routes = [
  { path: '', component: AdminComponent },
  { path: 'painel', component: PainelComponent },
  { path: 'categoria', component: PostarCategoriaComponent },
  { path: 'produto', component: PostarProdutoComponent },
  { path: 'postar-cupom', component: PostarCupomComponent },
  { path: 'cupons', component: CuponsComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
