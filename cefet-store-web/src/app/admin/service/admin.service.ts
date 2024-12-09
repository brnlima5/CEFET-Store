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

  addProduto(produtoDto: any): Observable<any> {
    return this.http.post(BASIC_URL + 'api/admin/produto', produtoDto, {
      headers: this.createAuthorizationHeader(),
    })
  }

  private createAuthorizationHeader(): HttpHeaders {
    return new HttpHeaders().set(
      'Authorization', 'Bearer ' + UsuarioStorageService.getToken()
    )
  }
}
