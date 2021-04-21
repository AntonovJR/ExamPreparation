package cresla.entities.reactors;
//created by J.M.

public class CryoReactor extends Reactors {

    private final int cryoProductionIndex;


    public CryoReactor(int id, int moduleCapacity, int cryoProductionIndex) {
        super(id, moduleCapacity);
        this.cryoProductionIndex = cryoProductionIndex;
    }

    private int getCryoProductionIndex() {

        return this.cryoProductionIndex;
    }

    @Override
    public long getTotalEnergyOutput() {

        return super.getTotalEnergyOutput()* this.getCryoProductionIndex();
    }
}
