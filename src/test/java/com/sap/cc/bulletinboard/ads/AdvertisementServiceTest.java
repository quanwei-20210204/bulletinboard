package com.sap.cc.bulletinboard.ads;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AdvertisementServiceTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
//    @Autowired
    private AdvertisementRepository advertisementRepository;
    @Autowired
//    @InjectMocks
    private AdvertisementService advertisementService;

    private final Advertisement AD_BIKE = createAdvertisementWithTitleContactPriceCurrency(
            "Bike for Sale", "Bike-sellers", BigDecimal.ONE, "USD");
    private final Advertisement AD_PAN = createAdvertisementWithTitleContactPriceCurrency(
            "Lightly used pan", "Second-hand Pans", BigDecimal.TEN, "EUR");
    @Before
    public void setUp() {
        advertisementService = new AdvertisementService(advertisementRepository);
    }
    @BeforeEach
    public void beforeEach() {
//        MockitoAnnotations.openMocks(this);
//        advertisementService.deleteAllAdvertisements();
    }

    @Test
    public void testRetrieveAdvertisementByIdNonExistingAdvertisement() {
        Optional<Advertisement> returnedAdvertisement = advertisementService.retrieveAdvertisementById(1L);
        assertThat(returnedAdvertisement.isPresent(), is(false));
    }

    @Test
    public void testSaveAdvertisement() {
        Advertisement returnedAdvertisement = advertisementService.saveAdvertisement(AD_BIKE);

        assertThat(returnedAdvertisement.getTitle(), is(AD_BIKE.getTitle()));
        assertThat(returnedAdvertisement.getContact(), is(AD_BIKE.getContact()));
//        assertThat(returnedAdvertisement.getId(), is(1L));
        assertThat(returnedAdvertisement.getPrice(), is(BigDecimal.ONE));
        assertThat(returnedAdvertisement.getCurrency(), is("USD"));
    }

    @Test
    public void testSaveTwoAdvertisements() {
        advertisementService.saveAdvertisement(AD_BIKE);

        Advertisement returnedAdvertisement = advertisementService.saveAdvertisement(AD_PAN);

        assertThat(returnedAdvertisement.getTitle(), is(AD_PAN.getTitle()));
        assertThat(returnedAdvertisement.getContact(), is(AD_PAN.getContact()));
//        assertThat(returnedAdvertisement.getId(), is(2L));
        assertThat(returnedAdvertisement.getPrice(), is(BigDecimal.TEN));
        assertThat(returnedAdvertisement.getCurrency(), is("EUR"));
    }

//    @Test
//    public void testSaveAdvertisementTryToForceId() {
//        Advertisement ad = AD_BIKE;
//        ad.setId(10L);
//        Advertisement returnedAdvertisement = advertisementService.saveAdvertisement(ad);
//
//        assertThat(returnedAdvertisement.getId(), is(1L));
//    }

//    @Test
//    public void testSaveAndRetrieveAdvertisementById() {
//        advertisementService.saveAdvertisement(AD_BIKE);
//
//        Optional<Advertisement> returnedAdvertisement = advertisementService.retrieveAdvertisementById(1L);
//
//        assertThat(returnedAdvertisement.isPresent(), is(true));
////        assertThat(returnedAdvertisement.get().getId(), is(1L));
//        assertThat(returnedAdvertisement.get().getTitle(), is(AD_BIKE.getTitle()));
//        assertThat(returnedAdvertisement.get().getContact(), is(AD_BIKE.getContact()));
//        assertThat(returnedAdvertisement.get().getPrice(), is(BigDecimal.ONE));
//        assertThat(returnedAdvertisement.get().getCurrency(), is("USD"));
//
//    }

