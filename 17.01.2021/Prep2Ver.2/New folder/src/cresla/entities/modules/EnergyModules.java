package cresla.entities.modules;
//created by J.M.

import cresla.interfaces.EnergyModule;

public abstract class EnergyModules  extends Modules implements EnergyModule {

    private final int energyOutput;

    protected EnergyModules(int id, int energyOutput) {
        super(id);
        this.energyOutput = energyOutput;
    }

    @Override
    public int getEnergyOutput() {
        return this.energyOutput;
    }


    @Override
    public String toString() {

        StringBuilder output = new StringBuilder();

        output.append(this.getClass().getSimpleName())
                .append(" Module – ")
                .append(this.getId())
                .append(System.lineSeparator())
                .append("Energy Output: ")
                .append(this.getEnergyOutput());

        return output.toString().trim();
    }
}
