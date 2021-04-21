package easterRaces.entities.cars;

import static easterRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class SportsCar extends BaseCar {

    public SportsCar(String model, int horsePower) {
        super(model, valid(horsePower), 3000.00);
    }

    private static int valid(int horsePower) {
        if (horsePower > 250 && horsePower < 450) {
           return horsePower;
        } else {
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER, horsePower));
        }
    }


}
