package halfLife;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTests {


    @Test
    public void testConstructorCreateInstance() {
        Player player = new Player("Pesho", 100);
        Assert.assertNotNull(player);
    }

    @Test
    public void testGetUsername() {
        Player player = new Player("Pesho", 100);
        String actualName = player.getUsername();
        String expected = "Pesho";
        Assert.assertEquals(expected, actualName);
    }

    @Test(expected = NullPointerException.class)
    public void testSetUserNameInvalid() {
        Player player = new Player(null, 100);

    }

    @Test
    public void testGetHealth() {
        Player player = new Player("Pesho", 100);
        int actualHealth = player.getHealth();
        int expected = 100;
        Assert.assertEquals(expected, actualHealth);

    }

    @Test
    public void testGetGuns() {
        Player player = new Player("Pesho", 100);
        int actualLength = player.getGuns().size();
        int expectedLength = 0;
        Assert.assertEquals(expectedLength, actualLength);

    }

    @Test
    public void testTakeDamageBelowZero() {
        Player player = new Player("Pesho", 100);
        player.takeDamage(200);
    }

    @Test
    public void testTakeDamage() {
        Player player = new Player("Pesho", 100);
        player.takeDamage(20);
        Assert.assertEquals(80, player.getHealth());
    }

    @Test(expected = NullPointerException.class)
    public void testAddGun() {
        Player player = new Player("Pesho", 100);
        player.addGun(null);
    }

    @Test
    public void testValidAddGun() {
        Player player = new Player("Pesho", 100);
        Gun gun = new Gun("pistol", 20);
        player.addGun(gun);

        Assert.assertEquals(gun, player.getGun(gun.getName()));
    }

    @Test
    public void testRemoveGun() {
        Player player = new Player("Pesho", 100);
        Gun gun = new Gun("pistol", 20);
        player.addGun(gun);
        boolean actualResult = player.removeGun(gun);
        Assert.assertTrue(actualResult);
    }

    @Test
    public void testGetGun() {
        Player player = new Player("Pesho", 100);
        Gun gun = new Gun("pistol", 20);
        player.addGun(gun);

        Assert.assertEquals(gun, player.getGun(gun.getName()));
    }

    @Test
    public void testGetInvalidGun() {
        Player player = new Player("Pesho", 100);
        Gun gun = new Gun("pistol", 20);
        player.addGun(gun);

        Assert.assertNull(player.getGun("M16"));
    }


}
