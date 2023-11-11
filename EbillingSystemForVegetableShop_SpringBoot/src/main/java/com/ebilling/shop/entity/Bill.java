package com.ebilling.shop.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Bill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy = "bill")
	private List<OrderedItem> orderedItem;
	
	@ManyToOne
	private Shopowner shopowner;
	
	@ManyToOne
	private Customer customer;

	private long totalPrice;
	
	private long amtPaid;

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

	public Shopowner getShopowner() {
		return shopowner;
	}

	public void setShopowner(Shopowner shopowner) {
		this.shopowner = shopowner;
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
	

}
