package viceCity.repositories;

import viceCity.models.guns.Gun;
import viceCity.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class GunRepository implements Repository {

    private final Collection<Gun> models;

    public GunRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Collection getModels() {
        return Collections.unmodifiableCollection(this.models);
    }

    @Override
    public void add(Object model) {
        this.models.add((Gun) model);
    }

    @Override
    public boolean remove(Object model) {
        return this.models.remove(model);
    }

    @Override
    public Object find(String name) {

        return this.models.stream().filter(e->e.getName().equals(name)).findFirst().orElse(null);
    }
}
