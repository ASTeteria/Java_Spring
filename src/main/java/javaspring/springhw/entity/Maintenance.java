package javaspring.springhw.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "Maintenances")
@Data
public class Maintenance {

    @MongoId
    private String id;

    private String name;

    private String description;

    private Double price;
}