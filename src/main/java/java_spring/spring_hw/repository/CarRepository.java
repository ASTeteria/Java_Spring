package java_spring.spring_hw.repository;


import java_spring.spring_hw.entity.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findByEnginePowerBetween(Integer min, Integer max);
}
