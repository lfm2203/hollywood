package br.com.lfm.hollywood;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = HollywoodApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class VencedoresTests {
    @Autowired
    MockMvc mockMvc;

    @Test
    void testarProdutorComMaiorIntervalo() throws Exception {
        mockMvc.perform(get("/vencedores/listarPorIntervalo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.max.length()").value(2))
                .andExpect(jsonPath("$.max[0].producer").value("Matthew Vaughn"))
                .andExpect(jsonPath("$.max[0].interval").value(22))
                .andExpect(jsonPath("$.max[0].previousWin").value(1980))
                .andExpect(jsonPath("$.max[0].followingWin").value(2002))
                .andExpect(jsonPath("$.max[1].producer").value("Matthew Vaughn"))
                .andExpect(jsonPath("$.max[1].interval").value(22))
                .andExpect(jsonPath("$.max[1].previousWin").value(2015))
                .andExpect(jsonPath("$.max[1].followingWin").value(2037));
    }

    @Test
    void testarProdutorComMenorIntervalo() throws Exception {
        mockMvc.perform(get("/vencedores/listarPorIntervalo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.max.length()").value(2))
                .andExpect(jsonPath("$.min[0].producer").value("Joel Silver"))
                .andExpect(jsonPath("$.min[0].interval").value(1))
                .andExpect(jsonPath("$.min[0].previousWin").value(1990))
                .andExpect(jsonPath("$.min[0].followingWin").value(1991))
                .andExpect(jsonPath("$.min[1].producer").value("Matthew Vaughn"))
                .andExpect(jsonPath("$.min[1].interval").value(1))
                .andExpect(jsonPath("$.min[1].previousWin").value(2002))
                .andExpect(jsonPath("$.min[1].followingWin").value(2003));
    }
}
