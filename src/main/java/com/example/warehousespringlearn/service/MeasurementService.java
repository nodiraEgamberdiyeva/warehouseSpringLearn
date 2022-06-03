package com.example.warehousespringlearn.service;

import com.example.warehousespringlearn.entity.Measurement;
import com.example.warehousespringlearn.payload.Result;
import com.example.warehousespringlearn.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MeasurementService {
    @Autowired
    private MeasurementRepository measurementRepository;

    public Page<Measurement> getAllMeasurement(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return measurementRepository.findAll(pageable);
    }

    public Measurement getMeasurementById(Integer id){
        Optional<Measurement> byId = measurementRepository.findById(id);
        if (byId.isPresent()){
            return byId.get();
        }
        else return null;

    }

    public Result addMeasurement(Measurement measurement){
        if (measurementRepository.existsByName(measurement.getName())) {
            return new Result("measurement is exist", false);
        }
        else {
            measurementRepository.save(measurement);
            return new Result("measurement is added", true);
        }
    }

    public Result editMeasurement(Integer id, Measurement measurement){
        Optional<Measurement> byId = measurementRepository.findById(id);
        if (byId.isPresent()){
            Measurement measurement1 = byId.get();
            measurement1.setName(measurement.getName());
            measurement1.setStatus(measurement.isStatus());
            measurementRepository.save(measurement1);
            return new Result("edited", true);
        }
        else return new Result("id is not found", false);
    }

    public Result deleteMeasurementById(Integer id){
        Optional<Measurement> byId = measurementRepository.findById(id);
        if (byId.isPresent()){
            measurementRepository.deleteById(id);
            return new Result("deleted", true);
        }
        else return new Result("id is not exist", false);

    }


}
