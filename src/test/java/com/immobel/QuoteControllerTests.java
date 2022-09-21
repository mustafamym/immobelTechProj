package com.immobel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.immobel.dto.QuoteSaveDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * integration test
 * **/
public class QuoteControllerTests extends ApplicationTests {

    public static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .registerModule(new JavaTimeModule());
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void createQuoteApiTest() throws Exception {

        QuoteSaveDto dto = new QuoteSaveDto();
        dto.setAuthor("ames gosselin");
        dto.setText("Java");
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/quotes")

                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)));
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.author").value(dto.getAuthor()))
                .andExpect(jsonPath("$.text").value(dto.getText()))
                .andReturn();

    }

}
