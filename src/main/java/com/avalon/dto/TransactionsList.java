package com.avalon.dto;

import java.util.ArrayList;
import java.util.List;

public class TransactionsList {

	private List<Transaction> transactions;
	
	
	public TransactionsList() {
		this.transactions = new ArrayList<Transaction>();
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
}
