package onCar.demo.services;

import jakarta.servlet.http.HttpServletResponse;
import onCar.demo.dto.VehiclesDTO;
import onCar.demo.model.VehiclesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehiclesService {

    @Autowired
    private VehiclesDTO vehiclesDTO;
    public VehiclesModel searchVehiclesById(Long id){
        Optional<VehiclesModel> vehicles = vehiclesDTO.findById(id);
        if(vehicles.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return vehicles.get();
    }
}
