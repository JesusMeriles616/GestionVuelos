package com.gestion.microservice_flight_management.controller;

import com.gestion.microservice_flight_management.model.Flight;
import com.gestion.microservice_flight_management.model.FlightStatus;
import com.gestion.microservice_flight_management.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Integer id) {
        return ResponseEntity.ok(flightService.getFlightById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlightStatus(@PathVariable Integer id, @RequestParam String status) {
        return ResponseEntity.ok(flightService.updateFlightStatus(id, FlightStatus.valueOf(status)));
    }
}
