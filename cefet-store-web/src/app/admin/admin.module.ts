import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { PainelComponent } from './components/painel/painel.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AngularMaterialModulos } from '../AngularMaterialModulos';
import { PostarCategoriaComponent } from './components/postar-categoria/postar-categoria.component';
import { PostarProdutoComponent } from './components/postar-produto/postar-produto.component';
import { PostarCupomComponent } from './components/postar-cupom/postar-cupom.component';
import { CuponsComponent } from './components/cupons/cupons.component';
import { PedidosComponent } from './components/pedidos/pedidos.component';
import { PostarFaqComponent } from './components/postar-faq/postar-faq.component';
import { AtualizarProdutoComponent } from './components/atualizar-produto/atualizar-produto.component';
import { AnaliseComponent } from './components/analise/analise.component';
import { PedidoPorStatusComponent } from './components/analise/pedido-por-status/pedido-por-status.component';


@NgModule({
  declarations: [
    AdminComponent,
    PainelComponent,
    PostarCategoriaComponent,
    PostarProdutoComponent,
    PostarCupomComponent,
    CuponsComponent,
    PedidosComponent,
    PostarFaqComponent,
    AtualizarProdutoComponent,
    AnaliseComponent,
    PedidoPorStatusComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AngularMaterialModulos
  ]
})
export class AdminModule { }
