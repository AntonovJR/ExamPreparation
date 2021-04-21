package cresla.interfaces;
//created by J.M.

public interface Reactor extends Identifiable {

    long getTotalEnergyOutput();

    long getTotalHeatAbsorbing();

    int getModuleCount();

    void addEnergyModule(EnergyModule energyModule);

    void addAbsorbingModule(AbsorbingModule absorbingModule);
}
