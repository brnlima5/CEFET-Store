import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClienteComponent } from './cliente.component';
import { PainelComponent } from './components/painel/painel.component';

const routes: Routes = [
  { path: '', component: ClienteComponent },
  { path: 'painel', component: PainelComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClienteRoutingModule { }
