package org.example.desafiodiopadroes;

import org.example.desafiodiopadroes.controller.ClientController;
import org.example.desafiodiopadroes.entities.Client;
import org.example.desafiodiopadroes.entities.Enderess;
import org.example.desafiodiopadroes.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ClientControllerTeste {

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEditClient() {
        Enderess enderessNew = new Enderess("", "", "", 0);
        Client client = new Client("", "", "", "", enderessNew);
        client.setId("1");
        client.setNome("Updated Name");

        when(clientService.update(any(Client.class), any(String.class), any(Enderess.class))).thenReturn(Optional.of(client));

        ResponseEntity<Client> response = clientController.edit(client, "1", enderessNew);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(client, response.getBody());
    }

    @Test
    void testEditClient_NotFound() {
        Enderess enderessNew = new Enderess("", "", "", 0);
        Client client = new Client("", "", "", "", enderessNew);

        when(clientService.update(any(Client.class), any(String.class), any(Enderess.class))).thenReturn(Optional.empty());

        ResponseEntity<Client> response = clientController.edit(client, "1", enderessNew);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
