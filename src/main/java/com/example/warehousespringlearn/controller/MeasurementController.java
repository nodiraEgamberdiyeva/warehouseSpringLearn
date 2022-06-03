package com.example.warehousespringlearn.controller;

import com.example.warehousespringlearn.entity.Category;
import com.example.warehousespringlearn.entity.Measurement;
import com.example.warehousespringlearn.payload.CategoryDto;
import com.example.warehousespringlearn.payload.Result;
import com.example.warehousespringlearn.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    @Autowired
    private MeasurementService measurementService;

    @GetMapping
    public Page<Measurement> getAllMeasurements(@RequestParam int page){
        return measurementService.getAllMeasurement(page);
    }

    @GetMapping("/{id}")
    public Measurement getMeasurementById(@PathVariable Integer id){
        return measurementService.getMeasurementById(id);
    }

    @PostMapping
    public Result addMeasurement(@RequestBody Measurement measurement){
        return measurementService.addMeasurement(measurement);
    }

    @PutMapping("/{id}")
    public Result editMeasurement(@PathVariable Integer id, @RequestBody Measurement measurement){
        return measurementService.editMeasurement(id,measurement);
    }

    @DeleteMapping("/{id}")
    public Result deleteMeasurement(@PathVariable Integer id){
        return measurementService.deleteMeasurementById(id);
    }
}
