package com.spendify.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spendify.entities.Expense;
import com.spendify.entities.Tag;
import com.spendify.services.ExpenseService;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin
public class ExpenseController {

	private final ExpenseService expenseService;

	public ExpenseController(ExpenseService expenseService) {
		this.expenseService = expenseService;
	}

	@GetMapping
	public List<Expense> getAllExpenses() {
		return expenseService.getAllExpenses();
	}

	// implement @requestBody later in all the methods
	@GetMapping("/expensesbydate")
	public List<Expense> getExpensesByDateRange(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {
		
		if (fromDate == null) {
	        fromDate = LocalDate.now().withDayOfMonth(1); // Default to first day of current month
	    }
	    if (toDate == null) {
	        toDate = LocalDate.now(); // Default to current date
	    }
		return expenseService.getExpensesByDateRange(fromDate, toDate);
	}

	@GetMapping("/total")
	public ResponseEntity<Double> getTotalAmount(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {
		double totalAmount = expenseService.getTotalAmount(fromDate, toDate);
		return ResponseEntity.ok(totalAmount);
	}

	@GetMapping("/category")
	public ResponseEntity<Map<Tag, Double>> getAmountPerCategory(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {

		Map<Tag, Double> amountPerCategory = expenseService.getAmountPerCategory(fromDate, toDate);
		return ResponseEntity.ok(amountPerCategory);

	}

	@PostMapping
	public Expense createExpense(@RequestBody Expense expense) {
		return expenseService.createExpense(expense);
	}

	@DeleteMapping("/{expenseId}")
	public void deleteExpense(@PathVariable long expenseId) {
		expenseService.deleteExpense(expenseId);
	}
}
