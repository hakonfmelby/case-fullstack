package com.hfm.fagsystem.controller;

import com.hfm.fagsystem.model.Client;
import com.hfm.fagsystem.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/api/client")
@CrossOrigin
@RestController
public class ClientController {

    @Autowired
    ClientService clientService;

    @PutMapping
    @ResponseBody
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public Client update(@RequestBody Client client) {
        return clientService.update(client);
    }

    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.FOUND)
    public Optional<Client> find(@RequestParam String idNumber) {
        return clientService.find(idNumber);
    }

    @DeleteMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam String idNumber) {
        clientService.delete(idNumber);
    }

}
