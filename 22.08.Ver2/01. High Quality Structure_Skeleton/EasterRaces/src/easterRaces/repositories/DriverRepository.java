package easterRaces.repositories;

import easterRaces.entities.drivers.Driver;
import easterRaces.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DriverRepository implements Repository<Driver>{

    private Collection<Driver> driverList;

    public DriverRepository() {
        this.driverList = new ArrayList<>();
    }

    @Override
    public Driver getByName(String name) {

        for (Driver driver : this.driverList) {
            if(driver.getName().equals(name)){
                return driver;
            }

        }
        return null;
    }

    @Override
    public Collection<Driver> getAll() {
        return Collections.unmodifiableCollection(this.driverList);
    }

    @Override
    public void add(Driver model) {
        this.driverList.add(model);

    }

    @Override
    public boolean remove(Driver model) {
        return this.driverList.remove(model);
    }
}
