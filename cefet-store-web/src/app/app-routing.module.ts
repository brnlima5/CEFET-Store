import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegistrarComponent } from './registrar/registrar.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: 'registrar', component: RegistrarComponent},
  { path: 'cliente', loadChildren: () => import('./cliente/cliente.module').then(m => m.ClienteModule) }, 
  { path: 'admin', loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule) }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
