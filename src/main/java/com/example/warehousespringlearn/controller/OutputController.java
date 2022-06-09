package com.example.warehousespringlearn.controller;

import com.example.warehousespringlearn.entity.Input;
import com.example.warehousespringlearn.entity.Output;
import com.example.warehousespringlearn.payload.InputDto;
import com.example.warehousespringlearn.payload.OutputDto;
import com.example.warehousespringlearn.payload.Result;
import com.example.warehousespringlearn.service.OutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/output")
public class OutputController {
    private OutputService outputService;

    @Autowired
    public OutputController(OutputService outputService) {
        this.outputService = outputService;
    }

    @GetMapping
    public Page<Output> getAllOutputs(@RequestParam int page){
        return outputService.getAllOutputs(page);
    }

    @GetMapping("/{id}")
    public Output getOutputById(@PathVariable Integer id){
        return  outputService.getOutputById(id);
    }

    @PostMapping
    public Result addOutput(@RequestBody OutputDto inputDto){
        return  outputService.addOutput(inputDto);
    }

    @PutMapping("/{id}")
    public Result editOutput(@PathVariable Integer id, @RequestBody OutputDto inputDto){
        return  outputService.editOutput(id, inputDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteOutput(@PathVariable Integer id){
        return  outputService.deleteOutputById(id);
    }
}
