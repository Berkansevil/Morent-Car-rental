package com.archisacademy.morent.services.abstracts;

import com.archisacademy.morent.dtos.requests.MaintenanceRequest;
import com.archisacademy.morent.dtos.responses.MaintenanceListResponse;
import com.archisacademy.morent.dtos.responses.MaintenanceResponse;

import java.util.List;
import java.util.UUID;

public interface MaintenanceService {

    MaintenanceResponse addMaintenance(MaintenanceRequest maintenanceRequest);

    List<MaintenanceListResponse> vehicleMaintenanceList(UUID vehicleId);
}
