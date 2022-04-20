package com.tomorrow.ParkingLotSystem.resources;

import com.tomorrow.ParkingLotSystem.domain.entities.Vehicle;
import com.tomorrow.ParkingLotSystem.dto.OwnerDto;
import com.tomorrow.ParkingLotSystem.dto.VehicleDto;
import com.tomorrow.ParkingLotSystem.exceptions.ResourceNotFoundException;
import com.tomorrow.ParkingLotSystem.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService service;

    @GetMapping
    public ResponseEntity<List<VehicleDto>> getAllVehicle(){
        List<Vehicle> vehicleList = service.findAll();
        return ResponseEntity.ok(VehicleDto.converter(vehicleList));
    }

    @GetMapping("filter")
    public ResponseEntity<VehicleDto> getByPlate(@RequestParam("plate") String plate){
        verifyIfVehicleExists(plate);
        Vehicle obj = service.findByPlate(plate);
        return ResponseEntity.ok(new VehicleDto(obj));
    }

    public ResponseEntity<VehicleDto> postVehicle(@RequestBody @Valid Vehicle vehicle){
        Vehicle obj = service.insert(vehicle);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{plate}").buildAndExpand(vehicle.getPlate()).toUri();
        return ResponseEntity.created(uri).body(new VehicleDto(obj));
    }

    private void verifyIfVehicleExists(String plate){
        if (service.findByPlate(plate) == null){
            throw new ResourceNotFoundException("Vehicle not found by plate: " + plate);
        }
    }
}
