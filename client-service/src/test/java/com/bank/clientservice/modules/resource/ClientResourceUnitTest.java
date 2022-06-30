package com.bank.clientservice.modules.resource;

import com.bank.clientservice.TestUtils;
import com.bank.clientservice.modules.ClientTestHelper;
import com.bank.clientservice.modules.dto.ClientRequestDTO;
import com.bank.clientservice.modules.entity.Client;
import com.bank.clientservice.modules.resource.ClientResource;
import com.bank.clientservice.modules.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ClientResourceUnitTest {

    private static final String BASE_URI = "/clients";

    MockMvc mockMvc;
    @Mock
    ClientService clientService;
    private ClientRequestDTO clientRequestDTO;

    @BeforeEach
    void setUpEach(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new ClientResource(clientService, TestUtils.modelParser()))
                .build();
        clientRequestDTO = ClientTestHelper.crateClient();
    }

    @Test
    void shouldCreateClient_MustPass() throws Exception{
        final Client client = TestUtils.modelParser().map(clientRequestDTO, Client.class);
        when(clientService.save(client)).thenReturn(client);
        final String json = TestUtils.objectMapper().writeValueAsString(clientRequestDTO);

        mockMvc.perform(post(BASE_URI).contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(json))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void shouldNotCreateClientWithNullName_MustFail() throws Exception{
        clientRequestDTO.setName(null);
        final String json = TestUtils.objectMapper().writeValueAsString(clientRequestDTO);

        mockMvc.perform(post(BASE_URI).contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(clientService, never()).save(any());
    }

    @Test
    void shouldNotCreateClientWithEmptyName_MustFail() throws Exception{
        clientRequestDTO.setName("");
        final String json = TestUtils.objectMapper().writeValueAsString(clientRequestDTO);

        mockMvc.perform(post(BASE_URI).contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(clientService, never()).save(any());
    }

    @Test
    void shouldNotCreateClientWithNullDocument_MustFail() throws Exception{
        clientRequestDTO.setDocument(null);
        final String json = TestUtils.objectMapper().writeValueAsString(clientRequestDTO);

        mockMvc.perform(post(BASE_URI).contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(clientService, never()).save(any());
    }

    @Test
    void shouldNotCreateClientWithEmptyDocument_MustFail() throws Exception{
        clientRequestDTO.setDocument("");
        final String json = TestUtils.objectMapper().writeValueAsString(clientRequestDTO);

        mockMvc.perform(post(BASE_URI).contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(clientService, never()).save(any());
    }

    @Test
    void shouldNotCreateClientWithNotValidDocument_MustFail() throws Exception{
        clientRequestDTO.setDocument("00012332131");
        final String json = TestUtils.objectMapper().writeValueAsString(clientRequestDTO);

        mockMvc.perform(post(BASE_URI).contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(clientService, never()).save(any());
    }

    @Test
    void shouldNotCreateClientWithoutBirthday_MustFail() throws Exception{
        clientRequestDTO.setBirthday(null);
        final String json = TestUtils.objectMapper().writeValueAsString(clientRequestDTO);

        mockMvc.perform(post(BASE_URI).contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(clientService, never()).save(any());
    }

    @Test
    void shouldFindOne_MustPass() throws Exception{
        final Client client = TestUtils.modelParser().map(clientRequestDTO, Client.class);
        client.setId(1);
        when(clientService.findById(1)).thenReturn(client);

        mockMvc.perform(get(BASE_URI+"/{id}", 1)).
                andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(client.getName()))
                .andExpect(jsonPath("$.document").value(client.getDocument()))
                .andExpect(jsonPath("$.birthday.[0]").value(client.getBirthday().getYear()))
                .andExpect(jsonPath("$.birthday.[1]").value(client.getBirthday().getMonthValue()))
                .andExpect(jsonPath("$.birthday.[2]").value(client.getBirthday().getDayOfMonth()))
        ;
    }

    @Test()
    void shouldReturnNotFoundWhenInvalidIdWasInformed_MustFail() throws Exception{
        when(clientService.findById(5)).thenReturn(null);

        mockMvc.perform(get(BASE_URI+"/{id}", 5))
                .andExpect(status().isNotFound());
    }
}