package onCar.demo.dto;

import onCar.demo.model.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDTO  extends JpaRepository<TransactionModel, Long> {
}
