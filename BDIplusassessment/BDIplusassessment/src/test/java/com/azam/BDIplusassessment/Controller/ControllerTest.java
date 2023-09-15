package com.azam.BDIplusassessment.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.net.URI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriComponentsBuilder;

import com.azam.BDIplusassessment.BdIplusassessmentApplication;
import com.azam.BDIplusassessment.Models.Task;
import com.azam.BDIplusassessment.Services.TaskServices;

@SpringBootTest(classes = { BdIplusassessmentApplication.class })
@AutoConfigureMockMvc
public class ControllerTest {

    @MockBean
    private TaskServices service;

    @InjectMocks
    private TaskController taskController;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {

        MockitoAnnotations.openMocks(this);

        mvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }

    @Test
    public void correctInputResponse() throws Exception {

        Task taskResponse = new Task(6l, "task 6", "describe the task", "2021-01-07", "azam");

        when(service
                .getTaskById(any(Long.class)))
                .thenReturn(taskResponse);

        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor
                .forClass(Long.class);

        URI uri = UriComponentsBuilder
                .fromPath("/v1/task/{id}")
                .build(6);

        MockHttpServletResponse response = mvc.perform(get(uri.toString())).andReturn()
                .getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        verify(service, times(1))
                .getTaskById(argumentCaptor.capture());

        assertEquals(6, argumentCaptor.getValue().longValue());
        String expectedResponse = "{\"id\":6,\"title\":\"task 6\",\"description\":\"describe the task\",\"date\":\"2021-01-07\",\"owner\":\"azam\"}" ;
        assertEquals(expectedResponse,  response.getContentAsString());
    }
}
