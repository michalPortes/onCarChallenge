package onCar.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
@Entity
@Table(name = "vehicles")
@Getter
@Setter
public class VehiclesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String model;
    @NotNull
    private String brand;
    @NotNull
    private String color;
    @NotNull
    private String name;
}
