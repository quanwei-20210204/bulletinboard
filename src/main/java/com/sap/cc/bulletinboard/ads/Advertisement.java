package com.sap.cc.bulletinboard.ads;

public class Advertisement {
	private Long id;

	private String title;

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
}
