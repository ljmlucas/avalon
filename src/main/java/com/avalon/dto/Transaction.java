package com.avalon.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction {

	@JsonProperty("id")
	private String id;
	private String accountId;
	private String counterpartyAccount;
	private String counterpartyName;
	private String counterPartyLogoPath;
	private Double instructedAmount;
	private String instructedCurrency;
	private Double transactionAmount;
	private String transactionCurrency;
	private String transactionType;
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getCounterpartyAccount() {
		return counterpartyAccount;
	}

	public void setCounterpartyAccount(String counterpartyAccount) {
		this.counterpartyAccount = counterpartyAccount;
	}

	public String getCounterpartyName() {
		return counterpartyName;
	}

	public void setCounterpartyName(String counterpartyName) {
		this.counterpartyName = counterpartyName;
	}

	public String getCounterPartyLogoPath() {
		return counterPartyLogoPath;
	}

	public void setCounterPartyLogoPath(String counterPartyLogoPath) {
		this.counterPartyLogoPath = counterPartyLogoPath;
	}

	public Double getInstructedAmount() {
		return instructedAmount;
	}

	public void setInstructedAmount(Double instructedAmount) {
		this.instructedAmount = instructedAmount;
	}

	public String getInstructedCurrency() {
		return instructedCurrency;
	}

	public void setInstructedCurrency(String instructedCurrency) {
		this.instructedCurrency = instructedCurrency;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionCurrency() {
		return transactionCurrency;
	}

	public void setTransactionCurrency(String transactionCurrency) {
		this.transactionCurrency = transactionCurrency;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("this_account")
	protected void unpackThisAccount(Map<String, Object> account) {
		this.accountId = (String) account.get("id");
	}

	@SuppressWarnings("unchecked")
	@JsonProperty("other_account")
	protected void unpackOtherAccount(Map<String, Object> account) {
		this.counterpartyAccount = (String) account.get("number");
		Map<String, String> holder = (Map<String, String>) account.get("holder");
		this.counterpartyName = (String) holder.get("name");
		Map<String, String> metadata = (Map<String, String>) account.get("metadata");
		this.counterPartyLogoPath = (String) metadata.get("image_URL");
	}

	@SuppressWarnings("unchecked")
	@JsonProperty("details")
	protected void unpackDetails(Map<String, Object> details) {
		this.transactionType = (String) details.get("type");
		this.description = (String) details.get("description");
		Map<String, String> value = (Map<String, String>) details.get("value");
		
		this.instructedAmount = convertStringToDouble(value.get("amount"));
		this.instructedCurrency = (String) value.get("currency");
		this.transactionAmount = convertStringToDouble(value.get("amount"));
		this.transactionCurrency = (String) value.get("currency");
	}

	private Double convertStringToDouble(String amount) {
		try {
	        return Double.parseDouble(amount);
	    } catch (NumberFormatException | NullPointerException nfe) {
	        return 0.;
	    }
	}

}
