import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})

export class BankAccountService{

    baseUrl : string = "http://localhost:8090";
    

    constructor(private _http: HttpClient) {}

    public getAllUserAccount(): Observable<any>{
      return this._http.get( this.baseUrl+ "/account/v1/get-all-user-account");
    }

    public getDestinationAccount(detinationAccount: string): Observable<any>{
        return this._http.get( this.baseUrl+ "/account/v1/get-username-account/" + detinationAccount);
      }
}