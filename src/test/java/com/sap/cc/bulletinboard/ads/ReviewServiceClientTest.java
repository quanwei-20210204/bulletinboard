package com.sap.cc.bulletinboard.ads;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.mockwebserver.MockWebServer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
public class ReviewServiceClientTest {

    public static final Advertisement WITH_VALID_ARGS = new Advertisement("WITH_VALID_ARGS ads","ok@example.com");
    public static final Advertisement WITH_UNKNOWN_CONTACT = new Advertisement("WITH_UNKNOWN_CONTACT ads","flower@example.com");

    private ReviewsServiceUrlProvider reviewsServiceUrlProvider = Mockito.mock(ReviewsServiceUrlProvider.class);
    private ReviewsServiceClient reviewsServiceClient;
    public static MockWebServer mockBackEnd;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @BeforeEach
    void initialize() {
        String serviceUrl = String.format("http://localhost:%s/api/v1/ads/", mockBackEnd.getPort());
        Mockito.when(reviewsServiceUrlProvider.getServiceUrl()).thenReturn(serviceUrl);
        reviewsServiceClient = new ReviewsServiceClient(WebClient.create(), reviewsServiceUrlProvider);

    }

    @Test
    void given_unknownContack_when_getAverageRatingFromReviewsService_then_return_0() throws Exception {
        mockBackEnd.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(null))
                .addHeader(org.springframework.http.HttpHeaders.CONTENT_TYPE,
                        org.springframework.http.MediaType.APPLICATION_JSON_VALUE));
        Advertisement advertisement = reviewsServiceClient.getAverageContactRatingFromReviews(WITH_UNKNOWN_CONTACT);
        assertEquals(0.0d, advertisement.getAverageContactRating());
    }

    @Test
    void given_validContack_when_getAverageRatingFromReviewsService_then_return_actualRate() throws Exception {
        mockBackEnd.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(3.5))
                .addHeader(org.springframework.http.HttpHeaders.CONTENT_TYPE,
                        org.springframework.http.MediaType.APPLICATION_JSON_VALUE));
        Advertisement advertisement = reviewsServiceClient.getAverageContactRatingFromReviews(WITH_VALID_ARGS);
        assertEquals(3.5f, advertisement.getAverageContactRating());
    }
}