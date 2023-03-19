package lt.seb.productadvisor.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lt.seb.productadvisor.models.AnswerRequest;
import lt.seb.productadvisor.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductAdvisorControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getSuggestedProducts_return2xxWithSuggestedProductList() throws Exception {
        AnswerRequest request = new AnswerRequest("highIncome", "adult", false);

        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.post("/v1/suggestProducts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonString(request)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();

        List<String> result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<String>>(){});

        assertEquals(4, result.size());
        assertTrue(result.contains(Product.CREDIT_CARD.toString()));
        assertTrue(result.contains(Product.GOLD_CREDIT_CARD.toString()));
        assertTrue(result.contains(Product.CURRENT_ACCOUNT.toString()));
        assertTrue(result.contains(Product.CURRENT_ACCOUNT_PLUS.toString()));
    }

    @Test
    void getSuggestedProducts_return4xxBadRequest_whenBadDataGiven() throws Exception {
        AnswerRequest request = new AnswerRequest(null, null, null);

        mvc.perform(MockMvcRequestBuilders.post("/v1/suggestProducts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonString(request)))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn();
    }

    @Test
    void getSuggestedProducts_return4xxBadRequest_whenNoRequestBody() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/v1/suggestProducts"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn();
    }


    private String convertObjectToJsonString(Object object) throws JsonProcessingException {
        ObjectWriter writer = objectMapper.writer().withDefaultPrettyPrinter();
        return writer.writeValueAsString(object);
    }

}
