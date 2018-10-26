package resources;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class Account {
	
	private final User user;
	private BigDecimal balance;
	private ArrayList<Transaction> transactionHistory = new ArrayList<>();
	
	public Account(User user){
		this(user, BigDecimal.ZERO);
	}
	
	public Account(User user, BigDecimal startingBalance){
		this.user = user;
		this.balance = startingBalance;
	}
	
	@Override
	public String toString(){
		return "The actual balance of " + user.getName() + " is " + balance + ".";
	}
	
	public BigDecimal getBalance(){
		return balance;
	}
	
	public void depositMoney(BigDecimal deposit){
		nullAndZeroCheck(deposit);
		addToBalance(deposit);
	}
	
	public void withdrawMoney(BigDecimal withdrawal){
		nullAndZeroCheck(withdrawal);
		outOfCoverageCheck(withdrawal);
		
		subtractFromBalance(withdrawal);
	}
	
	public void transferMoney(Account toAccount, BigDecimal amountToTransfer){
		nullAndZeroCheck(amountToTransfer);
		outOfCoverageCheck(amountToTransfer);
		if(toAccount == null) throw new IllegalArgumentException("Account can not be null.");
		if(toAccount == this) throw new IllegalArgumentException("You can not transfer money to your own account.");
		
		this.subtractFromBalance(amountToTransfer);
		toAccount.addToBalance(amountToTransfer);
	}
	
	public void printTransactionHistory(){
		StringBuilder transactionHistoryToPrint = new StringBuilder("Transaction History:\n\n");
		
		transactionHistory.forEach(
			t -> transactionHistoryToPrint.append(t.toString()).append("\n\n")	
		);
		System.out.print(transactionHistoryToPrint);
	}
	
	public void printTransactionHistoryByType(TransactionType transTypeToPrint){
		StringBuilder transactionHistoryToPrint = new StringBuilder("Transaction History (Only " + transTypeToPrint + "):\n\n");

		transactionHistory.forEach(
			t -> {if(t.getTransactionType() == transTypeToPrint) transactionHistoryToPrint.append(t.toString()).append("\n\n");}
		);
		System.out.print(transactionHistoryToPrint);
	}
	
	public void printTransactionHistoryByDate(LocalDate dateToPrint){
		StringBuilder transactionHistoryToPrint = new StringBuilder("Transaction History (Only day: " + dateToPrint + "):\n\n");

		transactionHistory.forEach(
			t -> {if(t.getTransactionDate().isEqual(dateToPrint)) transactionHistoryToPrint.append(t.toString()).append("\n\n");}
		);
		System.out.print(transactionHistoryToPrint);
	}
	
	private void addToBalance(BigDecimal amount){
		balance = balance.add(amount);
		newTransaction(amount, TransactionType.DEPOSIT);
	}
	
	private void subtractFromBalance(BigDecimal amount){
		balance = balance.subtract(amount);
		newTransaction(amount.negate(), TransactionType.WITHDRAWAL);
	}
	
	private int isOutOfCoverage(BigDecimal amount){
		return balance.compareTo(amount);
	}
	
	private void nullAndZeroCheck(BigDecimal amount){
		if(amount == null) throw new IllegalArgumentException("Transacton value can not be null.");
		if(amount == BigDecimal.ZERO) throw new IllegalArgumentException("Transacton value can not be 0.");
	}
	
	private void outOfCoverageCheck(BigDecimal amount){
		if(isOutOfCoverage(amount) == -1) throw new IllegalArgumentException("You are out of coverage to do this transaction.");
	}
	
	private void newTransaction(BigDecimal amount, TransactionType transType){
		transactionHistory.add(new Transaction(LocalDate.now(), amount, balance, transType));
	}
}
