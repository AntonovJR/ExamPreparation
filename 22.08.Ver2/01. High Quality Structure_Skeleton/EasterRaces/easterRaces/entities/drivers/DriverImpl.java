package easterRaces.entities.drivers;

import easterRaces.entities.cars.Car;

public class DriverImpl implements Driver{
    private String name;
    private Car car;
    private int numberOfWins;
    private boolean canParticipate = false;

    public DriverImpl(String name) {
        setName (name);
    }

    public void setName(String name) {
        if(name == null || name.trim ().isEmpty () || name.length () < 5){
            throw new IllegalArgumentException (String.format ("Name %s cannot be less than 5 symbols.",name));
        }
        this.name = name;
    }

    public void setCanParticipate(boolean canParticipate) {
        this.canParticipate = canParticipate;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Car getCar() {
        return this.car;
    }

    @Override
    public int getNumberOfWins() {
        return this.numberOfWins;
    }

    @Override
    public void addCar(Car car) {
        if(car == null){
            throw new IllegalArgumentException ( "Car cannot be null.");
        }else {
            this.car = car;
            setCanParticipate (true);
        }

    }
    @Override
    public void winRace() {
        this.numberOfWins++;
    }
    @Override
    public boolean getCanParticipate() {
        return this.canParticipate;
    }
}
