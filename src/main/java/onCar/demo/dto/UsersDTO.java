package onCar.demo.dto;

import onCar.demo.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersDTO extends JpaRepository<UsersModel, Long> {
}