//    @Test
//    public void testUpdateTitleOfExistingAdvertisement() {
//        Advertisement returnedAdvertisement = advertisementService.saveAdvertisement(AD_BIKE);
//        assertThat(returnedAdvertisement.getId(), is(1L));
//
//        final String newTitle = "Bruce Schneier";
//        returnedAdvertisement.setTitle(newTitle);
//
//        advertisementService.saveAdvertisement(returnedAdvertisement);
//
//        assertThat(returnedAdvertisement.getTitle(), is(newTitle));
//        assertThat(returnedAdvertisement.getId(), is(1L));
//
//    }

    @Test
    public void testUpdatePriceOfExistingAdvertisement() {
        Advertisement returnedAdvertisement = advertisementService.saveAdvertisement(AD_BIKE);
//        assertThat(returnedAdvertisement.getId(), is(1L));

        final BigDecimal newPrice = BigDecimal.valueOf(2L);
        returnedAdvertisement.setPrice(newPrice);

        advertisementService.saveAdvertisement(returnedAdvertisement);

        assertThat(returnedAdvertisement.getPrice(), is(newPrice));
//        assertThat(returnedAdvertisement.getId(), is(1L));

    }

    @Test
    public void testGetAllEmpty() {
        List<Advertisement> returnedAdvertisements = advertisementService.retrieveAllAdvertisements();
//        assertThat(returnedAdvertisements.size(), is(0));
    }

//    @Test
//    public void testGetAllFirstOneThenTwoEntries() {
//
//        advertisementService.saveAdvertisement(AD_BIKE);
//
//        List<Advertisement> returnedAdvertisements = advertisementService.retrieveAllAdvertisements();
//        assertThat(returnedAdvertisements.size(), is(1));
//        Advertisement ad = returnedAdvertisements.get(0);
//        assertThat(ad.getTitle(), is(AD_BIKE.getTitle()));
//        assertThat(ad.getContact(), is(AD_BIKE.getContact()));
//        assertThat(ad.getId(), is(1L));
//        assertThat(ad.getPrice(), is(BigDecimal.ONE));
//        assertThat(ad.getCurrency(), is("USD"));
//
//        advertisementService.saveAdvertisement(AD_PAN);
//
//        returnedAdvertisements = advertisementService.retrieveAllAdvertisements();
//        assertThat(returnedAdvertisements.size(), is(2));
//
//    }

    @Test
    public void testRetrieveAdvertisementByIdThrowsExceptionForNegativeValue() {

        assertThatThrownBy(() ->
        {
            advertisementService.retrieveAdvertisementById(-1L);
        }).isInstanceOf(IllegalArgumentException.class);

    }

//    @Test
//    public void testDeleteSingle() {
//
//        advertisementService.saveAdvertisement(AD_BIKE);
//        advertisementService.saveAdvertisement(AD_PAN);
//
//        List<Advertisement> returnedAdvertisements = advertisementService.retrieveAllAdvertisements();
//        assertThat(returnedAdvertisements.size(), is(2));
//
//        advertisementService.deleteAdvertisement(13L);
//
//        returnedAdvertisements = advertisementService.retrieveAllAdvertisements();
//
//        assertThat(returnedAdvertisements.size(), is(1));
//        Advertisement ad = returnedAdvertisements.get(0);
//        assertThat(ad.getTitle(), is(AD_PAN.getTitle()));
//        assertThat(ad.getContact(), is(AD_PAN.getContact()));
//        assertThat(ad.getId(), is(2L));
//
//    }

    @Test
    public void testDeleteAll() {

        advertisementService.saveAdvertisement(AD_BIKE);
        advertisementService.saveAdvertisement(AD_PAN);

        List<Advertisement> returnedAdvertisements = advertisementService.retrieveAllAdvertisements();
//        assertThat(returnedAdvertisements.size(), is(2));

//        advertisementService.deleteAllAdvertisements();

        returnedAdvertisements = advertisementService.retrieveAllAdvertisements();
//        assertThat(returnedAdvertisements.size(), is(0));
    }




    private Advertisement createAdvertisementWithTitleContactPriceCurrency(
            String title, String contact, BigDecimal price, String currency) {
        Advertisement ad = new Advertisement();
        ad.setTitle(title);
        ad.setContact(contact);
        ad.setPrice(price);
        ad.setCurrency(currency);
        ad.setAverageContactRating(0d);
        return ad;
    }

}