package cresla.entities.modules;

import cresla.interfaces.AbsorbingModule;

public abstract class AbsorberModules extends Modules implements AbsorbingModule {
    protected AbsorberModules(int id) {
        super(id);
    }

    @Override
    public int getHeatAbsorbing() {
        return 0;
    }
}
