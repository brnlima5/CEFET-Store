import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClienteRoutingModule } from './cliente-routing.module';
import { ClienteComponent } from './cliente.component';
import { PainelComponent } from './components/painel/painel.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AngularMaterialModulos } from '../AngularMaterialModulos';
import { CarrinhoComponent } from './components/carrinho/carrinho.component';


@NgModule({
  declarations: [
    ClienteComponent,
    PainelComponent,
    CarrinhoComponent
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
