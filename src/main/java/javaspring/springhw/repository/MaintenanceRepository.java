package javaspring.springhw.repository;


import javaspring.springhw.entity.Maintenance;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MaintenanceRepository extends MongoRepository<Maintenance, String> {}

