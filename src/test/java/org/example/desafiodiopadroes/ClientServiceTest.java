package org.example.desafiodiopadroes;

import org.example.desafiodiopadroes.entities.Client;
import org.example.desafiodiopadroes.entities.Enderess;
import org.example.desafiodiopadroes.repository.ClientRepository;
import org.example.desafiodiopadroes.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveClient() {
        Enderess enderessNew = new Enderess("", "", "", 0);
        Client client = new Client("", "", "", "", enderessNew);
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Client savedClient = clientService.save(client);
        assertNotNull(savedClient);
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void testUpdateClient() {
        Enderess enderessNew = new Enderess("", "", "", 0);
        Client client = new Client("", "", "", "", enderessNew);
        client.setId("1");
        client.setNome("Updated Name");

        when(clientRepository.findById("1")).thenReturn(Optional.of(client));
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Optional<Client> updatedClient = clientService.update(client, "1", enderessNew);
        assertTrue(updatedClient.isPresent());
        assertEquals("Updated Name", updatedClient.get().getNome());
        verify(clientRepository, times(1)).findById("1");
        verify(clientRepository, times(1)).save(client);
    }
}
