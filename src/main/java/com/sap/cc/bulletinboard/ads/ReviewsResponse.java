package com.sap.cc.bulletinboard.ads;
public class ReviewsResponse {
    Double average_rating;

    public Double getAverage_rating() {
        return average_rating;
    }

    public void setAverage_rating(Double average_rating) {
        this.average_rating = average_rating;
    }

    public ReviewsResponse(Double average_rating) {
        this.average_rating = average_rating;
    }
    public ReviewsResponse() {
    }
}
