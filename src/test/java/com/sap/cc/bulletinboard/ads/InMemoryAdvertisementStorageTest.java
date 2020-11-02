package com.sap.cc.bulletinboard.ads;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class InMemoryAdvertisementStorageTest {

	private InMemoryAdvertisementStorage adStorage = new InMemoryAdvertisementStorage();

	private final Advertisement AD_BIKE = createAdvertisementWithTitleAndContact("Bike for Sale", "Bike-sellers");
	private final Advertisement AD_PAN = createAdvertisementWithTitleAndContact("Lightly used pan", "Second-hand Pans");

	@BeforeEach
	public void beforeEach() {
		adStorage.deleteAllAdvertisements();
	}

	@Test
	public void testRetrieveAdvertisementByIdNonExistingAdvertisement() {
		Optional<Advertisement> returnedAdvertisement = adStorage.retrieveAdvertisementById(1L);
		assertThat(returnedAdvertisement.isPresent(), is(false));
	}

	@Test
	public void testSaveAdvertisement() {
		Advertisement returnedAdvertisement = adStorage.saveAdvertisement(AD_BIKE);

		assertThat(returnedAdvertisement.getTitle(), is(AD_BIKE.getTitle()));
		assertThat(returnedAdvertisement.getContact(), is(AD_BIKE.getContact()));
		assertThat(returnedAdvertisement.getId(), is(1L));
	}

	@Test
	public void testSaveTwoAdvertisements() {
		adStorage.saveAdvertisement(AD_BIKE);

		Advertisement returnedAdvertisement = adStorage.saveAdvertisement(AD_PAN);

		assertThat(returnedAdvertisement.getTitle(), is(AD_PAN.getTitle()));
		assertThat(returnedAdvertisement.getContact(), is(AD_PAN.getContact()));
		assertThat(returnedAdvertisement.getId(), is(2L));
	}

	@Test
	public void testSaveAdvertisementTryToForceId() {
		Advertisement ad = AD_BIKE;
		ad.setId(10L);
		Advertisement returnedAdvertisement = adStorage.saveAdvertisement(ad);

		assertThat(returnedAdvertisement.getId(), is(1L));
	}

	@Test
	public void testSaveAndRetrieveAdvertisementById() {
		adStorage.saveAdvertisement(AD_BIKE);

		Optional<Advertisement> returnedAdvertisement = adStorage.retrieveAdvertisementById(1L);

		assertThat(returnedAdvertisement.isPresent(), is(true));
		assertThat(returnedAdvertisement.get().getId(), is(1L));
		assertThat(returnedAdvertisement.get().getTitle(), is(AD_BIKE.getTitle()));
		assertThat(returnedAdvertisement.get().getContact(), is(AD_BIKE.getContact()));

	}

	@Test
	public void testUpdateTitleOfExistingAdvertisement() {
		Advertisement returnedAdvertisement = adStorage.saveAdvertisement(AD_BIKE);
		assertThat(returnedAdvertisement.getId(), is(1L));

		final String newTitle = "Bruce Schneier";
		returnedAdvertisement.setTitle(newTitle);

		adStorage.saveAdvertisement(returnedAdvertisement);

		assertThat(returnedAdvertisement.getTitle(), is(newTitle));
		assertThat(returnedAdvertisement.getId(), is(1L));

	}

	@Test
	public void testGetAllEmpty() {
		List<Advertisement> returnedAdvertisements = adStorage.retrieveAllAdvertisements();
		assertThat(returnedAdvertisements.size(), is(0));
	}

	@Test
	public void testGetAllFirstOneThenTwoEntries() {

		adStorage.saveAdvertisement(AD_BIKE);

		List<Advertisement> returnedAdvertisements = adStorage.retrieveAllAdvertisements();
		assertThat(returnedAdvertisements.size(), is(1));
		assertThat(returnedAdvertisements.iterator().next().getTitle(), is(AD_BIKE.getTitle()));
		assertThat(returnedAdvertisements.iterator().next().getContact(), is(AD_BIKE.getContact()));
		assertThat(returnedAdvertisements.iterator().next().getId(), is(1L));

		adStorage.saveAdvertisement(AD_PAN);

		returnedAdvertisements = adStorage.retrieveAllAdvertisements();
		assertThat(returnedAdvertisements.size(), is(2));

	}

	@Test
	public void testDeleteSingle() {

		adStorage.saveAdvertisement(AD_BIKE);
		adStorage.saveAdvertisement(AD_PAN);

		List<Advertisement> returnedAdvertisements = adStorage.retrieveAllAdvertisements();
		assertThat(returnedAdvertisements.size(), is(2));

		adStorage.deleteAdvertisement(1L);

		returnedAdvertisements = adStorage.retrieveAllAdvertisements();

		assertThat(returnedAdvertisements.size(), is(1));
		assertThat(returnedAdvertisements.iterator().next().getTitle(), is(AD_PAN.getTitle()));
		assertThat(returnedAdvertisements.iterator().next().getContact(), is(AD_PAN.getContact()));
		assertThat(returnedAdvertisements.iterator().next().getId(), is(2L));

	}

	@Test
	public void testDeleteAll() {

		adStorage.saveAdvertisement(AD_BIKE);
		adStorage.saveAdvertisement(AD_PAN);

		List<Advertisement> returnedAdvertisements = adStorage.retrieveAllAdvertisements();
		assertThat(returnedAdvertisements.size(), is(2));

		adStorage.deleteAllAdvertisements();

		returnedAdvertisements = adStorage.retrieveAllAdvertisements();
		assertThat(returnedAdvertisements.size(), is(0));
	}

	private Advertisement createAdvertisementWithTitleAndContact(String title, String contact) {
		Advertisement ad = new Advertisement();
		ad.setTitle(title);
		ad.setContact(contact);
		return ad;
	}

}
