package bankAccount;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class BankAccountTest {
    BankAccount bankAccount;
    @Before
    public void setUp(){
         bankAccount = new BankAccount("Pesho", new BigDecimal(10000));
    }

    @Test
    public void testConstructor() {

        Assert.assertEquals(bankAccount.getName(), "Pesho");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNameShort() {
        BankAccount bankAccount = new BankAccount("e", new BigDecimal(10000));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNameIllegal() {
        BankAccount bankAccount = new BankAccount("eeeeeeeeeeeeeeeeeeeeeeeeeeee", new BigDecimal(10000));

    }

    @Test
    public void testGetBalance() {
        Assert.assertEquals(bankAccount.getBalance(), new BigDecimal(10000));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetBalance() {
        BankAccount bankAccount = new BankAccount("Pesho", new BigDecimal(-2));

    }

    @Test(expected = UnsupportedOperationException.class)
    public void testDepositUnsupported() {
        bankAccount.deposit(new BigDecimal(-1000));

    }

    @Test
    public void testDeposit() {

        bankAccount.deposit(new BigDecimal(1000));

        Assert.assertEquals(bankAccount.getBalance(), new BigDecimal(11000));

    }

    @Test(expected = UnsupportedOperationException.class)
    public void testWithdrawMoreThanBalance() {
        bankAccount.withdraw(new BigDecimal(20000));

    }

    @Test(expected = UnsupportedOperationException.class)
    public void testWithdrawLessThanZero() {
        bankAccount.withdraw(new BigDecimal(-2000));

    }
    @Test
    public void testWithdraw() {

        Assert.assertEquals(bankAccount.withdraw(new BigDecimal(500)),new BigDecimal(500));
        Assert.assertEquals(bankAccount.getBalance(),new BigDecimal(9500));

    }



}