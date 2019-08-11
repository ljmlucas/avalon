package com.avalon.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.avalon.service.TransactionsService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = TransactionsController.class)
public class TransactionsControllerTests extends Mockito {

	@Mock private TransactionsService transactionsService;
	@InjectMocks private TransactionsController transactionsController;
	
	@Before
	public void start(){	
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldCallGetAllOfServiceOnGetAllRoute() {
		transactionsController.getAll();
		verify(transactionsService, times(1)).getAll();
	}
	
	@Test
	public void shouldCallGetByTypeOfServiceOnGetByTypeRoute() {
		String type = "type";
		transactionsController.getByType(type);
		verify(transactionsService, times(1)).getByType(type);
	}
		
	@Test
	public void shouldCallgetTotalAmountByTypeOfServiceOnGetTotalAmountByTypeRoute() {
		String type = "type";
		transactionsController.getTotalAmountByType(type);
		verify(transactionsService, times(1)).getTotalAmountByType(type);
	}

}
