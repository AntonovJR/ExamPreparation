package onlineShop.models.products.computers;

import onlineShop.models.BaseProduct;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.COMPUTER_COMPONENTS_TO_STRING;

public abstract class BaseComputer extends BaseProduct implements Computer {
    private List<Component> components;
    private List<Peripheral> peripherals;


    protected BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }


    @Override
    public String toString() {
        return super.toString() + " " + String.format(COMPUTER_COMPONENTS_TO_STRING, this.components.size());
    }

    @Override
    public List<Component> getComponents() {
        return this.components;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return this.peripherals;
    }

    @Override
    public void addComponent(Component component) {
        if (components.contains(component)) {
            throw new IllegalArgumentException(String.format(EXISTING_COMPONENT, component.getClass().getSimpleName(), getClass().getSimpleName(), getId()));
        }
        this.components.add(component);

    }

    public boolean isInComponentList(String componentType) {
        for (Component component : components) {
            if (component.getClass().getSimpleName().equals(componentType)) {
                return true;
            }

        }
        return false;
    }

    @Override
    public Component removeComponent(String componentType) {

        if (components.isEmpty() || isInComponentList(componentType)) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_COMPONENT,componentType, getClass().getSimpleName(), getId()));
        }
        return components.stream().filter(c->c.getClass().getSimpleName().equals(componentType)).findFirst().orElse(null);
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        if (peripherals.contains(peripheral)) {
            throw new IllegalArgumentException(String.format(EXISTING_PERIPHERAL, peripheral.getClass().getSimpleName(), getClass().getSimpleName(), getId()));
        }
        this.peripherals.add(peripheral);
    }
    public boolean isInPeripheralList(String peripheralType) {
        for (Peripheral peripheral : peripherals) {
            if (peripherals.getClass().getSimpleName().equals(peripheralType)) {
                return true;
            }

        }
        return false;
    }
    @Override
    public Peripheral removePeripheral(String peripheralType) {
        if (peripherals.isEmpty() || isInComponentList(peripheralType)) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_PERIPHERAL,peripheralType, getClass().getSimpleName(), getId()));
        }
        return peripherals.stream().filter(c->c.getClass().getSimpleName().equals(peripheralType)).findFirst().orElse(null);
    }

    @Override
    public double getOverallPerformance() {
        double overallPerformance = 0;
        if (getComponents().isEmpty()) {
            return super.getOverallPerformance();
        }
        for (Component component : components) {
            overallPerformance += component.getOverallPerformance();

        }
        return overallPerformance;
    }

    @Override
    public double getPrice() {
        double sum = 0;
        for (Component component : components) {
            sum += component.getPrice();

        }
        for (Peripheral peripheral : peripherals) {
            sum += peripheral.getPrice();

        }
        return sum;
    }
}
