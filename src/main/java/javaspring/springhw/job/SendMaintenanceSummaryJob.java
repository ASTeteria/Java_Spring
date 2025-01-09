package javaspring.springhw.job;
import javaspring.springhw.entity.Car;
import javaspring.springhw.entity.Owner;
import javaspring.springhw.repository.CarRepository;
import javaspring.springhw.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class SendMaintenanceSummaryJob {

    private final CarRepository carRepository;
    private final MailService mailService;

    @Value("${spring.mail.username}")
    private String to;

    @Scheduled(fixedDelay = 30, initialDelay = 10, timeUnit = TimeUnit.SECONDS)
    public void sendMaintenanceSummary() {
        log.info("Preparing maintenance summary...");


        Map<Owner, List<Car>> overdueCarsByOwner = carRepository.findAll()
                .stream()
                .filter(car -> car.getLastMaintenanceTimestamp() != null &&
                        car.getLastMaintenanceTimestamp().isBefore(LocalDateTime.now().minusYears(1)))
                .collect(Collectors.groupingBy(Car::getOwner));


        if (overdueCarsByOwner.isEmpty()) {
            log.info("No overdue maintenance found.");
            return;
        }
        String summaries = overdueCarsByOwner.entrySet()
                .stream()
                .map(entry -> {
                    String ownerEmail = entry.getKey().getEmail();
                    String cars = entry.getValue()
                            .stream()
                            .map(Car::getModel)
                            .collect(Collectors.joining(", "));

                    return "Owner '%s' (%s) has overdue maintenance for the following cars: %s"
                            .formatted(entry.getKey().getUsername(), ownerEmail, cars);
                })
                .collect(Collectors.joining("\n"));
        mailService.sendMail(
                to,
                "Overdue Maintenance Summary",
                summaries
        );

        log.info("Mail with maintenance summary was sent.");
    }
}