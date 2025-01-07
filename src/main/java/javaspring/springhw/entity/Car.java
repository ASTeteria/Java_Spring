package javaspring.springhw.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model", nullable = false, length = 50)
    private String model;

    @Column(name = "engine_power", nullable = false)
    private Integer enginePower;

    @Column(name = "torque", nullable = false)
    private Integer torque;

}
