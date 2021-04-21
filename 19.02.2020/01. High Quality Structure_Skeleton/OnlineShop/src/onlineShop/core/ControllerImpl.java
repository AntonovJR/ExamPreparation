package onlineShop.core;

import onlineShop.core.interfaces.Controller;
import onlineShop.models.products.components.*;
import onlineShop.models.products.computers.Computer;
import onlineShop.models.products.computers.DesktopComputer;
import onlineShop.models.products.computers.Laptop;
import onlineShop.models.products.peripherals.*;

import java.util.HashMap;
import java.util.Map;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.*;

public class ControllerImpl implements Controller {
    private Map<Integer, Computer> computerMap;
    private Map<Integer, Component> componentsList;
    private Map<Integer, Peripheral> peripheralList;

    public ControllerImpl() {
        computerMap = new HashMap<>();
        componentsList = new HashMap<>();
        peripheralList = new HashMap<>();
    }

    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {
        if (computerMap.containsKey(id)) {
            throw new IllegalArgumentException(EXISTING_COMPUTER_ID);
        }
        switch (computerType) {
            case "DesktopComputer":
                computerMap.put(id, new DesktopComputer(id, manufacturer, model, price));
                break;
            case "Laptop":
                computerMap.put(id, new Laptop(id, manufacturer, model, price));
                break;
            default:
                throw new IllegalArgumentException(INVALID_COMPUTER_TYPE);
        }
        return String.format(ADDED_COMPUTER, id);
    }

    @Override
    public String addPeripheral(int computerId, int id, String peripheralType, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
        Peripheral peripheral;
        if (!computerMap.containsKey(computerId)) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        if (peripheralList.containsKey(id)) {
            throw new IllegalArgumentException(EXISTING_PERIPHERAL_ID);
        }
        switch (peripheralType) {
            case "Headset":
                peripheral = new Headset(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Keyboard":
                peripheral = new Keyboard(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Monitor":
                peripheral = new Monitor(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Mouse":
                peripheral = new Mouse(id, manufacturer, model, price, overallPerformance, connectionType);
                break;

            default:
                throw new IllegalArgumentException(INVALID_PERIPHERAL_TYPE);


        }
        computerMap.get(computerId).addPeripheral(peripheral);
        peripheralList.put(id, peripheral);
        return String.format(ADDED_PERIPHERAL, peripheralType, id, computerId);
    }

    @Override
    public String removePeripheral(String peripheralType, int computerId) {
        if (!computerMap.containsKey(computerId)) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        computerMap.get(computerId).removePeripheral(peripheralType);
        Peripheral peripheralToRemove = null;
        for (Peripheral peripheral : peripheralList.values()) {
            if (peripheral.getClass().getSimpleName().equals(peripheralType)) {
                peripheralToRemove = peripheral;
            }
        }
        try {
            peripheralList.remove(peripheralToRemove);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.format(REMOVED_PERIPHERAL, peripheralType, computerId);
    }

    @Override
    public String addComponent(int computerId, int id, String componentType, String manufacturer, String model, double price, double overallPerformance, int generation) {
        Component component;
        if (!computerMap.containsKey(computerId)) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        if (componentsList.containsKey(id)) {
            throw new IllegalArgumentException(EXISTING_COMPONENT_ID);
        }
        switch (componentType) {
            case "CentralProcessingUnit":
                component = new CentralProcessingUnit(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "Motherboard":
                component = new Motherboard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "PowerSupply":
                component = new PowerSupply(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "RandomAccessMemory":
                component = new RandomAccessMemory(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "SolidStateDrive":
                component = new SolidStateDrive(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "VideoCard":
                component = new VideoCard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            default:
                throw new IllegalArgumentException(INVALID_COMPONENT_TYPE);


        }
        computerMap.get(computerId).addComponent(component);
        componentsList.put(id, component);
        //ADDED_COMPONENT = "Component %s with id %d added successfully in computer with id %d.";
        return String.format(ADDED_COMPONENT, componentType, id, computerId);
    }

    @Override
    public String removeComponent(String componentType, int computerId) {
        if (!computerMap.containsKey(computerId)) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        computerMap.get(computerId).removeComponent(componentType);
        Component componentToRemove = null;
        for (Component component : componentsList.values()) {
            if (component.getClass().getSimpleName().equals(componentType)) {
                componentToRemove = component;
            }
        }
        try {
            componentsList.remove(componentToRemove);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.format(REMOVED_COMPONENT, componentType, computerId);
    }

    @Override
    public String buyComputer(int id) {
        Computer computer;
        if (!computerMap.containsKey(id)) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        computer = computerMap.remove(id);

        return computer.toString();
    }

    @Override
    public String BuyBestComputer(double budget) {
        return null;
    }

    @Override
    public String getComputerData(int id) {
        return computerMap.get(id).toString();
    }
}
