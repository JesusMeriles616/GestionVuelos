package com.gestion.microservice_flight_management.repositoty;

import com.gestion.microservice_flight_management.model.Flight;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
}
