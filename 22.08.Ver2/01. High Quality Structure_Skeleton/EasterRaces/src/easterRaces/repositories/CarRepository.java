package easterRaces.repositories;

import easterRaces.entities.cars.BaseCar;
import easterRaces.entities.cars.Car;
import easterRaces.entities.drivers.Driver;
import easterRaces.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CarRepository implements Repository<Car> {

    private Collection<Car> carList;

    public CarRepository() {
        this.carList = new ArrayList<>();
    }


    @Override
    public Car getByName(String name) {

        for (Car car : this.carList) {
            if(car.getModel().equals(name)){
                return car;
            }

        }

        return null;
    }

    @Override
    public Collection<Car> getAll() {
        return  Collections.unmodifiableCollection(this.carList);
    }

    @Override
    public void add(Car model) {
        this.carList.add(model);

    }

    @Override
    public boolean remove(Car model) {
        return this.carList.remove(model);
    }
}
