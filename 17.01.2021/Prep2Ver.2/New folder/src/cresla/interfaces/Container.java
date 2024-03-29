package cresla.interfaces;
//created by J.M.

public interface Container {

    long getTotalEnergyOutput();

    long getTotalHeatAbsorbing();

    void addEnergyModule(EnergyModule energyModule);

    void addAbsorbingModule(AbsorbingModule absorbingModule);

    int getModuleByInputCount();
}
