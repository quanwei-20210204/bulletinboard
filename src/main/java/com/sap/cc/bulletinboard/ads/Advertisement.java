package com.sap.cc.bulletinboard.ads;

public class Advertisement {
	private Long id;

	private String title;
	private String contact;
	private String price;

	@Override
	public String toString() {
		return "Advertisement [id=" + id + ", title=" + title + ", contact=" + contact + ", price=" + price + "]";
	}

	public Advertisement() {
	}

	public Advertisement(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
