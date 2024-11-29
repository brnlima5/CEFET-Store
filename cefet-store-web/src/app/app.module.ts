import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { AngularMaterialModulos } from './AngularMaterialModulos';
import { LoginComponent } from './login/login.component';
import { RegistrarComponent } from './registrar/registrar.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AngularMaterialModulos
  ],
  providers: [
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
