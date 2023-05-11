package com.example;

import com.example.result.Result;
import com.example.result.ResultController;
import com.example.result.ResultRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class ResultControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ResultRepository resultRepository;

    @InjectMocks
    private ResultController resultController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(resultController).build();
    }

    @Test
    public void addNewResultTest() throws Exception {
        // Set up dummy data
        Result result = new Result();
        result.setId(1);
        result.setScore("90");
        result.setTotalMarks("100");
        result.setTotalQuestion("10");

        when(resultRepository.save(result)).thenReturn(result);

        // Send POST request
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/result")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\n"
                        + "    \"score\": \"90\",\n"
                        + "    \"totalMarks\": \"100\",\n"
                        + "    \"totalQuestion\": \"10\"\n"
                        + "}")).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
    }
}
