package com.example.warehousespringlearn.service;

import com.example.warehousespringlearn.entity.Client;
import com.example.warehousespringlearn.entity.Measurement;
import com.example.warehousespringlearn.payload.Result;
import com.example.warehousespringlearn.repository.CategoryRepository;
import com.example.warehousespringlearn.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private OtherService otherService;

    public Page<Client> getAllClients(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return clientRepository.findAll(pageable);
    }

    public Client getClientById(Integer id){
        Optional<Client> byId = clientRepository.findById(id);
        if (byId.isPresent()){
            return byId.get();
        }
        else return null;

    }

    public Result addClient(Client client){
        String phoneNumber = otherService.phoneNumberToSave(client.getPhoneNumber());
        if (phoneNumber!=null) {
            client.setPhoneNumber(phoneNumber);
            if (clientRepository.existsByPhoneNumber(client.getPhoneNumber())) {
                return new Result("Supplier is exist", false);
            }
            else {
                clientRepository.save(client);
                return new Result("Client is added", true);
            }

        }
        else return new Result("phone number is incorrect", false);
    }

    public Result editClient(Integer id, Client client){
        Optional<Client> byId = clientRepository.findById(id);
        if (byId.isPresent()){
            Client client1 = byId.get();
            if (client.getPhoneNumber()!=null){
                if (!client.getPhoneNumber().equals(client1.getPhoneNumber())){
                    if (clientRepository.existsByPhoneNumber(client.getPhoneNumber())){
                        return new Result("client with this phone number is exist", false);
                    }
                    else client1.setPhoneNumber(otherService.phoneNumberToSave(client.getPhoneNumber()));
                }
            }
            if (client.getName()!=null) {
                client1.setName(client.getName());
            }
            if (client.isStatus()!=client1.isStatus()){
                client1.setStatus(client.isStatus());
            }
            clientRepository.save(client1);
            return new Result("edited", true);
        }
        else return new Result("id is not found", false);
    }

    public Result deleteClientById(Integer id){
        Optional<Client> byId = clientRepository.findById(id);
        if (byId.isPresent()){
            clientRepository.deleteById(id);
            return new Result("deleted", true);
        }
        else return new Result("id is not exist", false);

    }
}
