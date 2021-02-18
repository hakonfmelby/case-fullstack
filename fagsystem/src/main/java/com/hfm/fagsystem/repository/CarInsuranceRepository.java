package com.hfm.fagsystem.repository;

import com.hfm.fagsystem.model.CarInsurance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarInsuranceRepository extends CrudRepository<CarInsurance, String> {
}

