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


@NgModule({
  declarations: [
    AdminComponent,
    PainelComponent,
    PostarCategoriaComponent,
    PostarProdutoComponent
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
