package com.archisacademy.morent.controllers;


import com.archisacademy.morent.dtos.requests.MaintenanceRequest;
import com.archisacademy.morent.dtos.responses.MaintenanceResponse;
import com.archisacademy.morent.services.abstracts.MaintenanceService;
import com.archisacademy.morent.services.abstracts.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/maintenance")
@RequiredArgsConstructor
public class MaintenanceController {

    private final VehicleService vehicleService;
    private final MaintenanceService maintenanceService;


    @PostMapping("/add")
    public ResponseEntity<MaintenanceResponse> addMaintenance(@RequestBody MaintenanceRequest maintenanceRequest){
        MaintenanceResponse response = maintenanceService.addMaintenance(maintenanceRequest);
        return ResponseEntity.ok(response);
    }
}
