package javaspring.springhw.repository;


import javaspring.springhw.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {


    List<Car> findAllByEnginePowerBetween(Integer minEnginePower, Integer maxEnginePower);


    List<Car> findAllByEnginePowerGreaterThan(Integer value);


    List<Car> findAllByEnginePowerLessThan(Integer value);
}
