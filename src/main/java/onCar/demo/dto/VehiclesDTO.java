package onCar.demo.dto;

import onCar.demo.model.VehiclesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiclesDTO extends JpaRepository<VehiclesModel, Long> {
}
