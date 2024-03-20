import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { error } from 'console';
import { BankAccount } from 'src/app/model/account.model';
import { BankAccountService } from 'src/app/services/bank-account-service';
import { TransactionService } from 'src/app/services/transaction.service';

@Component({
  selector: 'app-transfer-money',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit{

  accounts: BankAccount[] = []

  currentAccount: BankAccount = {
    balance: 99999999,
    accountId: 0,
    accountType: '',
    createAt: '',
    accountNumber: ''
  };

  transaction={
    sourceAccountId: "",
    destinationName :"",
    moneyToExchange :'',
    destinationAccountId : ''
  }


  constructor(private bankAccountService: BankAccountService, private formbuilder: FormBuilder
    ,private transactionService: TransactionService){}

  transactionForm = this.formbuilder.group({
    sourceAccount: [Validators.required],
    destinationAccount: [Validators.required],
    money: [Validators.required]
  })


  ngOnInit(): void {
    this.bankAccountService.getAllUserAccount().subscribe((reponse) => {
      this.accounts = reponse.accounts;
      if(this.accounts.length != 0){
        this.currentAccount = this.accounts[0];
      }
    },(error) => {alert('loi roi kia')},() =>
    {this.transaction.sourceAccountId = this.currentAccount.accountId.toString();}

    )
  }

  changeCurrentAccount(selectElement: EventTarget | null): void {
    if (selectElement instanceof HTMLSelectElement) {
        const accountNumber = selectElement.value;
        const selectedAccount = this.accounts.find(account => account.accountNumber === accountNumber);
        if (selectedAccount) {
            this.currentAccount = selectedAccount;
            this.transaction.sourceAccountId = this.currentAccount.accountId.toString();
        }
        // this.currentAccount = this.accounts.find(account => account.accountNumber === accountNumber);
    }
}

getDestinationName(event: any) {
  const destinationAccount: string = event.target.value;

  if(destinationAccount == this.currentAccount.accountNumber){
    alert("invalid account number");
    return;
  }

  this.bankAccountService.getDestinationAccount(destinationAccount).subscribe((response) => {
    this.transaction.destinationName = response.userResponseDTO.fullName;
    this.transaction.destinationName = response.userResponseDTO.fullName
    this.transaction.destinationAccountId = response.userResponseDTO.accountId;},(error)=>{},()=>{})}




  exchangeMoney() {
    if(Number(this.exchangeMoney ) >= this.currentAccount.balance || this.transaction.destinationAccountId === ''
      || this.transaction.sourceAccountId === ''){
      alert('please sign valid information');
      return;
    }
    this.transactionService.exchangeMoney(this.transaction).subscribe((response) => {
      console.log(this.transaction);
    })
  }
}
