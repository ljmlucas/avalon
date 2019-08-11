package com.avalon.security;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.avalon.controller.TransactionsController;
import com.avalon.service.TransactionsService;

@RunWith(SpringRunner.class)
@WebMvcTest(TransactionsController.class)
public class SecurityTests {

	private static final String USERNAME = "username";
	@Autowired
	private MockMvc mvc;
	@MockBean
	private TransactionsService transactionsService;

	@Test
	public void shouldSuccessWith200RouteDocs() throws Exception {
		mvc.perform(get("/swagger-ui.html").contentType(MediaType.TEXT_HTML)).andExpect(status().isOk());
	}

	@Test
	public void shouldFailWith403RouteTransactionsWithoutCredentials() throws Exception {
		mvc.perform(get("/transactions").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
	}

	@Test
	public void shouldSuccessWith200RouteTransactionsWithValidCredentials() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/transactions").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", TokenAuthenticationService.createToken(USERNAME))).andExpect(status().isOk());
	}

	@Test
	public void shouldFailWith403RouteTransactionsByTpeWithoutCredentials() throws Exception {
		mvc.perform(get("/transactions/type").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isForbidden());
	}

	@Test
	public void shouldSuccessWith200RouteTransactionsByTypeWithValidCredentials() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/transactions/type").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", TokenAuthenticationService.createToken(USERNAME))).andExpect(status().isOk());
	}

	@Test
	public void shouldFailWith403RouteTotalAmountByTransactionsTypeWithoutCredentials() throws Exception {
		mvc.perform(get("/transactions/type/total-amount").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isForbidden());
	}

	@Test
	public void shouldSuccessWith200RouteTotalAmountByTransactionsTypeWithValidCredentials() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/transactions/type/total-amount")
				.contentType(MediaType.APPLICATION_JSON).header("Authorization", TokenAuthenticationService.createToken(USERNAME)))
				.andExpect(status().isOk());
	}

}
