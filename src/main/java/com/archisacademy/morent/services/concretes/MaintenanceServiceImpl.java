package com.archisacademy.morent.services.concretes;

import com.archisacademy.morent.dtos.requests.MaintenanceRequest;
import com.archisacademy.morent.dtos.responses.MaintenanceListResponse;
import com.archisacademy.morent.dtos.responses.MaintenanceResponse;
import com.archisacademy.morent.entities.Maintenance;
import com.archisacademy.morent.entities.Vehicle;
import com.archisacademy.morent.exceptions.VehicleNotFoundException;
import com.archisacademy.morent.repositories.MaintenanceRepository;
import com.archisacademy.morent.repositories.VehicleRepository;
import com.archisacademy.morent.services.abstracts.MaintenanceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements MaintenanceService {

    private final VehicleRepository vehicleRepository;
    private final ModelMapper modelMapper;
    private final MaintenanceRepository maintenanceRepository;


    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public MaintenanceResponse addMaintenance(MaintenanceRequest maintenanceRequest) {

        Optional<Vehicle> isVehicleHere = vehicleRepository.findByVehicleId(maintenanceRequest.getVehicleId());
        if (isVehicleHere.isPresent()) {
            Maintenance maintenance = new Maintenance();
            maintenance.setVehicle(isVehicleHere.get());
            maintenance.setDetails(maintenanceRequest.getDetails());
            maintenance.setStartDate(maintenanceRequest.getStartDate());
            maintenance.setEndDate(maintenanceRequest.getEndDate());

            Maintenance save = maintenanceRepository.save(maintenance);
            MaintenanceResponse maintenanceResponse = modelMapper.map(save, MaintenanceResponse.class);
            maintenanceResponse.setMessage("Maintenance has been added!!!");
            return maintenanceResponse;
        }else {
            throw new VehicleNotFoundException("Vehicle can not be found");
        }
    }

    @Override
    public List<MaintenanceListResponse> vehicleMaintenanceList(UUID vehicleId){
        Vehicle vehicle = vehicleRepository.findByVehicleId(vehicleId).orElseThrow(()-> new VehicleNotFoundException("Vehicle not found!!!"));

        List<Maintenance> maintenances = vehicle.getMaintenances();
        if (maintenances.isEmpty()){
            throw new RuntimeException("No maintenance history can be found!!!");
        }
        List<MaintenanceListResponse> maintenanceListResponses = maintenances.stream().map(maintenance -> modelMapper.map(maintenance, MaintenanceListResponse.class)).collect(Collectors.toList());
        return maintenanceListResponses;
    }
}
