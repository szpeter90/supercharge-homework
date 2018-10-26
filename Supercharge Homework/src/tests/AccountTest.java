package tests;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import resources.Account;
import resources.User;

public class AccountTest {

	Account testAcc1;
	Account testAcc2;
	
	@Before
	public void setUp(){
		testAcc1 = new Account(new User("John", "Doe"), BigDecimal.valueOf(1000));
		testAcc2 = new Account(new User("Jane", "Doe"));
	}

	@Test(expected=IllegalArgumentException.class)
	public void	depositNullParam(){
		testAcc1.depositMoney(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void	depositZeroParam(){
		testAcc1.depositMoney(BigDecimal.ZERO);
	}
	
	@Test
	public void depositWorksWell(){
		testAcc1.depositMoney(BigDecimal.valueOf(2000));
		assertEquals(BigDecimal.valueOf(3000), testAcc1.getBalance());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void	withdrawNullParam(){
		testAcc1.withdrawMoney(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void	withdrawZeroParam(){
		testAcc1.withdrawMoney(BigDecimal.ZERO);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void	withdrawOutOfCoverage(){
		testAcc1.withdrawMoney(BigDecimal.valueOf(6000));
	}
	
	@Test
	public void withdrawWorksWell(){
		testAcc1.withdrawMoney(BigDecimal.valueOf(600.55));
		assertEquals(BigDecimal.valueOf(399.45), testAcc1.getBalance());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void transferNullAmountParam(){
		testAcc1.transferMoney(testAcc2, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void transferZeroAmountParam(){
		testAcc1.transferMoney(testAcc2, BigDecimal.ZERO);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void transferNullAccountParam(){
		testAcc1.transferMoney(null, BigDecimal.valueOf(600));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void transferOutOfCoverage(){
		testAcc1.transferMoney(testAcc2, BigDecimal.valueOf(2000));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void transferToOwnAcc(){
		testAcc1.transferMoney(testAcc1, BigDecimal.valueOf(2000));
	}
	
	@Test
	public void transferWorksWell(){
		testAcc1.transferMoney(testAcc2, BigDecimal.valueOf(600));
		assertEquals(BigDecimal.valueOf(400), testAcc1.getBalance());
		assertEquals(BigDecimal.valueOf(600), testAcc2.getBalance());
	}
}
