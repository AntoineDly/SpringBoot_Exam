package com.tactfactory.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Book extends BaseEntity {
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Integer nbPage;
	
	@Column(nullable = false)
	private Integer price;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNbPage() {
		return nbPage;
	}

	public void setNbPage(Integer nbPage) {
		this.nbPage = nbPage;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

}
