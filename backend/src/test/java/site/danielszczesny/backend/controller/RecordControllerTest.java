package site.danielszczesny.backend.controller;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class RecordControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testGetMainEndpointStatus() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetIndexBody() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(content().string(containsString("<a href=\"/lolapp\">")))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetIncomeTypes() throws Exception {
        mockMvc.perform(get("/tf/getIncomeTypes"))
                .andExpect(content()
                        .json("{\"IncomeTypes\": [\"EMPTY\",\"SALARY\",\"OVERTIME\",\"INVESTMENTS\",\"LOAN\",\"OTHER\"],\"ChargeTypes\": [\"EMPTY\",\"BILL\",\"FOOD\",\"GOODS\",\"CREDIT\",\"SERVICES\",\"RECREATION\"]}"))
                        .andExpect(status().isOk())
                        .andExpect(header().string("Content-Length", "155"));
    }

    @Test
    public void testGetPeriodTypes() throws Exception {
        mockMvc.perform(get("/tf/getPeriodTypes"))
                .andExpect(content().string(startsWith("[")))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @ValueSource(strings = {"{\"username\" : \"test\"," +
            "\"type\" : \"BILL\"," +
            "\"amount\" : \"5.5\"," +
            "\"time\" : \"ONCE\"}","{\"username\" : \"test123\"," +
            "\"type\" : \"SALARY\"," +
            "\"amount\" : \"7.5\"," +
            "\"time\" : \"ONCE\"}"})
    public void testCreateRecord(String json) throws Exception {
        mockMvc.perform(post("/tf/createRecord")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @ParameterizedTest
    @After("testCreateRecord")
    @ValueSource(strings = {"test", "test123", "test321t"})
    public void testGetRecords(String username) throws Exception {
        mockMvc.perform(get("/tf/" + username + "/getRecords"))
                .andExpect(content().string(notNullValue()))
                .andExpect(content().string(startsWith("[{")))
                .andExpect(content().string(endsWith("}]")));
    }
}
