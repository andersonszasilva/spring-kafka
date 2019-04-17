package br.com.fiap.tcdamazon.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String description;
	private String category;
	private Integer viewQuantities = 0;
	
	public Product() { }
	
	public Product(String description, String category) {
		this.description = description;
		this.category = category;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public Integer getViewQuantities() {
		return viewQuantities;
	}
	
	public void setViewQuantities(Integer viewQuantities) {
		this.viewQuantities = viewQuantities;
	}
	

}
