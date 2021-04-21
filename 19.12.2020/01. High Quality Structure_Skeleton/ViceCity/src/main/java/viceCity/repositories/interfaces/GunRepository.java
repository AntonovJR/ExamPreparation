package viceCity.repositories.interfaces;

import viceCity.models.guns.Gun;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GunRepository implements Repository<Gun> {

    private Map<String, Gun> guns;

    public GunRepository() {
        this.guns = new HashMap<>();
    }

    @Override
    public Collection<Gun> getModels() {
        return Collections.unmodifiableCollection(this.guns.values());
    }

    @Override
    public void add(Gun model) {
        this.guns.putIfAbsent(model.getName(),model);

    }

    @Override
    public boolean remove(Gun model) {
        if(this.guns.containsKey(model.getName())){
            this.guns.remove(model.getName());
            return  true;
        }
       return false;
    }

    @Override
    public Gun find(String name) {
        return this.guns.get(name);
    }
}
