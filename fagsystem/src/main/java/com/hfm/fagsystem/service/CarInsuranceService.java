package com.hfm.fagsystem.service;

import com.hfm.fagsystem.model.CarInsurance;
import com.hfm.fagsystem.repository.CarInsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CarInsuranceService {

    /*
    @Autowired
    CarInsuranceRepository carInsuranceRepository;

     */

    public Optional<CarInsurance> find(String id) {
        return Optional.empty();
        // return carInsuranceRepository.findById(id);
    }

    public CarInsurance update(CarInsurance insurance) {
        insurance.setId(UUID.randomUUID().toString());
        insurance.setStatus("PENDING");
        return insurance;
        // return carInsuranceRepository.save(insurance);
    }

    public void delete(String id) {
        // carInsuranceRepository.deleteById(id);
    }
}
