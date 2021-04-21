package easterRaces.core;

import easterRaces.core.interfaces.Controller;
import easterRaces.entities.cars.BaseCar;
import easterRaces.entities.cars.Car;
import easterRaces.entities.cars.MuscleCar;
import easterRaces.entities.cars.SportsCar;
import easterRaces.entities.drivers.Driver;
import easterRaces.entities.drivers.DriverImpl;
import easterRaces.entities.racers.Race;
import easterRaces.entities.racers.RaceImpl;
import easterRaces.repositories.interfaces.Repository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static easterRaces.common.ExceptionMessages.*;
import static easterRaces.common.OutputMessages.*;


public class ControllerImpl implements Controller {
    private Repository<Driver> riderRepository;
    private Repository<Car> motorcycleRepository;
    private Repository<Race> raceRepository;
    private static int limit = 0;

    public ControllerImpl(Repository<Driver> riderRepository, Repository<Car> motorcycleRepository,
                          Repository<Race> raceRepository) {
        this.riderRepository = riderRepository;
        this.motorcycleRepository = motorcycleRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createDriver(String driver) {
        if (riderRepository.getByName(driver) != null) {
            throw new IllegalArgumentException(String.format(DRIVER_EXISTS, driver));
        }
        riderRepository.add(new DriverImpl(driver));
        return String.format(DRIVER_CREATED, driver);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        if (motorcycleRepository.getByName(model) != null) {
            throw new IllegalArgumentException(String.format(CAR_EXISTS, model));
        }
        Car car;
        if (type.equals("Muscle")) {
            car = new MuscleCar(model, horsePower);
        } else {
            car = new SportsCar(model, horsePower);
        }
        motorcycleRepository.add(car);
        return String.format(CAR_CREATED, type+"Car", model);

    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        if (riderRepository.getByName(driverName) == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }
        if (motorcycleRepository.getByName(carModel) == null) {
            throw new IllegalArgumentException(String.format(CAR_NOT_FOUND, carModel));
        }
        riderRepository.getByName(driverName).addCar(motorcycleRepository.getByName(carModel));
        return String.format(CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {

        if (raceRepository.getByName(raceName) == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }
        if (riderRepository.getByName(driverName) == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }
        raceRepository.getByName(raceName).addDriver(riderRepository.getByName(driverName));
        return String.format(DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        if (raceRepository.getByName(raceName) == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }
        if (raceRepository.getByName(raceName).getDrivers().size() < 3) {
            throw new IllegalArgumentException(String.format(RACE_INVALID, raceName, 3));
        }
        Map<String, Double> drivers = new HashMap<>();
        Collection<Driver> all = raceRepository.getByName(raceName).getDrivers();
        for (Driver driver1 : all) {
            int laps = raceRepository.getByName(raceName).getLaps();
            double points = driver1.getCar().calculateRacePoints(laps);
            drivers.put(driver1.getName(), points);
        }
        LinkedHashMap<String, Double> result = drivers.entrySet()
                .stream()
                .sorted((o1, o2) ->
                        o2.getValue().compareTo(o1.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Double> entryDriver : result.entrySet()) {
            if (limit == 0){
                sb.append(String.format(DRIVER_FIRST_POSITION, entryDriver.getKey(),raceName));
                sb.append(System.lineSeparator());
                limit++;
            }else if (limit == 1){
                sb.append(String.format(DRIVER_SECOND_POSITION,entryDriver.getKey(),raceName));
                sb.append(System.lineSeparator());
                limit++;
            }else if (limit == 2){
                sb.append(String.format(DRIVER_THIRD_POSITION, entryDriver.getKey(),raceName));
                limit++;
            }
        }

        raceRepository.remove(raceRepository.getByName(raceName));
        return sb.toString().trim();


    }

    @Override
    public String createRace(String name, int laps) {
        if (raceRepository.getByName(name) != null) {
            throw new IllegalArgumentException(String.format(RACE_EXISTS, name));
        }
        Race race = new RaceImpl(name, laps);
        raceRepository.add(race);
        return String.format(RACE_CREATED, name);
    }
}
