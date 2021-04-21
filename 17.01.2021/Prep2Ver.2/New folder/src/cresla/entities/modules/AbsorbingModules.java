package cresla.entities.modules;
//created by J.M.

import cresla.interfaces.AbsorbingModule;

public abstract class AbsorbingModules extends Modules implements AbsorbingModule {

    private final int heatAbsorbing;

    protected AbsorbingModules(int id, int heatAbsorbing) {
        super(id);
        this.heatAbsorbing = heatAbsorbing;
    }

    @Override
    public int getHeatAbsorbing() {
        return this.heatAbsorbing;
    }

    @Override
    public String toString() {

        StringBuilder output = new StringBuilder();

        output.append(this.getClass().getSimpleName())
                .append(" Module – ")
                .append(this.getId())
                .append(System.lineSeparator())
                .append("Heat Absorbing: ")
                .append(this.getHeatAbsorbing());

        return output.toString().trim();
    }
}
