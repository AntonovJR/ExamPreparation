package carTrip;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarTest {
    Car car;

    @Before
    public void setUp() {
        car = new Car("Test", 100.00, 90.00, 0.06);
    }

    @Test
    public void testConstructor() {
        Car carTest = new Car("Test", 100.00, 90.00, 0.06);
        Assert.assertEquals(carTest.getModel(), "Test");
        Assert.assertEquals(car.getTankCapacity(), 100.00, 0.00);
        Assert.assertEquals(car.getFuelAmount(), 90.00, 0.00);
        Assert.assertEquals(car.getFuelConsumptionPerKm(), 0.06, 0.00);

    }

    @Test
    public void testGetModel() {
        Assert.assertEquals(car.getModel(), "Test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setModelNull() {
        car.setModel(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setModelEmpty() {
        car.setModel("");
    }

    @Test
    public void setModel() {
        car.setModel("Test1");
        Assert.assertEquals(car.getModel(), "Test1");
    }

    @Test
    public void testGetTankCapacity() {
        Assert.assertEquals(car.getTankCapacity(), 100.00, 0.00);

    }

    @Test
    public void testSetTankCapacity() {
        car.setTankCapacity(120);
        Assert.assertEquals(car.getTankCapacity(), 120.00, 0.00);
    }

    @Test
    public void testGetFuelAmount() {
        Assert.assertEquals(car.getFuelAmount(), 90.00, 0.00);
    }

    @Test
    public void testGetFuelConsumption() {
        Assert.assertEquals(car.getFuelConsumptionPerKm(), 0.06, 0.00);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetFuelAmountBiggerThanTank() {
        car.setFuelAmount(120);
    }

    @Test
    public void testSetFuelAmount() {
        car.setFuelAmount(50);
        Assert.assertEquals(car.getFuelAmount(), 50, 0.00);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetFuelConsumptionFalse() {
        car.setFuelConsumptionPerKm(0.0);
    }

    @Test
    public void testSetFuelConsumption() {
        car.setFuelConsumptionPerKm(1);
        Assert.assertEquals(car.getFuelConsumptionPerKm(), 1, 0.00);
    }

    @Test(expected = IllegalStateException.class)
    public void testDriveFail() {
        car.drive(1503);
        //(90l 0.6= 6/100 = 1500km
    }

    @Test
    public void testDrive() {
        car.drive(100);
        Assert.assertEquals(car.getFuelAmount(), 84, 0.00);
        Assert.assertEquals(car.drive(100), "Have a nice trip");
    }

 @Test(expected = IllegalStateException.class)
    public void testRefuelFail() {
        car.refuel(100);

    }

    @Test
    public void testRefuel() {
        car.refuel(5);
        Assert.assertEquals(car.getFuelAmount(), 95, 0.00);

    }
}