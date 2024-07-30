package org.example.desafiodiopadroes.services;

import org.example.desafiodiopadroes.entities.Client;
import org.example.desafiodiopadroes.entities.Enderess;
import org.example.desafiodiopadroes.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public Client save(Client client) {
        return clientRepository.save(client);
    }
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
    public Client findById(String id) {
        return clientRepository.findById(id).orElse(null);
    }
    public void delete(String id) {
        clientRepository.deleteById(id);
    }

    public Optional<Client> update(Client client, String id, Enderess enderess) {
        return Optional.ofNullable(clientRepository.findById(id).map(c -> {
                    c.setNome(client.getNome());
                    c.setEmail(client.getEmail());
                    c.setCpf(client.getCpf());
                    c.setEnderess(enderess);
                    return clientRepository.save(c);
                })
                .orElseThrow(() -> new RuntimeException("Não é possível localizar os dados")));
    }
}
