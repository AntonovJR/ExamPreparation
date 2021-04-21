package easterRaces.repositories;

import easterRaces.entities.cars.Car;
import easterRaces.entities.racers.Race;
import easterRaces.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class RaceRepository implements Repository<Race> {
    private Collection<Race> raceCollection;

    public RaceRepository() {
        this.raceCollection = new ArrayList<>();
    }


    @Override
    public Race getByName(String name) {

        for (Race race : this.raceCollection) {
            if(race.getName().equals(name)){
                return race;
            }

        }

        return null;
    }

    @Override
    public Collection<Race> getAll() {
        return  Collections.unmodifiableCollection(this.raceCollection);
    }

    @Override
    public void add(Race model) {
        this.raceCollection.add(model);

    }

    @Override
    public boolean remove(Race model) {
        return this.raceCollection.remove(model);
    }
}
