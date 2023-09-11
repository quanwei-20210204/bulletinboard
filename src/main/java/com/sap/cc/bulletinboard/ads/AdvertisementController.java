package com.sap.cc.bulletinboard.ads;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpServletRequest;



@RestController
@RequestMapping("/api/v1/ads/")
public class AdvertisementController  {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
//    private InMemoryAdvertisementStorage inMemoryAdvertisementStorage;
    private AdvertisementService advertisementService;
    @Autowired
    ReviewsServiceClient reviewsServiceClient;
    @GetMapping
    public ResponseEntity<List<Advertisement>> getAllAdvertisement() throws JsonProcessingException {
        List<Advertisement> advertisements = advertisementService.retrieveAllAdvertisements();
        for (Advertisement advertisement:advertisements
             ) {
            advertisement=reviewsServiceClient.getAverageContactRatingFromReviews(advertisement);
        }
        return ResponseEntity.ok(advertisements);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Advertisement> getSingleAdvertisement(@PathVariable("id") Long id) throws IllegalArgumentException, JsonProcessingException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String endpointPath = request.getRequestURI();
        MDC.put("endpoint",endpointPath);
        logger.info("This is a sample log msg");

        if (id<0){
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            for (StackTraceElement element:stackTrace){
                sb.append(element.toString()).append("\n");
            }
            logger.warn("Negative advertisement ID: {}\n {} ",id,sb);
            MDC.clear();
            throw new IllegalArgumentException("Invalid advertisement ID");
        }
        logger.info("Requested id: {}",id);
        Optional<Advertisement> advertisement = advertisementService.retrieveAdvertisementById(id);
        if (!advertisement.isPresent()){
            logger.warn("Not found advertisement, id: {}",id);
            MDC.clear();
            return ResponseEntity.notFound().build();
        }else {
            logger.trace("Requested advertisement, id: {},title: {}",id,advertisement.get().getTitle());
            advertisement=Optional.of(reviewsServiceClient.getAverageContactRatingFromReviews(advertisement.get()));

            MDC.clear();
            return ResponseEntity.ok(advertisement.get());
        }
    }

    @PostMapping
    public ResponseEntity<Advertisement> addAdvertisement(@RequestBody Advertisement ad){
        Advertisement advertisement = advertisementService.saveAdvertisement(ad);

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String endpointPath = request.getRequestURI();
        MDC.put("endpoint",endpointPath);
        logger.trace("Post advertisement, id:{}, title:{}",advertisement.getId(),advertisement.getTitle());
        MDC.clear();
        return new ResponseEntity<>(advertisement, HttpStatus.CREATED);
    }
    @PostMapping("/0")
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public void addAdvertisement_0() throws HttpRequestMethodNotSupportedException {
        throw new HttpRequestMethodNotSupportedException("POST");
    }

}
