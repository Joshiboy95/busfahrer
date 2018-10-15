package de.joshuarosenberger.suffkischte;

import org.junit.Before;
import org.junit.Test;

import de.joshuarosenberger.suffkischte.framework.Player;

import static org.junit.Assert.*;

public class PlayerTest {
    Player p1;
    String testname = "p1";

    @Before
    public void initialize(){
        p1 = new Player(testname);
    }

    @Test
    public void getNameTest() {
        String name = p1.getName();
        assertEquals(testname, name);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyNameTest() {
        Player noname = new Player("");
    }

    @Test
    public void addRecievedDrinksTest() {
        assertEquals(0,  p1.getRecievedDrinks());

        p1.addRecievedDrinks(1);
        assertEquals(1, p1.getRecievedDrinks());

        p1.addRecievedDrinks(2);
        assertEquals(3, p1.getRecievedDrinks());

        p1.addRecievedDrinks(0);
        assertEquals(3, p1.getRecievedDrinks());

        p1.addRecievedDrinks(-1);
        assertEquals(2, p1.getRecievedDrinks());
    }

    @Test
    public void addHandedOutDrinksTest() {
        assertEquals(0,  p1.getHandedOutDrinks());

        p1.addHandedOutDrinks(1);
        assertEquals(1, p1.getHandedOutDrinks());

        p1.addHandedOutDrinks(2);
        assertEquals(3, p1.getHandedOutDrinks());

        p1.addHandedOutDrinks(0);
        assertEquals(3, p1.getHandedOutDrinks());

        p1.addHandedOutDrinks(-1);
        assertEquals(2, p1.getHandedOutDrinks());
    }

}
