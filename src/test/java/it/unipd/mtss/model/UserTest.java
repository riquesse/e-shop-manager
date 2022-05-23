package it.unipd.mtss.model;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class UserTest {

    private User user;

    @Before
    public void initUser() {

        //Arrange
        user = new User("Paolo", "Cannone", LocalDate.of(1996,5,13),"ZPFRDM65A05C567D");
    }

    @Test
    public void getNomeTest() {

        //Act
        String name = user.getNome();

        //Assert
        assertEquals("Paolo", name);
    }

    @Test
    public void getCognomeTest() {

        //Act
        String lastName = user.getCognome();

        //Assert
        assertEquals("Cannone", lastName);
    }

    @Test
    public void getDataNascitaTest() {

        //Act
        LocalDate dataNascita = user.getDataNascita();

        //Assert
        assertEquals(LocalDate.of(1996, 5, 13), dataNascita);
    }

    @Test
    public void getCodiceFiscaleTest() {

        //Act
        String CF = user.getCodiceFiscale();

        //Assert
        assertEquals("ZPFRDM65A05C567D", CF);
    }
    
    @Test
    public void isMaggiorenneTest() {

        //Act
        boolean isMaggiorenne = user.isMaggiorenne();

        //Assert
        assertTrue(isMaggiorenne);
    }

    @Test
    public void isNotMaggiorenneTest() {

        //Arrange
        User newUser = new User("Paolo", "Valeri", LocalDate.of(2007,5,13), "ZPFRDM65A05C567D");

        //Act
        boolean isMaggiorenne = newUser.isMaggiorenne();

        //Assert
        assertFalse(isMaggiorenne);
    }

    
}