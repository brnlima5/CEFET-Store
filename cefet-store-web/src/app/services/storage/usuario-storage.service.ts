import { Injectable } from '@angular/core';

const TOKEN = 'cefet-token';
const USER = 'cefet-user';

@Injectable({
  providedIn: 'root'
})
export class UsuarioStorageService {

  constructor() { }

  //--- SALVA TOKEN & DADOS DO USER

  public saveToken(token: string): void {
    //Remove valor antigo associado à chave
    window.localStorage.removeItem(TOKEN);
    //Add o novo token
    window.localStorage.setItem(TOKEN, token);
  }

  public saveUser(user): void {
    window.localStorage.removeItem(USER);
    //------
    window.localStorage.setItem(USER, JSON.stringify(user));
  }

  static getToken(): string {
    return localStorage.getItem(TOKEN);
  }

  static getUser(): any {
    return JSON.parse(localStorage.getItem(USER));
  }

  static getUserId(): string {
    const user = this.getUser();
    if(user == null) {
      return '';
    }
    return user.userId;
  }

  static getUserRole(): string {
    const user = this.getUser();
    if(user == null) {
      return '';
    }
    return user.role;
  }

  static isAdminLoggedIn(): boolean {
    console.log(localStorage.getItem('cefet-token'));
    console.log(localStorage.getItem('cefet-user'));

    if(this.getToken === null) {
      return false;
    }
    const role: string = this.getUserRole();
    return role == 'ADMIN';
  }

  static isClienteLoggedIn(): boolean {
    console.log(localStorage.getItem('cefet-token'));
  console.log(localStorage.getItem('cefet-user'));

    if(this.getToken === null) {
      return false;
    }
    const role: string = this.getUserRole();
    return role == 'CLIENTE';
  }

  static deslogar(): void {
    window.localStorage.removeItem(TOKEN);
    window.localStorage.removeItem(USER);
  }

}
