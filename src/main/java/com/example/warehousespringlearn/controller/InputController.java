package com.example.warehousespringlearn.controller;

import com.example.warehousespringlearn.entity.Client;
import com.example.warehousespringlearn.entity.Input;
import com.example.warehousespringlearn.payload.InputDto;
import com.example.warehousespringlearn.payload.Result;
import com.example.warehousespringlearn.service.InputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/input")
public class InputController {
    @Autowired
    private InputService inputService;

    @GetMapping
    public Page<Input> getAllInputs(@RequestParam int page){
        return inputService.getAllInput(page);
    }

    @GetMapping("/{id}")
    public Input getInputById(@PathVariable Integer id){
        return inputService.getInputById(id);
    }

    @PostMapping
    public Result addInput(@RequestBody InputDto inputDto){
        return inputService.addInput(inputDto);
    }

    @PutMapping("/{id}")
    public Result editInput(@PathVariable Integer id, @RequestBody InputDto inputDto){
        return inputService.editInput(id, inputDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteInput(@PathVariable Integer id){
        return inputService.deleteInputById(id);
    }
}
