package com.gestion.microservice_flight_management.service;

import com.gestion.microservice_flight_management.model.Flight;
import com.gestion.microservice_flight_management.model.FlightStatus;
import com.gestion.microservice_flight_management.repositoty.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.concurrent.CompletableFuture;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public Flight getFlightById(Integer id) {
        return flightRepository.findById(id).orElseThrow(() -> new RuntimeException("Flight not found"));
    }

    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    public FlightService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public Flight updateFlightStatus(Integer id, FlightStatus status) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Flight not found"));
        flight.setStatus(status);
        Flight updatedFlight = flightRepository.save(flight);
        System.out.println("flightStatusUpdate"+ updatedFlight.getFlightId().toString());
        System.out.println(updatedFlight.getStatus().toString());
        // Send Kafka message asynchronously
        String jsonValue = String.format("\"%s\"", updatedFlight.getStatus().toString());
        kafkaTemplate.send("flightStatusUpdate", updatedFlight.getFlightId().toString(), jsonValue);


        return updatedFlight;
    }






}
