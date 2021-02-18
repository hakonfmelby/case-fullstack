package com.hfm.fagsystem.controller;

import com.hfm.fagsystem.model.CarInsurance;
import com.hfm.fagsystem.service.CarInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/api/carInsurance")
@RestController
public class CarInsuranceController {

    @Autowired
    CarInsuranceService carInsuranceService;

    @PutMapping
    @ResponseBody
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public CarInsurance update(@RequestBody CarInsurance insurance) {
        return carInsuranceService.update(insurance);
    }

    @GetMapping
    @ResponseBody
    @CrossOrigin
    @ResponseStatus(HttpStatus.FOUND)
    public Optional<CarInsurance> find(@RequestParam String idNumber) {
        return carInsuranceService.find(idNumber);
    }

    @DeleteMapping
    @ResponseBody
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam String idNumber) {
        carInsuranceService.delete(idNumber);
    }
}
