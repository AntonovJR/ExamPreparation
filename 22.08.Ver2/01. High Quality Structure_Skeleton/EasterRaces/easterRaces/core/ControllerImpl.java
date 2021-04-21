package easterRaces.core;

import easterRaces.core.interfaces.Controller;
import easterRaces.entities.cars.Car;
import easterRaces.entities.cars.MuscleCar;
import easterRaces.entities.cars.SportsCar;
import easterRaces.entities.drivers.Driver;
import easterRaces.entities.drivers.DriverImpl;
import easterRaces.entities.racers.Race;
import easterRaces.entities.racers.RaceImpl;
import easterRaces.repositories.interfaces.Repository;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private Repository<Driver> driverRepository;
    private Repository<Car> carRepository;
    private Repository<Race> raceRepository;


    public ControllerImpl(Repository<Driver> driverRepository, Repository<Car> carRepository, Repository<Race> raceRepository) {
        this.carRepository = carRepository;
        this.driverRepository = driverRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createDriver(String driver) {
        Driver driverCurrent =  this.driverRepository.getByName (driver);
        if(driverCurrent != null){
            throw new IllegalArgumentException (String.format ("Driver %s is already created.",driver));
        }driverCurrent = new DriverImpl (driver);
        this.driverRepository.add (driverCurrent);
        return String.format ("Driver %s is created.", driver);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        Car car =  this.carRepository.getByName (model);
        if(car != null){
            throw new IllegalArgumentException (String.format ("Car %s is already created.",model));
        }
        if(type.equals ("Muscle")){
            car = new MuscleCar (model, horsePower);
        }else if(type.equals ("Sports")){
            car = new SportsCar (model, horsePower);
        }
        this.carRepository.add (car);
        return String.format ("%s %s is created.",car.getClass ().getSimpleName (),model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        Driver driver = null;
        Car car = null;
        driver =   this.driverRepository.getByName (driverName);
        car =  this.carRepository.getByName (carModel);
        if(driver == null){
            throw new IllegalArgumentException (String.format ("Driver %s could not be found.",driverName));
        }if(car == null){
            throw new IllegalArgumentException (String.format ("Car %s could not be found.",carModel));
        }
        driver.addCar (car);

        return String.format ("Driver %s received car %s.",driver.getName (), car.getModel ());
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        Driver driver =  this.driverRepository.getByName (driverName);
        Race race = this.raceRepository.getByName (raceName);
        if(driver == null){
            throw new IllegalArgumentException (String.format ("Driver %s could not be found.", driverName));
        }if(race == null){
            throw new IllegalArgumentException (String.format ("Race %s could not be found.", raceName));
        }
        race.addDriver (driver);
        return String.format ("Driver %s added in %s race.", driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        Race race = this.raceRepository.getByName (raceName);
        if(race == null){
            throw new IllegalArgumentException (String.format ("Race %s could not be found.",raceName));
        }if(race.getDrivers ().size () < 3){
            throw new IllegalArgumentException (String.format ("Race %s cannot start with less than 3 participants.",
                    raceName));
        }
        List<Driver> drivers = this.driverRepository.getAll ().stream ()
                .sorted ((s1, s2) -> (int) ((int) s2.getCar ().calculateRacePoints (race.getLaps ()) -
                        s1.getCar ().calculateRacePoints (race.getLaps ()))).collect(Collectors.toList());
        StringBuilder sb = new StringBuilder ();
        sb.append (String.format ("Driver %s wins %s race.",drivers.get (0).getName (),race.getName ()));
        sb.append (System.lineSeparator ());
        sb.append (String.format ("Driver %s is second in %s race.", drivers.get (1).getName (),race.getName ()));
        sb.append (System.lineSeparator ());
        sb.append (String.format ("Driver %s is third in %s race.",drivers.get (2).getName (), race.getName ()));
        return sb.toString ().trim ();
    }

    @Override
    public String createRace(String name, int laps) {
        Race race =  this.raceRepository.getByName (name);
        if(race != null){
            throw new IllegalArgumentException (String.format ("Race %s is already created.", name));
        }
        race = new RaceImpl (name, laps);
        this.raceRepository.add (race);
        return String.format ("Race %s is created.", name);
    }
}
