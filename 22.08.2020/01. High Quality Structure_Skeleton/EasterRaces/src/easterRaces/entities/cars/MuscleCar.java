package easterRaces.entities.cars;

import static easterRaces.common.ExceptionMessages.*;

public class MuscleCar extends BaseCar {

    private int horsePower;

    public MuscleCar(String model, int horsePower) {
        super(model, valid(horsePower), 5000.00);
    }


    private static int valid(int horsePower) {
        if (horsePower > 400 && horsePower < 600) {
            return horsePower;
        } else {
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER, horsePower));
        }
    }
}
