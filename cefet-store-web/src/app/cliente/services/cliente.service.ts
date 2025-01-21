import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UsuarioStorageService } from '../../services/storage/usuario-storage.service';

const BASIC_URL = "http://localhost:8080/";

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  constructor(private http: HttpClient) { }

    getAllProdutos(): Observable<any> {
      return this.http.get(BASIC_URL + 'api/cliente/produtos', {
        headers: this.createAuthorizationHeader(),
      })
    }
  
    getAllProdutosByName(name:any): Observable<any> {
      return this.http.get(BASIC_URL + `api/cliente/search/${name}`, {
        headers: this.createAuthorizationHeader(),
      })
    }

    addToCart(productId:any): Observable<any>{
      const cartDto = {
        productId : productId,
        userId: UsuarioStorageService.getUserId()
      }
      return this.http.post(BASIC_URL + `api/cliente/carrinho`, cartDto, {
        headers: this.createAuthorizationHeader(),
      })
    }

    getCartByUserId(): Observable<any>{
      const userId = UsuarioStorageService.getUserId()
      return this.http.get(BASIC_URL + `api/cliente/carrinho/${userId}`, {
        headers: this.createAuthorizationHeader(),
      })
    }

    applyCupom(code:any): Observable<any>{
      const userId = UsuarioStorageService.getUserId()
      return this.http.get(BASIC_URL + `api/cliente/cupom/${userId}/${code}`, {
        headers: this.createAuthorizationHeader(),
      })
    }


    private createAuthorizationHeader(): HttpHeaders {
        return new HttpHeaders().set(
          'Authorization', 'Bearer ' + UsuarioStorageService.getToken()
        )
      }
}
