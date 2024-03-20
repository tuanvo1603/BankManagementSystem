import { Component, OnInit } from "@angular/core";
import { transaction, transactionPage } from "src/app/model/transaction.model";
import { TransactionService } from "src/app/services/transaction.service";

@Component({
    selector: 'app-notification-user',
    templateUrl: './notification.component.html',
    styleUrls: ['./notification.component.css'],
  })
  export class NotificationUserComponent implements OnInit {

    transactionPage : transactionPage = {
      transaction: [],
      pageNumber: 0,
      pageSize: 0,
      totalPages: 0
    };

    currentPage : number = 0;
  
    constructor(private transactionService : TransactionService){}
  
    ngOnInit(): void {
      this.getTransactionByPage(this.currentPage);
    }

    goToNextPage() {
      if(this.currentPage !== this.transactionPage.totalPages){
        this.currentPage += 1;
        this.getTransactionByPage(this.currentPage);
      }
    }

    goToPreviosPage() {
      if(this.currentPage != 0){
        this.currentPage -= 1;
        this.getTransactionByPage(this.currentPage);
      }
    }

    getTransactionByPage(currentPageNumber: number){
      this.transactionService.getUserTranSaction(currentPageNumber).subscribe((data) => {
        this.transactionPage.transaction = data.transaction.content;
        this.transactionPage.pageNumber = data.transaction.pageable.pageNumber;
        this.transactionPage.pageSize = data.transaction.pageable.pageSize;
        this.transactionPage.totalPages = data.transaction.totalPages;
      })
    }
    
  
 
  }