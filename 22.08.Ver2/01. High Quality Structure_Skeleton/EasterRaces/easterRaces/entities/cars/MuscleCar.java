package easterRaces.entities.cars;

public class MuscleCar extends BaseCar{
private static final double CUBIC_SANTIMETERS = 5000;
    public MuscleCar(String model, int horsePower) {
        super (model, horsePower, CUBIC_SANTIMETERS);
    }
}
