package it.unipd.mtss.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class EItemTest {
    
    private EItem item;

    @Before
    public void initEItem() {

        //Arrange
        item = new EItem(EItem.items.MotherBoard, "Gigabyte GA-A320M-S2H", 399);
    }

    @Test
    public void getCategoriaItemTest() {

        //Act
        EItem.items categoria = item.getCategoriaItem();

        //Arrange
        assertEquals(EItem.items.MotherBoard, categoria);
    }

    @Test
    public void getNomeTest() {

        //Act
        String nome = item.getNome();

        //Assert
        assertEquals("Gigabyte GA-A320M-S2H", nome);
    }

    @Test
    public void getPrezzoTest() {

        //Act
        double prezzo = item.getPrezzo(); 

        //Assert
        assertEquals(399, prezzo, 0.0);
    }
}