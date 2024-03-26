import { Component, OnInit } from '@angular/core';
import { TransactionService } from '../../services/transaction.service';

@Component({
  selector: 'app-manage-transaction',
  standalone: true,
  imports: [],
  templateUrl: './manage-transaction.component.html',
  styleUrl: './manage-transaction.component.css'
})
export class ManageTransactionComponent implements OnInit{

  constructor(private transactionService: TransactionService){}

  ngOnInit(): void {
    this.transactionService.getAllTransaction().subscribe(data => {
      console.log(data);
    },() => {console.log("error")})
  }

}
