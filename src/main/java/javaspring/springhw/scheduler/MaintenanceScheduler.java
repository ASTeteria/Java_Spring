package javaspring.springhw.scheduler;

import javaspring.springhw.entity.Car;
import javaspring.springhw.entity.Owner;
import javaspring.springhw.repository.CarRepository;
import javaspring.springhw.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class MaintenanceScheduler {

    private final CarRepository carRepository;
    private final MailService mailService;


    @Scheduled(cron = "0 0 9 * * ?")
    public void checkOverdueMaintenance() {
        log.info("Запущено перевірку автомобілів із простроченим технічним обслуговуванням...");


        List<Car> overdueCars = carRepository.findAll()
                .stream()
                .filter(car -> car.getLastMaintenanceTimestamp() != null &&
                        car.getLastMaintenanceTimestamp().isBefore(LocalDateTime.now().minusYears(1)))
                .toList();

        Map<Owner, List<Car>> overdueCarsByOwner = overdueCars.stream()
                .collect(Collectors.groupingBy(Car::getOwner));


        if (overdueCarsByOwner.isEmpty()) {
            log.info("Прострочених автомобілів не знайдено.");
            return;
        }

        overdueCarsByOwner.forEach((owner, cars) -> {
            String carList = cars.stream()
                    .map(Car::getModel)
                    .collect(Collectors.joining(", "));

            String emailContent = """
                Шановний(а) %s,
                
                Ваші автомобілі потребують технічного обслуговування:
                %s
                
                Будь ласка, зверніться до сервісного центру для обслуговування.
                
                З повагою,
                Ваша команда CarService.
                """.formatted(owner.getUsername(), carList);

            mailService.sendMail(
                    owner.getEmail(),
                    "Прострочене технічне обслуговування",
                    emailContent
            );

            log.info("Лист відправлено власнику: {}", owner.getUsername());
        });

        log.info("Перевірка завершена. Листи відправлено.");
    }
}