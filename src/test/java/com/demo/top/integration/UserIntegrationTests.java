package com.demo.top.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.demo.top.model.artist.Artist;
import com.demo.top.model.response.ApplicationArtistResponse;
import com.demo.top.utils.TestUtils;
import com.google.common.base.Charsets;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@SpringBootTest
class UserIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void userDoesNotExist() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("http://localhost:8080/v1/user/09999/artists/favourites");

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void userFavouritesRetrievalSuccess() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("http://localhost:8080/v1/user/1/artists/favourites");


        String response = mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(Charsets.UTF_8);

        ApplicationArtistResponse artistResponse = TestUtils.getStringAsObject(response, ApplicationArtistResponse.class);


        assertEquals(1, artistResponse.getArtists().size());
        assertEquals("Kanye West", artistResponse.getArtists().get(0).getArtistName());
    }

    @Test
    void userFavouritesSaveSuccess() throws Exception {

        Long expectedAmgArtistId = 10L;
        Long expectedArtistId = 12L;
        String expectedName = "Duke Silver";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("http://localhost:8080/v1/user/1/artists/favourites")
                .content(TestUtils.getObjectAsString(new Artist.ArtistBuilder()
                        .amgArtistId(expectedAmgArtistId)
                        .artistId(expectedArtistId)
                        .artistName(expectedName)
                        .build()))
                .contentType(MediaType.APPLICATION_JSON);


        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());


        //Attached favourite to existing user, now retrieve it
        RequestBuilder requestBuilder2 = MockMvcRequestBuilders
                .get("http://localhost:8080/v1/user/1/artists/favourites");

        String response = mockMvc.perform(requestBuilder2)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(Charsets.UTF_8);

        ApplicationArtistResponse artistResponse = TestUtils.getStringAsObject(response, ApplicationArtistResponse.class);

        // Assert on the second favourite artist, first was inserted as part of data.sql
        assertEquals(2, artistResponse.getArtists().size());
        assertEquals(expectedName, artistResponse.getArtists().get(1).getArtistName());
        assertEquals(expectedArtistId, artistResponse.getArtists().get(1).getArtistId());
        assertEquals(expectedAmgArtistId, artistResponse.getArtists().get(1).getAmgArtistId());


    }

}
