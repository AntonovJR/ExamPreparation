package easterRaces.repositories;

import easterRaces.entities.racers.Race;
import easterRaces.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;

public class RaceRepository implements Repository<Race> {
    private Collection<Race> races = new ArrayList<> ();
    @Override
    public Race getByName(String name) {
        Race race = null;
        for(Race out: this.races){
            if(out.getName ().equals (name)){
                race = out;
            }
        }
        return race;
    }

    @Override
    public Collection<Race> getAll() {
        return this.races;
    }

    @Override
    public void add(Race model) {
        this.races.add (model);
    }

    @Override
    public boolean remove(Race model) {
        return this.races.removeIf (f -> f.getName ().equals (model));
    }
}
