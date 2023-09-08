package com.sap.cc.bulletinboard.ads;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ReviewsServiceUrlProvider {

    @Value("${service.reviews.url}")
    private String reviewServiceUrl = "";

    public String getServiceUrl() {
        return reviewServiceUrl;
    }
}
