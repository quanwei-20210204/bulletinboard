package com.sap.cc.bulletinboard.smoketests;

public class Advertisement {

    private Long id;

    private String title;
    private String contact;
    private String price;

    public Advertisement() {}

    public Advertisement(String title, String contact, String price) {
        this.title = title;
        this.contact = contact;
        this.price = price;
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
