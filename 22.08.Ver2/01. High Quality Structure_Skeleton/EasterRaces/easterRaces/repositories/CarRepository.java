package easterRaces.repositories;

import easterRaces.entities.cars.Car;
import easterRaces.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;

public class CarRepository implements Repository<Car> {
    private Collection<Car> cars = new ArrayList<> ();

    @Override
    public Car getByName(String name) {
        Car car = null;
        for(Car out: this.cars){
            if(out.getModel ().equals (name)){
                car = out;
            }
        }
        return car;
    }

    @Override
    public Collection<Car> getAll() {
        return this.cars;
    }

    @Override
    public void add(Car model) {
        this.cars.add (model);
    }

    @Override
    public boolean remove(Car model) {
        return this.cars.removeIf (f -> f.getModel ().equals (model));
    }
}
