package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import resources.User;

public class UserTest {

	@Test
	public void testUserName(){
		assertEquals("John Doe", new User("John", "Doe").getName());
	}
}
