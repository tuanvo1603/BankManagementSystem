import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  user!: User;

  constructor(private httpClient: HttpClient) { }

  public getUsername(): Observable<any> {
    return this.httpClient.get<any>('/current/user');
  }

  public getUser(): Observable<User> {
    return this.httpClient.get<any>('/users/info');
  }

}
