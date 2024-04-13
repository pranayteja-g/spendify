package com.spendify.entities;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Expense {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Double amount;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Tag tag;


	private LocalDateTime date;

	public Expense(Long id, Double amount, Tag tag, LocalDateTime date) {
		super();
		this.id = id;
		this.amount = amount;
		this.tag = tag;
		this.date = date;
	}

	public Expense() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public LocalDateTime getdate() {
		return date;
	}

	public void setdate(LocalDateTime date) {
		this.date = date;
	}

}
