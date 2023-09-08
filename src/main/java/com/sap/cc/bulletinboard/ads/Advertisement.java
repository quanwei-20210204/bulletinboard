package com.sap.cc.bulletinboard.ads;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class Advertisement implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String contact;
    private BigDecimal price;
    private String currency;
    private Double averageContactRating;

    public Advertisement(String title, String contact) {
        this.title = title;
        this.contact = contact;
    }


    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


    @Override
    public String toString() {
        return "Advertisement{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", contact='" + contact + '\'' +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                ", averageContactRating=" + averageContactRating +
                '}';
    }

    public Advertisement() {
    }

    public Advertisement(String title) {
        this.title = title;
        this.averageContactRating=0d;//default value
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public void setAverageContactRating(Double averageContactRating) {
        this.averageContactRating = averageContactRating;
    }
    public Double getAverageContactRating() {
        return averageContactRating;
    }


}
