import { Component } from '@angular/core';
import { UsuarioStorageService } from './services/storage/usuario-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'cefet-store-web';

  isClienteLoggedIn: boolean = UsuarioStorageService.isClienteLoggedIn();
  isAdminLoggedIn: boolean = UsuarioStorageService.isAdminLoggedIn();

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.router.events.subscribe(event => {
      this.isClienteLoggedIn = UsuarioStorageService.isClienteLoggedIn();
      this.isAdminLoggedIn =UsuarioStorageService.isAdminLoggedIn();
    })
  }

  logout() {
    UsuarioStorageService.deslogar();
    this.router.navigateByUrl('login');
  }
}
