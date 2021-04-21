package easterRaces.entities.cars;

import easterRaces.entities.cars.BaseCar;

public class SportsCar extends BaseCar {
    private static final double CUBIC_SANTIMETERS = 3000;
    public SportsCar(String model, int horsePower) {
        super (model, horsePower, CUBIC_SANTIMETERS);
    }
}
