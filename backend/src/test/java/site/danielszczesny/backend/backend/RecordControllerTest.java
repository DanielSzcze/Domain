package site.danielszczesny.backend.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.DispatcherServlet;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
}
