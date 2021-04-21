package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GarageTests {
    private Garage carList = new Garage();
    Car car;

    @Before
    public void initialize() {
        car = new Car("BMW", 500, 1500000);

        carList.addCar(new Car("BMW", 255, 15000));
        carList.addCar(new Car("Opel", 100, 5000));
        carList.addCar(new Car("Honda", 55, 500));
        carList.addCar(new Car("Seat", 160, 7500));
        carList.addCar(new Car("Golf", 400, 150000));
        carList.addCar(car);

    }

    @Test
    public void testConstructor() {
        Garage garage = new Garage();
        Assert.assertTrue(garage.getCars().isEmpty());
    }

    @Test
    public void testGetCars() {
        Assert.assertEquals(carList.getCars().size(), 6);
    }

    @Test
    public void testGetCount() {
        Assert.assertEquals(carList.getCars().size(), carList.getCount());
    }

    @Test
    public void testAdd() {
        carList.addCar(new Car("Test", 122, 1200));
        Assert.assertEquals(carList.getCars().size(), 7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNull() {
        carList.addCar(null);
    }

    @Test
    public void testSpeedSearch() {
        Assert.assertEquals(carList.findAllCarsWithMaxSpeedAbove(499).size(), 1);

    }
    @Test
    public void testSearchByModel(){
        Assert.assertEquals(carList.findAllCarsByBrand("BMW").size(), 2);
    }
    @Test
    public void getTheMostExpensiveCar(){
        Assert.assertEquals(carList.getTheMostExpensiveCar(),car);
    }
}