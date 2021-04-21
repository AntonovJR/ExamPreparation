package computers;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ComputerManagerTest {
    private ComputerManager computerManager;
    private Computer computer;
    private Computer computerTwo;

    @Before
    public void setUp() {
        this.computerManager = new ComputerManager();
        computer = new Computer("DELL", "A4532", 300.00);
        computerTwo = new Computer("ASUS", "ROG", 500.00);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetComputers() {
        computerManager.getComputers().remove(0);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testAddNull() {
        this.computerManager.addComputer(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddSecond() {
        this.computerManager.addComputer(computer);
        this.computerManager.addComputer(computer);
    }

    @Test
    public void testAdd() {
        this.computerManager.addComputer(computer);
        assertEquals(1, this.computerManager.getCount());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testGetComputer() {
        this.computerManager.getComputer(null, "test_model");

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetTwoComputer() {
        this.computerManager.getComputer("Dell", null);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetNonExisting() {
        this.computerManager.getComputer(computer.getManufacturer(), computer.getModel());

    }

    @Test
    public void testGetCorrect() {
        computerManager.addComputer(computer);
        computerManager.addComputer(computerTwo);
        Computer returned = this.computerManager.getComputer(this.computer.getManufacturer(), this.computer.getModel());
        assertNotNull(returned);
        assertEquals(computer.getManufacturer(), returned.getManufacturer());
        assertEquals(computer.getModel(), returned.getModel());
    }

    @Test
    public void testGetByMan() {
        computerManager.addComputer(computer);
        computerManager.addComputer(computerTwo);
        List<Computer> list = computerManager.getComputersByManufacturer(computer.getManufacturer());
        assertNotNull(list);
        assertEquals(list.get(0).getManufacturer(), computer.getManufacturer());
    }

    @Test
    public void testGetByManWhenEmpty() {
        List<Computer> list = computerManager.getComputersByManufacturer(computer.getManufacturer());
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }
}