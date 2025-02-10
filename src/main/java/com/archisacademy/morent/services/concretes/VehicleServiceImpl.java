package com.archisacademy.morent.services.concretes;

import com.archisacademy.morent.dtos.requests.VehicleMaintenanceRequest;
import com.archisacademy.morent.dtos.requests.VehicleRequest;
import com.archisacademy.morent.dtos.responses.*;
import com.archisacademy.morent.dtos.requests.VehicleUpdateRequest;
import com.archisacademy.morent.entities.Review;
import com.archisacademy.morent.entities.Vehicle;
import com.archisacademy.morent.exceptions.VehicleNotFoundException;
import com.archisacademy.morent.repositories.VehicleRepository;
import com.archisacademy.morent.services.abstracts.VehicleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.UUID;
import java.util.Optional;



@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ModelMapper modelMapper;

    @Override
    public VehicleResponse addVehicle(VehicleRequest vehicleRequest) {
        Vehicle vehicle = modelMapper.map(vehicleRequest, Vehicle.class);
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return new VehicleResponse("Vehicle added successfully", savedVehicle.getVehicleId());
    }

    @Override
    public List<VehicleFilterResponse> getVehiclesByType(String type) {
        List<Vehicle> vehicles = vehicleRepository.findByTypeIgnoreCase(type);
        return vehicles.stream().
                map(vehicle -> modelMapper.map(vehicle, VehicleFilterResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<SearchVehicleResponse> searchVehicles(String location, LocalDate startDate, LocalDate endDate, String vehicleType) {
        List<Vehicle> vehicles = vehicleRepository.findAll()
                .stream()
                .filter(vehicle -> location == null || vehicle.getLocation().equalsIgnoreCase(location))
                .filter(Vehicle::isAvailability)
                .collect(Collectors.toList());

        if (vehicles.isEmpty()) {
            return List.of();
        }

        return vehicles.stream()
                .map(vehicle -> new SearchVehicleResponse(
                        vehicle.getVehicleId(),
                        vehicle.getMake(),
                        vehicle.getModel(),
                        vehicle.getYear(),
                        vehicle.getLocation(),
                        vehicle.getPricePerDay(),
                        vehicle.getFeatures(),
                        vehicle.isAvailability()
                ))
                .collect(Collectors.toList());
    }


    @Override
    public List<VehicleReviewsResponse> getVehicleReviews(UUID vehicleId) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findByVehicleId(vehicleId);

        if (vehicleOptional.isPresent()) {
            Vehicle vehicle = vehicleOptional.get();
            List<Review> reviews = vehicle.getReviews();
            return reviews.stream()
                    .map(review -> modelMapper.map(review, VehicleReviewsResponse.class))
                    .collect(Collectors.toList());
        }
        return List.of();
    }

    @Override
    public VehicleUpdateResponse updateVehicle(UUID vehicleId, VehicleUpdateRequest vehicleUpdateRequest) {

        Vehicle vehicle = vehicleRepository.findByVehicleId(vehicleId)
                .orElseThrow(() -> new VehicleNotFoundException("No vehicle to update found!"));

        modelMapper.map(vehicleUpdateRequest, vehicle);

        vehicleRepository.save(vehicle);

        return new VehicleUpdateResponse("Vehicle updated successfully");
    }

     @Override
    public VehicleDetails getVehicleById(UUID vehicleId){
        Optional<Vehicle> vehicle = vehicleRepository.findByVehicleId(vehicleId);
        return vehicle.map(vehicle1 -> {
            VehicleDetails vehicleDetails = new VehicleDetails();
            BeanUtils.copyProperties(vehicle1, vehicleDetails);
            return vehicleDetails;
        }).orElseThrow(() -> new VehicleNotFoundException("Vehicle id is wrong."));
    }

    @Override
    public VehicleAvailabilityResponse isVehicleAvailable(UUID vehicleId){
        Vehicle vehicle = vehicleRepository.findByVehicleId(vehicleId).orElseThrow(()-> new RuntimeException("Vehicle not found"));
        return new VehicleAvailabilityResponse(vehicle.isAvailability());
    }

    @Override
    public String vehicleDeleteSoft(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(()-> new VehicleNotFoundException("Vehicle not found"));
        vehicle.setActive(false);
        return "Vehicle soft deleted successfully";
    }

    @Override
    public VehicleUpdateResponse updateMaintenanceStatus(UUID vehicleId, VehicleMaintenanceRequest vehicleMaintenanceRequest) {

        return vehicleRepository.findByVehicleId(vehicleId)
                .map(vehicle -> {

                    vehicle.setUnderMaintenance(vehicleMaintenanceRequest.getUnderMaintenance());
                    vehicleRepository.save(vehicle);
                    return new VehicleUpdateResponse("Vehicle maintenance status updated");

                })
                .orElse(new VehicleUpdateResponse("Vehicle not found!"));

    }


}
