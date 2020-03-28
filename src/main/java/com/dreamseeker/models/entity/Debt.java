package com.dreamseeker.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Debts")
public class Debt implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String apartmentID;
	private float total;
	private String month;
	private String year;

	public Debt () {
		
	}
	
	public Debt(String apartmentID, float debt, String month, String year) {
		this.apartmentID = apartmentID;
		this.total = debt;
		this.month = month;
		this.year = year;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApartmentID() {
		return apartmentID;
	}

	public void setApartmentID(String apartmentId) {
		this.apartmentID = apartmentId;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float debt) {
		this.total = debt;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
}
