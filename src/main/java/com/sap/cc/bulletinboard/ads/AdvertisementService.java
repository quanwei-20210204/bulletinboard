package com.sap.cc.bulletinboard.ads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    @Autowired
    public AdvertisementService(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository=advertisementRepository;
    }
    public Advertisement saveAdvertisement(final Advertisement ad){
        Advertisement advertisement = advertisementRepository.save(ad);
        return advertisement;
    }
    public Optional<Advertisement> retrieveAdvertisementById(Long id){
        if(id <= 0) {
            throw new IllegalArgumentException("Negative ids are not allowed");
        }
        return advertisementRepository.findById(id);
    }
    public List<Advertisement> retrieveAllAdvertisements(){
        return advertisementRepository.findAll();
    }
    public void deleteAllAdvertisements(){
        advertisementRepository.deleteAll();
    }
    public void deleteAdvertisement(Long id){
        advertisementRepository.deleteById(id);
    }
}
