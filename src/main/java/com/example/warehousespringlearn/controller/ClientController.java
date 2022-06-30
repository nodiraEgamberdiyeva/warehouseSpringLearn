package com.example.warehousespringlearn.controller;

import com.example.warehousespringlearn.entity.Client;
import com.example.warehousespringlearn.entity.Measurement;
import com.example.warehousespringlearn.payload.Result;
import com.example.warehousespringlearn.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PreAuthorize("hasAnyAuthority('READ_ALL_ATTACHMENT', 'ALL')")
    @GetMapping
    public Page<Client> getAllClients(@RequestParam int page){
        return clientService.getAllClients(page);
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Integer id){
        return clientService.getClientById(id);
    }

    @PostMapping
    public Result addClient(@RequestBody Client client){
        return clientService.addClient(client);
    }

    @PutMapping("/{id}")
    public Result editClient(@PathVariable Integer id, @RequestBody Client client){
        return clientService.editClient(id, client);
    }

    @PreAuthorize("hasAnyRole('DIRECTOR')")
    @DeleteMapping("/{id}")
    public Result deleteMeasurement(@PathVariable Integer id){
        return clientService.deleteClientById(id);
    }
}
