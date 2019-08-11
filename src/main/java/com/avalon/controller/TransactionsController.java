package com.avalon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.avalon.dto.Transaction;
import com.avalon.service.TransactionsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Transactions")
@RestController
public class TransactionsController {

	@Autowired
	private TransactionsService transactionService;

	@ApiOperation(value = "Transactions")
	@RequestMapping(path = "/transactions", method = RequestMethod.GET)
	public List<Transaction> getAll() {
		return transactionService.getAll();
	}

	@ApiOperation(value = "Transactions by type")
	@RequestMapping(path = "/transactions/{type}", method = RequestMethod.GET)
	public List<Transaction> getByType(@PathVariable("type") String type) {
		return transactionService.getByType(type);
	}

	@ApiOperation(value = "Total amount of transactions by type")
	@RequestMapping(path = "/transactions/{type}/total-amount", method = RequestMethod.GET)
	public Double getTotalAmountByType(@PathVariable("type") String type) {
		return transactionService.getTotalAmountByType(type);
	}

}
