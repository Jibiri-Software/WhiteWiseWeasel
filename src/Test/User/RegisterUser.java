package Test.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RegisterUser {
    RegisterUser user;

    @Before
    public void setUp() {
        user = new RegisterUser();
    }

    @Test
    public void testSearch() {
        assertEquals(user.searchScam("sample text"), new ArrayList<Scam>().add(new Scam("Scam1")));
        assertEquals(user.searchScam("should not be found"),new ArrayList<Scam>());
    }

    @After
    public void cleanUp() {
        user = null;
    }
}
