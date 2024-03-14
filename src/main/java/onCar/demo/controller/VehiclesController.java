package onCar.demo.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import onCar.demo.dto.VehiclesDTO;
import onCar.demo.event.UriServices;
import onCar.demo.model.VehiclesModel;
import onCar.demo.services.VehiclesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
public class VehiclesController {

    @Autowired
    private VehiclesDTO vehiclesDTO;

    @Autowired
    private VehiclesService vehiclesService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public ResponseEntity<?> listVehicles(){
        List<VehiclesModel> allVehicles = vehiclesDTO.findAll();
        return !allVehicles.isEmpty() ? ResponseEntity.ok(allVehicles) : ResponseEntity.ok("Empty list");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findVehiclesById(@PathVariable Long id){
        Optional<VehiclesModel> vehicle = vehiclesDTO.findById(id);
        return vehicle.isPresent() ? ResponseEntity.ok(vehicle.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<VehiclesModel> createVehicle(@Valid @RequestBody VehiclesModel vehicles, HttpServletResponse response){
        VehiclesModel saveVehicle = vehiclesDTO.save(vehicles);
        publisher.publishEvent(new UriServices( this, response, saveVehicle.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(saveVehicle);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVehicle(@PathVariable Long id){
        vehiclesDTO.deleteById(id);
    };
}
