package com.example.warehousespringlearn.service;

import com.example.warehousespringlearn.entity.User;
import com.example.warehousespringlearn.entity.Warehouse;
import com.example.warehousespringlearn.payload.Result;
import com.example.warehousespringlearn.payload.UserDto;
import com.example.warehousespringlearn.repository.UserRepository;
import com.example.warehousespringlearn.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private UserRepository userRepository;
    private WarehouseRepository warehouseRepository;
    private OtherService otherService;

    @Autowired
    public UserService(UserRepository userRepository,
                       WarehouseRepository warehouseRepository,
                       OtherService otherService) {
        this.userRepository = userRepository;
        this.warehouseRepository = warehouseRepository;
        this.otherService = otherService;
    }

    public Page<User> getAllUser(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return userRepository.findAll(pageable);
    }

    public User getUserById(Integer id){
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()){
            return byId.get();
        }
        else return null;

    }

    public Result addUser(UserDto userDto){
        String phoneNumber = otherService.phoneNumberToSave(userDto.getPhoneNumber());
        if (phoneNumber==null){
            return new Result("please check phoe number", false);
        }
        if (userRepository.existsByPhoneNumber(phoneNumber)){
            return new Result("user is exist", false);
        }
        Set<Integer> warehouseIds = userDto.getWarehousesIds();
        Set<Warehouse> warehouses = new HashSet<>();
        for (Integer warehouseId : warehouseIds) {
            Optional<Warehouse> byId = warehouseRepository.findById(warehouseId);
            if (byId.isPresent()){
                warehouses.add(byId.get());
            }
            else return new Result("warehouse with "+warehouseId+" id is not exist", false);

        }
        User user = new User();
        user.setPhoneNumber(otherService.phoneNumberToSave(userDto.getPhoneNumber()));
        user.setCode(userDto.getCode());
        user.setActive(userDto.isActive());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setWarehouses(warehouses);
        userRepository.save(user);
        return new Result("user is added", true);
    }

    public Result editUser(Integer id, UserDto userDto){
        String phoneNumber = otherService.phoneNumberToSave(userDto.getPhoneNumber());
        if (phoneNumber==null){
            return new Result("check phone number", false);
        }
        Optional<User> userById = userRepository.findById(id);
        if (!userById.isPresent()){
            return new Result("id is not found", false);
        }
        User user = userById.get();

        if (userDto.getPhoneNumber()!=null&&
                (!phoneNumber.equals(user.getPhoneNumber()))){
            if (userRepository.existsByPhoneNumber(phoneNumber)){
                return new Result("phone number is exist", false);
            }
            else user.setPhoneNumber(otherService.phoneNumberToSave(userDto.getPhoneNumber()));
        }

        if (userDto.getWarehousesIds()!=null) {
            Set<Integer> warehouseIds = userDto.getWarehousesIds();
            Set<Warehouse> warehouses = new HashSet<>();
            for (Integer warehouseId : warehouseIds) {
                Optional<Warehouse> byId = warehouseRepository.findById(warehouseId);
                if (byId.isPresent()) {
                    warehouses.add(byId.get());
                } else return new Result("warehouse with " + warehouseId + " id is not exist", false);

            }
            user.setWarehouses(warehouses);
        }

        if (userDto.getFirstName()!=null){
            user.setFirstName(userDto.getFirstName());
        }
        if (userDto.getLastName()!=null){
            user.setLastName(userDto.getLastName());
        }
        if (userDto.getPassword()!=null){
            user.setPassword(userDto.getPassword());
        }

        if (userDto.getCode()!=null){
            user.setCode(userDto.getCode());
        }
        userRepository.save(user);
        return new Result("user is edited", true);
    }

    public Result deleteUserById(Integer id){
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()){
            userRepository.deleteById(id);
            return new Result("deleted", true);
        }
        else return new Result("id is not exist", false);

    }
}
