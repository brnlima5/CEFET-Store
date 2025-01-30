import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UsuarioStorageService } from '../../services/storage/usuario-storage.service';

const BASIC_URL = "http://localhost:8080/";

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  addCategoria(categoriaDto: any): Observable<any> {
    return this.http.post(BASIC_URL + 'api/admin/categoria', categoriaDto, {
      headers: this.createAuthorizationHeader(),
    })
  }

  getAllCategorias(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/admin', {
      headers: this.createAuthorizationHeader(),
    })
  }

  updateProduto(produtoId:any,produtoDto: any): Observable<any> {
    return this.http.put(BASIC_URL + `api/admin/produto/${produtoId}`, produtoDto, {
      headers: this.createAuthorizationHeader(),
    })
  }

  addProduto(produtoDto: any): Observable<any> {
    return this.http.post(BASIC_URL + 'api/admin/produto', produtoDto, {
      headers: this.createAuthorizationHeader(),
    })
  }

  getAllProdutos(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/admin/produtos', {
      headers: this.createAuthorizationHeader(),
    })
  }

  getProdutosById(produtoId): Observable<any> {
    return this.http.get(BASIC_URL + `api/admin/produto/${produtoId}`, {
      headers: this.createAuthorizationHeader(),
    })
  }

  getAllProdutosByName(name:any): Observable<any> {
    return this.http.get(BASIC_URL + `api/admin/search/${name}`, {
      headers: this.createAuthorizationHeader(),
    })
  }

  deleteProduto(productId:any): Observable<any> {
    return this.http.delete(BASIC_URL + `api/admin/produto/${productId}`, {
      headers: this.createAuthorizationHeader(),
    })
  }

  addCupom(cupomDto:any): Observable<any> {
    return this.http.post(BASIC_URL + 'api/admin/cupons', cupomDto, {
      headers: this.createAuthorizationHeader(),
    })
  }

  getCupons(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/admin/cupons', {
      headers: this.createAuthorizationHeader(),
    })
  }

  getPedidos(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/admin/pedidosFeitos', {
      headers: this.createAuthorizationHeader(),
    })
  }

  mudarStatusPedido(pedidoId: number, status: string): Observable<any> {
    return this.http.get(BASIC_URL + `api/admin/pedido/${pedidoId}/${status}`, {
      headers: this.createAuthorizationHeader(),
    })
  }

  postFAQ(produtoId: number, faqDto: any): Observable<any> {
    return this.http.post(BASIC_URL + `api/admin/faq/${produtoId}`, faqDto, {
      headers: this.createAuthorizationHeader(),
    })
  }

  private createAuthorizationHeader(): HttpHeaders {
    return new HttpHeaders().set(
      'Authorization', 'Bearer ' + UsuarioStorageService.getToken()
    )
  }
}
