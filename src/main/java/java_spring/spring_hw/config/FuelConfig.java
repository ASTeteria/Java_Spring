package java_spring.spring_hw.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties
public class FuelConfig {
    private Map<String, List<String>> fuelTypes;
}