package javaspring.springhw.entity;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Data
@Builder
@Document("maintenance-review")
public class Maintenance {

    @MongoId
    private ObjectId id;

    private String name;

    private String description;

    private Double price;

    private LocalDateTime createdAt;
}