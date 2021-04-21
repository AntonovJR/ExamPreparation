package cresla.core;

import cresla.entities.modules.CooldownSystem;
import cresla.entities.modules.CryogenRod;
import cresla.entities.modules.HeatProcessor;
import cresla.entities.reactors.CryoReactor;
import cresla.entities.reactors.HeatReactor;
import cresla.interfaces.*;
import cresla.interfaces.Module;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerImpl implements Manager {
    private static int id = 0;

    private static int newId() {
        return ++id;
    }

    private static  Map<Integer, Reactor> reactorMap = new HashMap<>();
    private static  Map<Integer, Module> moduleMap = new HashMap<>();

    public ManagerImpl() {
    }

    @Override
    public String reactorCommand(List<String> arguments) {

        String reactorType = arguments.get(0);
        int additionalParameter = Integer.parseInt(arguments.get(1));
        int moduleCapacity = Integer.parseInt(arguments.get(2));

        Reactor reactor = reactorType.equals("Heat")
                ? new HeatReactor(newId(), moduleCapacity, additionalParameter)
                : new CryoReactor(newId(), moduleCapacity, additionalParameter);

        reactorMap.put(id, reactor);

        return String.format("Created %s Reactor - %d", reactorType, id);
    }

    @Override
    public String moduleCommand(List<String> arguments) {

        int reactorId = Integer.parseInt(arguments.get(0));
        int additionalParameter = Integer.parseInt(arguments.get(2));
        String moduleType = arguments.get(1);
        Module module;

        if (moduleType.equals("CryogenRod")) {

            module = new CryogenRod(newId(), additionalParameter);

            reactorMap.get(reactorId).addEnergyModule((EnergyModule) module);

        } else {

            module = moduleType.equals("HeatProcessor")

                    ? new HeatProcessor(newId(), additionalParameter)
                    : new CooldownSystem(newId(), additionalParameter);

            reactorMap.get(reactorId).addAbsorbingModule((AbsorbingModule) module);
        }
        moduleMap.put(id, module);
        return String.format("Added %s - %d to Reactor - %d", moduleType, id, reactorId);
    }

    @Override
    public String reportCommand(List<String> arguments) {

        int wantedId = Integer.parseInt(arguments.get(0));
        StringBuilder output = new StringBuilder();

        if (reactorMap.containsKey(wantedId)) {
            output.append(reactorMap.get(wantedId).toString());
        } else {
            output.append(moduleMap.get(wantedId).toString());
        }

        return output.toString().trim();
    }

    @Override
    public String exitCommand(List<String> arguments) {

        int cryo = (int) reactorMap.entrySet().stream()
                .filter(r -> r.getValue().getClass().getSimpleName().equals("CryoReactor")).count();

        int heat = reactorMap.size() - cryo;

        int energyModule = (int) moduleMap.entrySet().stream()
                .filter(m -> m.getValue().getClass().getSimpleName().equals("CryogenRod")).count();

        int absorberModule = moduleMap.size() - energyModule;

        long totalEnergy = reactorMap.values().stream().mapToLong(Reactor::getTotalEnergyOutput).sum();

        long totalAbsorbing = reactorMap.values().stream().mapToLong(Reactor::getTotalHeatAbsorbing).sum();

        StringBuilder output = new StringBuilder();

        output.append("Cryo Reactors: ")
                .append(cryo)
                .append(System.lineSeparator())
                .append("Heat Reactors: ")
                .append(heat)
                .append(System.lineSeparator())
                .append("Energy Modules: ")
                .append(energyModule)
                .append(System.lineSeparator())
                .append("Absorbing Modules: ")
                .append(absorberModule)
                .append(System.lineSeparator())
                .append("Total Energy Output: ")
                .append(totalEnergy)
                .append(System.lineSeparator())
                .append("Total Heat Absorbing: ")
                .append(totalAbsorbing);

        return output.toString().trim();
    }
}
