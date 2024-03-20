import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root',
})
export class TransactionService {

    baseUrl : string = "http://localhost:8091/v1/customer";

    constructor(private _http: HttpClient) {}

    public getUserTranSaction(pageNumber : any): Observable<any>{
      return this._http.get( this.baseUrl+ "/transaction/" + pageNumber);
    }

    public exchangeMoney(transaction: any): Observable<any>{
      return this._http.post( this.baseUrl+"/exchange/"+
      transaction.sourceAccountId+
      "/"+transaction.destinationAccountId+"/"+
      transaction.moneyToExchange,"");
    }
}