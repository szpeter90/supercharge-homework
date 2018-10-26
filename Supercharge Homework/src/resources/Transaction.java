package resources;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {

	private final LocalDate transactionDate;
	private final BigDecimal transactionAmount;
	private final BigDecimal currentBalance;
	private final TransactionType transType;
	
	public Transaction(LocalDate transactionDate, BigDecimal transactionAmount, BigDecimal currentBalance, TransactionType transType){
		this.transactionDate = transactionDate;
		this.transactionAmount = transactionAmount;
		this.currentBalance = currentBalance;
		this.transType = transType;
	}
	
	public TransactionType getTransactionType(){
		return this.transType;
	}
	
	public LocalDate getTransactionDate(){
		return this.transactionDate;
	}
	
	@Override
	public String toString(){
		return ""
				+ "Date/time of the transaction: " + this.transactionDate + "\n"
				+ "Amount of the transaction: " + this.transactionAmount + "\n"
				+ "Current balance: " + this.currentBalance;
	}
}
