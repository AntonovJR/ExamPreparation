package easterRaces.entities.racers;

import easterRaces.entities.drivers.Driver;

import java.util.ArrayList;
import java.util.Collection;

public class RaceImpl implements Race{
    private String name;
    private int laps;
    private Collection<Driver> drivers;

    public RaceImpl(String name, int laps) {
        this.name = name;
        this.laps = laps;
        this.drivers = new ArrayList<> ();
    }

    public void setName(String name) {
        if(name == null || name.trim ().isEmpty () || name.length () < 5){
            throw  new IllegalArgumentException ("Name {name} cannot be less than 5 symbols.");
        }
        this.name = name;
    }

    public void setLaps(int laps) {
        if(laps < 1){
            throw new IllegalArgumentException ("Laps cannot be less than 1.");
        }
        this.laps = laps;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLaps() {
        return this.laps;
    }

    @Override
    public Collection<Driver> getDrivers() {
        return this.drivers;
    }

    @Override
    public void addDriver(Driver driver) {
        if(driver == null){
            throw new IllegalArgumentException ("Driver cannot be null.");
        }else if(!driver.getCanParticipate ()){
            throw new IllegalArgumentException (String.format ("Driver %s could not participate in race.",driver.getName ()));
        }else if(this.drivers.contains (driver)){
            throw new IllegalArgumentException (String.format ("Driver %s is already added in %s race.",driver.getName (),this.name));
        }
        this.drivers.add (driver);
    }
}
