package com.spendify.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.spendify.entities.Expense;
import com.spendify.entities.Tag;
import com.spendify.repositories.ExpenseRepository;

@Service
public class ExpenseService {

	private ExpenseRepository expenseRepo;

//	@Autowired
	public ExpenseService(ExpenseRepository expenseRepo) {
		this.expenseRepo = expenseRepo;
	}

	// get all expenses in DB
	public List<Expense> getAllExpenses() {
		return expenseRepo.findAll();
	}
	
	// get expenses in a range of dates
	public List<Expense> getExpensesByDateRange(LocalDate fromDate, LocalDate toDate) {
		LocalDateTime fromDateTime = fromDate.atStartOfDay();
		LocalDateTime toDateTime = toDate.atTime(LocalTime.MAX);
        return expenseRepo.findByDateBetween(fromDateTime, toDateTime);
    }
	
	// Overloaded method to provide default date range
    public List<Expense> getExpensesByDateRange() {
        // Default fromDate to the first day of the current month
        LocalDate fromDate = LocalDate.now().withDayOfMonth(1);
        // Default toDate to the current date
        LocalDate toDate = LocalDate.now();
        return getExpensesByDateRange(fromDate, toDate);
    }

	public Expense createExpense(Expense expense) {
		return expenseRepo.save(expense);
	}
	
	// Delete expense
	public void deleteExpense(Long expenseID) {
		expenseRepo.deleteById(expenseID);
	}

	// getTotalAmount in a range of dates
	public double getTotalAmount(LocalDate fromDate, LocalDate toDate) {
		LocalDateTime fromDateTime = fromDate.atStartOfDay();
		LocalDateTime toDateTime = toDate.atTime(LocalTime.MAX);

		List<Expense> expenses = expenseRepo.findByDateBetween(fromDateTime, toDateTime);
		return expenses.stream().mapToDouble(Expense::getAmount).sum();
	}

	// get amount per tag category in a range of dates
	public Map<Tag, Double> getAmountPerCategory(LocalDate fromDate, LocalDate toDate) {
		LocalDateTime fromDateTime = fromDate.atStartOfDay();
		LocalDateTime toDateTime = toDate.atTime(LocalTime.MAX);

		List<Expense> expenses = expenseRepo.findByDateBetween(fromDateTime, toDateTime);
		Map<Tag, Double> amountPerCategory = new HashMap<>();
		for (Expense expense : expenses) {
			Tag category = expense.getTag();
			double amount = amountPerCategory.getOrDefault(category, 0.0);
			amountPerCategory.put(category, amount + expense.getAmount());
		}

		return amountPerCategory;
	}

}
