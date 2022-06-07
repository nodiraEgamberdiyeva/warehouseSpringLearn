package com.example.warehousespringlearn.service;

import org.springframework.stereotype.Service;

@Service
public class OtherService {
    public String phoneNumberToSave(String phoneNumber){
        if (phoneNumber.length()==13){
            return phoneNumber;
        }
        if ( phoneNumber.length()==12){
            return "+"+phoneNumber;
        }
        if (phoneNumber.length()==9){
            return "+998"+phoneNumber;
        }
        else return null;

    }
}
