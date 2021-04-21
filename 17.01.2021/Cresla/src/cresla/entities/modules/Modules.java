package cresla.entities.modules;

import cresla.interfaces.Module;

public abstract class Modules implements Module {

    private final int id;

    protected Modules(int id) {
        this.id = id;

    }

    @Override
    public int getId() {
        return this.id;
    }
}
