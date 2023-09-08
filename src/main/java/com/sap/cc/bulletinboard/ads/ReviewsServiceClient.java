package com.sap.cc.bulletinboard.ads;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.math.BigDecimal;

//import com.sap.cc.InvalidRequestException;


@Service
public class ReviewsServiceClient {

    @Value("${service.reviews.api_username}")
    private String reviewsServiceUsername = "";

    @Value("${service.reviews.api_password}")
    private String reviewsServicePassword = "";

    private final WebClient webClient;
    private final ReviewsServiceUrlProvider reviewsServiceUrlProvider;

    public ReviewsServiceClient(WebClient webClient, ReviewsServiceUrlProvider reviewsServiceUrlProvider) {
        this.webClient = webClient;
        this.reviewsServiceUrlProvider = reviewsServiceUrlProvider;
    }


    public Advertisement getAverageContactRatingFromReviews(Advertisement advertisement) throws JsonProcessingException {

        ReviewsResponse reviewsResponse = webClient.get().uri(String.format("%sapi/v1/averageRatings/%s", reviewsServiceUrlProvider.getServiceUrl(), advertisement.getContact()))
                .headers(httpHeaders -> httpHeaders.setBasicAuth(reviewsServiceUsername, reviewsServicePassword))
                .retrieve()
                .bodyToMono(ReviewsResponse.class)
                .block();

        if(reviewsResponse!=null){
            advertisement.setAverageContactRating(reviewsResponse.getAverage_rating());
        }
        else {
            advertisement.setAverageContactRating(0d);
        }
        return advertisement;
    }
}