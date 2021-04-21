package easterRaces.entities.cars;

public abstract class BaseCar implements Car{
    private String model;
    private int horsePower;
    private double cubicCentimeters;

    protected BaseCar(String model, int horsePower, double cubicCentimeters) {
        setModel (model);
        setHorsePower (horsePower);
        this.cubicCentimeters = cubicCentimeters;
    }

    private void setModel(String model) {
        if(model == null || model.trim ().isEmpty () || model.length () < 4){
            throw new IllegalArgumentException (String.format ( "Model %s cannot be less than 4 symbols.",model));
        }
        this.model = model;
    }

    private void setHorsePower(int horsePower) {
        if(this.getClass ().getSimpleName ().equals ("MuscleCar")){
            if(horsePower < 400 || horsePower > 600){
                throw new IllegalArgumentException (String.format ("Invalid horse power: %d.",horsePower));
            }
        }else if(this.getClass ().getSimpleName ().equals ("SportsCar")){
            if(horsePower < 250 || horsePower > 450){
                throw new IllegalArgumentException (String.format ("Invalid horse power: %d.", horsePower));
            }
        }
        this.horsePower = horsePower;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public int getHorsePower() {
        return this.horsePower;
    }

    @Override
    public double getCubicCentimeters() {
        return this.cubicCentimeters;
    }

    @Override
    public double calculateRacePoints(int laps) {
        return ((getCubicCentimeters () / getHorsePower ()) * laps);
    }
}
