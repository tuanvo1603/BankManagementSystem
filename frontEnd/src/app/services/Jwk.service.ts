import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
  })
  export class JwkService {
  
    constructor(private http: HttpClient) { }
  
    getJwkSet() {
      return this.http.get<any>('http://localhost:8080/jwkEndpoint');
    }
  }