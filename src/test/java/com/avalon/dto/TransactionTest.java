package com.avalon.dto;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = Transaction.class)
public class TransactionTest {

	@Test
	public void shouldUnpackThisAccountFields() {
		Transaction transaction = new Transaction();
		Map<String, Object> account = new HashMap<String, Object>();
		account.put("id", "id");
		transaction.unpackThisAccount(account);
		
		assertEquals("id", transaction.getAccountId());
	}

	@Test
	public void shouldUnpackOtherAccountFields() {
		Transaction transaction = new Transaction();
		Map<String, Object> account = new HashMap<String, Object>();
		account.put("number", "number");
		Map<String, Object> holder = new HashMap<String, Object>();
		holder.put("name", "name");
		account.put("holder", holder);
		Map<String, Object> metadata = new HashMap<String, Object>();
		metadata.put("image_URL", "image_URL");
		account.put("metadata", metadata);

		transaction.unpackOtherAccount(account);
		
		assertEquals("number", transaction.getCounterpartyAccount());
		assertEquals("name", transaction.getCounterpartyName());
		assertEquals("image_URL", transaction.getCounterPartyLogoPath());
	}

	@Test
	public void shouldUnpackDetailsFields() {
		Transaction transaction = new Transaction();
		Map<String, Object> details = new HashMap<String, Object>();
		details.put("type", "type");
		details.put("description", "description");
		Map<String, Object> value = new HashMap<String, Object>();
		value.put("amount", "10");
		value.put("currency", "currency");
		details.put("value", value);

		transaction.unpackDetails(details);
		
		assertEquals("type", transaction.getTransactionType());
		assertEquals("description", transaction.getDescription());
		assertEquals("currency", transaction.getInstructedCurrency());
		assertEquals("currency", transaction.getTransactionCurrency());
		assertEquals(new Double(10), transaction.getTransactionAmount());
		assertEquals(new Double(10), transaction.getInstructedAmount());
	}
	
	@Test
	public void shouldSetAmountsZeroIfAmountNotValid() {
		Transaction transaction = new Transaction();
		Map<String, Object> details = new HashMap<String, Object>();
		Map<String, Object> value = new HashMap<String, Object>();
		value.put("amount", "invalid");
		details.put("value", value);

		transaction.unpackDetails(details);
		
		assertEquals(new Double(0), transaction.getTransactionAmount());
		assertEquals(new Double(0), transaction.getInstructedAmount());
	}
	
	@Test
	public void shouldSetAmountsZeroIfAmountIsNull() {
		Transaction transaction = new Transaction();
		Map<String, Object> details = new HashMap<String, Object>();
		Map<String, Object> value = new HashMap<String, Object>();
		value.put("amount", null);
		details.put("value", value);

		transaction.unpackDetails(details);
		
		assertEquals(new Double(0), transaction.getTransactionAmount());
		assertEquals(new Double(0), transaction.getInstructedAmount());
	}
	
}
