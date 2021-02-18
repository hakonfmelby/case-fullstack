package com.hfm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hfm.model.CarInsurance;
import com.hfm.model.CarInsuranceMessage;
import com.hfm.model.ClientMessage;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class CarInsuranceService {

    private static final Logger logger
            = LoggerFactory.getLogger(CarInsuranceService.class);

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();

    public CarInsuranceMessage saveCarInsurance(CarInsurance insurance) {
        String clientId = putClient(insurance);

        if (clientId == null) {
            // fallback handling required
            return null;
        }

        CarInsuranceMessage insuranceMessage = putInsurance(insurance);

        if (insuranceMessage == null) {
            // fallback handling required
            return null;
        }

        // Request til brevtjeneste her og oppdatering av forsikringstatus til "SENT"
        insuranceMessage.setStatus("SENT");

        return insuranceMessage;
    }

    private String putClient(CarInsurance insurance) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(insurance);

            RequestBody body = RequestBody.create(JSON, jsonString);
            Request request = new Request.Builder()
                    .url("http://localhost:7081/api/client")
                    .put(body)
                    .build();

            Response response = client.newCall(request).execute();

            if (response.code() == 201) {
                return mapper.readValue(response.body().string(), ClientMessage.class).id;
            }

            return null;
        } catch (IOException e) {
            logger.error("Failed client PUT request to Fagsystem: " + e.toString());
        }

        return null;
    }

    private CarInsuranceMessage putInsurance(CarInsurance insurance) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(insurance);

            RequestBody body = RequestBody.create(JSON, jsonString);
            Request request = new Request.Builder()
                    .url("http://localhost:7081/api/carInsurance")
                    .put(body)
                    .build();

            Response response = client.newCall(request).execute();

            if (response.code() == 201) {
                return mapper.readValue(response.body().string(), CarInsuranceMessage.class);
            }

            return null;
        } catch (IOException e) {
            logger.error("Failed insurance PUT request to Fagsystem: " + e.toString());
        }

        return null;
    }
}
