package java_spring.spring_hw.repository;


import java_spring.spring_hw.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {


    List<Car> findAllByEnginePowerBetween(Integer minEnginePower, Integer maxEnginePower);


    List<Car> findAllByEnginePowerGreaterThan(Integer value);


    List<Car> findAllByEnginePowerLessThan(Integer value);
}