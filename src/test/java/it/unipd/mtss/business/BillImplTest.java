package it.unipd.mtss.business;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;
import it.unipd.mtss.model.EItem.items;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;



public class BillImplTest {

    BillImpl bill;
    List<EItem> itemsOrdered;
    double totale;

    @Before
    public void initItemsOrderedList() {
        
        //Arrange
        itemsOrdered = new ArrayList<EItem>();
    }

    @Before
    public void initTotale() {

        //Arrange
        totale = 0;
    }

    @Test
    public void simpleTotalWithoutDiscountsTest() {

        //Arrange
        itemsOrdered.add(new EItem(items.MotherBoard, "Gigabyte GA-A320M-S2H", 399));
        itemsOrdered.add(new EItem(items.Keyboard, "Logitech K120", 39));
        itemsOrdered.add(new EItem(items.Keyboard, "Trust GXT 835", 24));
        itemsOrdered.add(new EItem(items.Mouse, "Logitech MX Master 3", 29));
        itemsOrdered.add(new EItem(items.Processor, "IntelCore i7-1165g7", 449));
        bill = new BillImpl();
        User user = new User("Riccardo", "Smanio", LocalDate.of(1997, 6, 15), "ZPFRDM65A05C567D");

        //Act
        try {
            totale += bill.getOrderPrice(itemsOrdered, user);
        }
        catch(BillException exc) {
            exc.getErrorMessage();
        }

        //Assert
        assertEquals(940, totale, 0.0);
    } 

    @Test
    public void scontoCPUTest() {

        //Arrange
        itemsOrdered.add(new EItem(items.Processor, " ", 79));
        itemsOrdered.add(new EItem(items.Processor, " ", 189));
        itemsOrdered.add(new EItem(items.Processor, " ", 249));
        itemsOrdered.add(new EItem(items.Processor, " ", 99));
        itemsOrdered.add(new EItem(items.Processor, " ", 129));
        itemsOrdered.add(new EItem(items.Processor, " ", 199));
        bill = new BillImpl();
        User user = new User("Riccardo", "Smanio", LocalDate.of(1997, 6, 15), "ZPFRDM65A05C567D");

        //Act
        try {
            totale += bill.getOrderPrice(itemsOrdered, user); 
        }
        catch(BillException exc) {
            exc.getErrorMessage();
        }

        //Assert
        assertEquals( 904.5, totale, 0.0);
    }

    @Test
    public void regaloMouseTest() {

        //Arrange
        for(int i=0; i<=10; ++i){
            itemsOrdered.add(new EItem(EItem.items.Mouse, "Logitech G20", 29));
        }
        bill = new BillImpl();
        User user = new User("Riccardo", "Smanio", LocalDate.of(1997, 6, 15), "ZPFRDM65A05C567D");
        double total = 0;

        //Act
        try {
            total += bill.getOrderPrice(itemsOrdered, user); 
        }
        catch(BillException exc) {
            exc.getErrorMessage();
        }

        //Assert
        assertEquals(290, total, 0.0);
    }
}