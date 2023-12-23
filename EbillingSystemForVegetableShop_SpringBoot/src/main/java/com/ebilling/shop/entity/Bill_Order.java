package com.ebilling.shop.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Bill_Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<OrderedItem> orderedItem;
	
	@ManyToOne
	@JsonBackReference
	private Customer customer;

	private long totalPrice;
	
	private long amtPaid;
	
	private String date =LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<OrderedItem> getOrderedItem() {
		return orderedItem;
	}

	public void setOrderedItem(List<OrderedItem> orderedItem) {
		this.orderedItem = orderedItem;
	}


	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public long getAmtPaid() {
		return amtPaid;
	}

	public void setAmtPaid(long amtPaid) {
		this.amtPaid = amtPaid;
	}

	public String getDate() {
		return date;
	}


	
	

}
