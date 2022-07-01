package com.bank.clientservice.modules.resource;

import com.bank.clientservice.TestUtils;
import com.bank.clientservice.modules.ClientTestHelper;
import com.bank.clientservice.modules.dto.ClientRequestDTO;
import com.bank.clientservice.modules.entity.Client;
import com.bank.clientservice.modules.repository.ClientRepository;
import com.bank.clientservice.modules.service.ClientService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ClientResourceIntegrationTest {

    private static final String BASE_URI = "/clients";

    MockMvc mockMvc;

    @Autowired
    ClientService clientService;

    @Autowired
    ClientRepository clientRepository;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(
            new ClientResource(clientService, TestUtils.modelParser())).build();
    }

    @AfterEach
    void tearDown(){
        clientRepository.deleteAll();
    }

    @Test
    void shouldCreateClient_MustPass() throws Exception {
        ClientRequestDTO clientRequestDTO = ClientTestHelper.crateClient();
        mockMvc.perform(
                post(BASE_URI)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(TestUtils.serializeAsBytes(clientRequestDTO)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value(clientRequestDTO.getName()))
                .andExpect(jsonPath("$.document").value(clientRequestDTO.getDocument()))
                .andExpect(jsonPath("$.birthday.[0]").value(clientRequestDTO.getBirthday().getYear()))
                .andExpect(jsonPath("$.birthday.[1]").value(clientRequestDTO.getBirthday().getMonthValue()))
                .andExpect(jsonPath("$.birthday.[2]").value(clientRequestDTO.getBirthday().getDayOfMonth()))
        ;
         assertEquals(1, clientRepository.count());
    }

    @Test
    void findOne_MustPass() throws Exception {
        Client client = TestUtils.modelParser().map(ClientTestHelper.crateClient(), Client.class);
        clientRepository.saveAndFlush(client);

        mockMvc.perform(
                get(BASE_URI+"/{id}", client.getId()).accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(client.getId()))
                .andExpect(jsonPath("$.name").value(client.getName()))
                .andExpect(jsonPath("$.document").value(client.getDocument()))
                .andExpect(jsonPath("$.birthday.[0]").value(client.getBirthday().getYear()))
                .andExpect(jsonPath("$.birthday.[1]").value(client.getBirthday().getMonthValue()))
                .andExpect(jsonPath("$.birthday.[2]").value(client.getBirthday().getDayOfMonth()));
    }

}