package bankSafe;


import org.junit.Assert;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.Map;

public class BankVaultTest {

    @Test
    public void testConstructor() {
        BankVault bankVault = new BankVault();
        Assert.assertEquals(bankVault.getVaultCells().size(), 12);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNotExistingCell() throws OperationNotSupportedException {
        BankVault bankVault = new BankVault();
        Item item = new Item("Sasho", "OMG");
        bankVault.addItem("Sasho", item);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testExistingCell() throws OperationNotSupportedException {
        BankVault bankVault = new BankVault();
        Item item = new Item("Sasho", "OMG");
        Item itemTwo = new Item("Pesho", "OMG");
        bankVault.addItem("A1", item);
        bankVault.addItem("A1", itemTwo);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testExistingItem() throws IllegalArgumentException, OperationNotSupportedException {
        BankVault bankVault = new BankVault();
        Item item = new Item("Sasho", "OMG");
        bankVault.addItem("A2", item);
        bankVault.addItem("A2", item);

    }@Test
    public void testAdd() throws OperationNotSupportedException {
        BankVault bankVault = new BankVault();
        Item item = new Item("Sasho", "OMG");
        bankVault.addItem("A2", item);
        Map<String, Item> vaultCells = bankVault.getVaultCells();
        Assert.assertEquals(vaultCells.get("A2").getOwner(), "Sasho");

    }
    @Test(expected = IllegalArgumentException.class)
    public void testNotContainRemove(){
        BankVault bankVault = new BankVault();
        Item item = new Item("A1", "OMG");
        bankVault.removeItem("Sasho", item);

    }
    @Test(expected = IllegalArgumentException.class)
    public void testNotContainItem() throws OperationNotSupportedException {
        BankVault bankVault = new BankVault();
        Item item = new Item("Pesho", "OMG");
        Item itemTwo = new Item("Sasho", "OMG");
        bankVault.addItem("A1",item);
        bankVault.removeItem("A1", itemTwo);

    }  @Test
    public void testRemove() throws OperationNotSupportedException {
        BankVault bankVault = new BankVault();
        Item item = new Item("Pesho", "OMG");
        bankVault.addItem("A1",item);
        bankVault.removeItem("A1", item);
        Map<String, Item> vaultCells = bankVault.getVaultCells();
       Assert.assertNull(vaultCells.get("A1"));

    }

}