package org.example.desafiodiopadroes.controller;

import org.example.desafiodiopadroes.entities.Client;
import org.example.desafiodiopadroes.entities.Enderess;
import org.example.desafiodiopadroes.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/list-all")
    public ResponseEntity<List<Client>> listAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @PostMapping("/save-client")
    public ResponseEntity<Client> save(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.save(client));
    }

    @PutMapping(value = "edit-client/{id}")
    public ResponseEntity<Client> edit(@RequestBody Client client, @PathVariable String id, @RequestBody Enderess enderess) {
        Optional<Client> updatedClient = clientService.update(client, id, enderess);
        if (updatedClient.isPresent()) {
            return ResponseEntity.ok(updatedClient.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        clientService.delete(id);
        return ResponseEntity.ok("Deletado com sucesso.");
    }
}
