package com.sap.cc.bulletinboard.ads;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;
@SpringBootTest
@AutoConfigureMockMvc
public class AdvertisementTest {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void given_whengetAllAdvertisement_thenStatusOKReturn() throws Exception {
        mockMvc.perform(get("/api/v1/ads/"))
                .andExpect(status().isOk());
                //.andExpect(content().json("[]"));
    }

//    @Test
//    void given_rootPath_when_getAllAdvertisement_then_returnAListOfAdvertisement() throws Exception {
//        String objectAdvertisement0=objectMapper.writeValueAsString(new Advertisement("title0"));
//        String objectAdvertisement1=objectMapper.writeValueAsString(new Advertisement("title1"));
//        mockMvc.perform(post("/api/v1/ads/")
//                .content(objectAdvertisement0)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated());
//        mockMvc.perform(post("/api/v1/ads/")
//                        .content(objectAdvertisement1)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated());
//
//        ResultActions resultActions = mockMvc.perform(get("/api/v1/ads/"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").value(2))
//                .andExpect(jsonPath("$[0].title", is("title0")))
//                .andExpect(jsonPath("$[1].title", is("title1")));
//    }
    @Test
    void given_id_when_getSingleAdvertisement_then_returnAdvertisementWithStatusOK() throws Exception{
        mockMvc.perform(get("/api/v1/ads/121")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title",is("Updated Title9.4")))
                .andExpect(jsonPath("$.id",is(121)))
                .andExpect(jsonPath("$.averageContactRating",is(3.5)));
    }

    @Test
    void given_negativeID_when_getSingleAdvertisement_then_throwIllegalArgumentException() throws Exception {
        mockMvc.perform(get("/api/v1/ads/-2"))
                .andExpect(status().isBadRequest())
                .andExpect(result->assertTrue(result.getResolvedException() instanceof IllegalArgumentException));
    }

    @Test
    void given_whenAddAdvertisement_thenStatus201Return() throws Exception {
        String objectAdvertisement = objectMapper.writeValueAsString(new Advertisement("title1"));
        mockMvc.perform(post("/api/v1/ads/")
                        .content(objectAdvertisement)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated());

//        mockMvc.perform(get("/api/v1/ads/"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].title",is("title1")));
    }

    @Test
    void given_0_whenAddAdvertisement_then_returnStatus405() throws Exception{
        String objectAdvertisement = objectMapper.writeValueAsString(new Advertisement("title1"));
        mockMvc.perform(post("/api/v1/ads/0")
                        .content(objectAdvertisement)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());

    }

    @Test
    void given_notExist_when_getAllAdvertisement_then_returnStatus404() throws Exception{
        mockMvc.perform(get("/api/v1/ads/100"))
                .andExpect(status().isNotFound());
    }
    @Test
    void testRepositorySave(){
        Advertisement advertisement=new Advertisement("title2023-8-30");
        Advertisement save = advertisementRepository.save(advertisement);
        assertNotNull(save);
    }
//    @Test
//    void testRepositoryFindAll(){
//        List<Advertisement> all = advertisementRepository.findAll();
//        assertEquals(1,all.size());
//    }
//    @Test
//    void testRepositoryDellAll(){
//        List<Advertisement> all = advertisementRepository.findAll();
//        assertEquals(1,all.size());
//        advertisementRepository.deleteAll();
//        all = advertisementRepository.findAll();
//        assertEquals(0,all.size());
//    }


}