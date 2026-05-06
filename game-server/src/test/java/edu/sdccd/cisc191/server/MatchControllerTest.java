package edu.sdccd.cisc191.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sdccd.cisc191.server.dto.JoinMatchRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MatchControllerTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @Test
    void joinMatchReturnsJsonMatch() throws Exception {
        JoinMatchRequest request = new JoinMatchRequest("Ada", "Hard", true);

        mockMvc.perform(post("/api/matches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.matchId", not(blankOrNullString())))
                .andExpect(jsonPath("$.playerName").value("Ada"))
                .andExpect(jsonPath("$.opponentName").value("Bot (Hard)"))
                .andExpect(jsonPath("$.difficulty").value("Hard"))
                .andExpect(jsonPath("$.ranked").value(true));
    }

    @Test
    void historyReturnsJsonArray() throws Exception {
        mockMvc.perform(get("/api/matches/history").param("playerName", "Ada"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.matches[0]").exists());
    }
}
