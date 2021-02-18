package com.hfm.rest;

import com.hfm.model.CarInsurance;
import com.hfm.model.CarInsuranceMessage;
import com.hfm.service.CarInsuranceService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;

import javax.validation.Valid;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Application;

@Validated
@Controller("/api/carInsurance")
public class CarInsuranceController extends Application {
    final private CarInsuranceService service = new CarInsuranceService();

    @PUT()
    public HttpResponse<?> put(@Body @Valid CarInsurance insurance) {
        CarInsuranceMessage message = this.service.saveCarInsurance(insurance);

        if (message == null) {
            return HttpResponse.status(HttpStatus.BAD_REQUEST);
        }

        return HttpResponse.status(HttpStatus.CREATED).body(message);
    }

}
