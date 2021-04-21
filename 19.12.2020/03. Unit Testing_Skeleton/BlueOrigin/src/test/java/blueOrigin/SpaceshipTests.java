package blueOrigin;

import org.junit.Assert;
import org.junit.Test;

public class SpaceshipTests {
    @Test(expected = NullPointerException.class)
    public void testSetName() {
        new Spaceship("", 1);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameNull() {
        new Spaceship(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacity() {
        new Spaceship("Ship", -1);
    }

    @Test
    public void testAddAstronaut() {
        Spaceship ship = new Spaceship("Ship", 10);
        ship.add(new Astronaut("name", 100));
        Assert.assertEquals(1, ship.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAstronautFail() {
        Spaceship ship = new Spaceship("Ship", 1);
        ship.add(new Astronaut("name", 100));
        ship.add(new Astronaut("name2", 100));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAstronautAlreadyThere() {
        Spaceship ship = new Spaceship("Ship", 3);
        ship.add(new Astronaut("name", 100));
        ship.add(new Astronaut("name", 100));

    }

    @Test
    public void testRemoveShouldReturnTrue() {
        Spaceship ship = new Spaceship("Ship", 3);
        ship.add(new Astronaut("name", 100));
        Assert.assertTrue(ship.remove("name"));
    }

    @Test
    public void testRemoveShouldReturnFalse() {
        Spaceship ship = new Spaceship("Ship", 3);
        ship.add(new Astronaut("name", 100));
        Assert.assertFalse(ship.remove("invalid"));
    }

}
