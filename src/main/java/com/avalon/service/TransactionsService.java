package com.avalon.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.avalon.dto.Transaction;
import com.avalon.dto.TransactionsList;

@Service
public class TransactionsService {

	private static final String TRANSACTIONS_URL = "https://apisandbox.openbankproject.com/obp/v1.2.1/banks/rbs/accounts/savings-kids-john/public/transactions";

	@Autowired RestTemplate restTemplate;
	
	public List<Transaction> getAll() {
		ResponseEntity<TransactionsList> resp = restTemplate.getForEntity(TRANSACTIONS_URL,
				TransactionsList.class);

		return resp.getStatusCode() == HttpStatus.OK ? resp.getBody().getTransactions() : null;
	}

	public List<Transaction> getByType(String type) {
		List<Transaction> transactions = getAll();
		return transactions.stream().filter(transaction -> type.equals(transaction.getTransactionType()))
				.collect(Collectors.toList());
	}

	public Double getTotalAmountByType(String type) {
		List<Transaction> transactions = getByType(type);
		Double totalAmount = transactions.stream().mapToDouble(transaction -> transaction.getTransactionAmount()).sum();
		return totalAmount;
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	   return builder.build();
	}

}
