package javaspring.springhw.repository;



import javaspring.springhw.entity.Maintenance;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MaintenanceRepository extends MongoRepository<Maintenance, ObjectId> {


    List<Maintenance> findAllByName(String name);

    List<Maintenance> findAllByCreatedAtAfter(LocalDateTime from);
}