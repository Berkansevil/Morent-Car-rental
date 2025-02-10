package com.archisacademy.morent.services.abstracts;

import com.archisacademy.morent.dtos.requests.VehicleMaintenanceRequest;
import com.archisacademy.morent.dtos.requests.VehicleRequest;
import com.archisacademy.morent.dtos.responses.*;
import com.archisacademy.morent.dtos.requests.VehicleUpdateRequest;

import java.util.List;
import java.util.UUID;
import java.time.LocalDate;


public interface VehicleService {
    VehicleResponse addVehicle(VehicleRequest vehicleRequest);

    VehicleUpdateResponse updateVehicle(UUID vehicleId, VehicleUpdateRequest vehicleUpdateRequest);

    List<VehicleFilterResponse> getVehiclesByType(String type);

    VehicleDetails getVehicleById(UUID vehicleId);

    List<SearchVehicleResponse> searchVehicles(String location, LocalDate startDate, LocalDate endDate, String vehicleType);

    List<VehicleReviewsResponse> getVehicleReviews(UUID vehicleId);
    VehicleAvailabilityResponse isVehicleAvailable(UUID vehicleId);

    String vehicleDeleteSoft(Long vehicleId);
    VehicleUpdateResponse updateMaintenanceStatus(UUID vehicleId, VehicleMaintenanceRequest vehicleMaintenanceRequest);

}
