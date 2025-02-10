package com.archisacademy.morent.controllers;

import com.archisacademy.morent.dtos.requests.VehicleRequest;
import com.archisacademy.morent.dtos.responses.*;
import com.archisacademy.morent.dtos.requests.VehicleUpdateRequest;
import com.archisacademy.morent.services.abstracts.MaintenanceService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.archisacademy.morent.services.abstracts.VehicleService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;
    private final MaintenanceService maintenanceService;

    @PostMapping
    public ResponseEntity<VehicleResponse> addVehicle(@Valid @RequestBody VehicleRequest vehicleRequest) {
        VehicleResponse response = vehicleService.addVehicle(vehicleRequest);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/search")
    public ResponseEntity<List<SearchVehicleResponse>> searchVehicles(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(required = false) String vehicleType) {
        List<SearchVehicleResponse> response = vehicleService.searchVehicles(location, startDate, endDate, vehicleType);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<VehicleUpdateResponse> updateVehicle(@PathVariable UUID vehicleId,
                                                               @Valid @RequestBody VehicleUpdateRequest vehicleUpdateRequest) {
        VehicleUpdateResponse response = vehicleService.updateVehicle(vehicleId, vehicleUpdateRequest);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/type/{type}")
    public List<VehicleFilterResponse> getVehiclesByType(@PathVariable String type) {
        return vehicleService.getVehiclesByType(type);
    }
     @GetMapping("/{vehicleId}")
    public ResponseEntity<?> getVehicleById(@PathVariable UUID vehicleId) {
        VehicleDetails vehicleDetails = vehicleService.getVehicleById(vehicleId);
        if (vehicleDetails != null) {
            return ResponseEntity.ok(vehicleDetails);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{vehicleId}/reviews")
    public List<VehicleReviewsResponse> getReviewsByVehicleId(@PathVariable("vehicleId") UUID vehicleId) {
        return vehicleService.getVehicleReviews(vehicleId);
    }

    @GetMapping("/{vehicleId}/availability")
    public ResponseEntity<VehicleAvailabilityResponse> isVehicleAvailable(@PathVariable UUID vehicleId){
        VehicleAvailabilityResponse response = vehicleService.isVehicleAvailable(vehicleId);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/soft-delete/{Id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> softDelete(@PathVariable Long Id) {
        String s = vehicleService.vehicleDeleteSoft(Id);
        return ResponseEntity.ok(s);
    }

    @GetMapping("{vehicleId}/maintenance-history")
    public ResponseEntity<List<MaintenanceListResponse>>vehicleMaintenanceList(@PathVariable UUID vehicleId){
        List<MaintenanceListResponse> responses = maintenanceService.vehicleMaintenanceList(vehicleId);
        return ResponseEntity.ok(responses);
    }
}
