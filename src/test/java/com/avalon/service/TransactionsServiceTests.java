package com.avalon.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.avalon.dto.Transaction;
import com.avalon.dto.TransactionsList;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = TransactionsService.class)
public class TransactionsServiceTests extends Mockito {

	private static final String TRANSACTIONS_URL = "https://apisandbox.openbankproject.com/obp/v1.2.1/banks/rbs/accounts/savings-kids-john/public/transactions";

	@InjectMocks
	private TransactionsService transactionsService;
	@Mock
	private RestTemplate restTemplate;

	@Before
	public void start() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldReturnListOfTransactionsOnGetAll() {
		TransactionsList list = new TransactionsList();
		List<Transaction> transactions = new ArrayList<Transaction>();
		Transaction transaction = new Transaction();
		transaction.setId("1");
		transactions.add(transaction);
		list.setTransactions(transactions);
		
		Mockito
        .when(restTemplate.getForEntity(TRANSACTIONS_URL, TransactionsList.class))
        .thenReturn(new ResponseEntity<TransactionsList>(list, HttpStatus.OK));

		assertEquals(1, transactionsService.getAll().size());
		
	}
	
	@Test
	public void shouldReturnNullOnGetAllIfHttpStatusIsNotOK() {
		TransactionsList list = new TransactionsList();
		List<Transaction> transactions = new ArrayList<Transaction>();
		Transaction transaction = new Transaction();
		transaction.setId("1");
		transactions.add(transaction);
		list.setTransactions(transactions);
		
		Mockito
        .when(restTemplate.getForEntity(TRANSACTIONS_URL, TransactionsList.class))
        .thenReturn(new ResponseEntity<TransactionsList>(list, HttpStatus.BAD_GATEWAY));

		assertNull(transactionsService.getAll());
	}
	
	@Test
	public void shouldsReturnListOfTransactionsByTypeOnGetByType() {
		List<Transaction> transactions = new ArrayList<Transaction>();
		Transaction t1 = new Transaction();
		t1.setTransactionType("TYPE 1");
		transactions.add(t1);
		Transaction t2 = new Transaction();
		t2.setTransactionType("TYPE 2");
		transactions.add(t2);

		TransactionsService spyTransactionsService = Mockito.spy(transactionsService);
		doReturn(transactions).when(spyTransactionsService).getAll();
		
		assertEquals(1, spyTransactionsService.getByType("TYPE 1").size());
	}
	
	@Test
	public void shouldsReturnTotalAmountTransactionsByTypeOnGetTotalAmountByType() {
		List<Transaction> transactions = new ArrayList<Transaction>();
		Transaction t1 = new Transaction();
		t1.setTransactionType("TYPE 1");
		t1.setTransactionAmount(10.5);
		transactions.add(t1);
		Transaction t2 = new Transaction();
		t2.setTransactionType("TYPE 1");
		t2.setTransactionAmount(15.2);
		transactions.add(t2);
		Transaction t3 = new Transaction();
		t3.setTransactionType("TYPE 2");
		t3.setTransactionAmount(75.);
		transactions.add(t3);

		TransactionsService spyTransactionsService = Mockito.spy(transactionsService);
		doReturn(transactions).when(spyTransactionsService).getAll();
		
		assertEquals(new Double(25.7), spyTransactionsService.getTotalAmountByType("TYPE 1"));
	}

}
