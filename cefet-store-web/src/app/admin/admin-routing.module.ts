import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';
import { PainelComponent } from './components/painel/painel.component';
import { PostarCategoriaComponent } from './components/postar-categoria/postar-categoria.component';
import { PostarProdutoComponent } from './components/postar-produto/postar-produto.component';

const routes: Routes = [
  { path: '', component: AdminComponent },
  { path: 'painel', component: PainelComponent },
  { path: 'categoria', component: PostarCategoriaComponent },
  { path: 'produto', component: PostarProdutoComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
