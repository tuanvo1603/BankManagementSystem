export interface transaction{
    transactionId: string,
    sourceAccountId: string,
    destinationAccountId: string,
    amount: number,
    transactionType: string,
    transactionDate: string
}

export interface transactionPage{
    transaction: transaction[],
    pageNumber: number,
    pageSize: number,
    totalPages: number,
}