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

    @Test
    public void regaloArticoloMenoCaroTest() {

        //Arrange
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 1", 19));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 2", 15));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 3", 29));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 1", 40));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 2", 19));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 3", 69));
        bill = new BillImpl();
        User user = new User("Riccardo", "Smanio", LocalDate.of(1997,6,15), "ZPFRDM65A05C567D");

        //Act
        try {
            totale += bill.getOrderPrice(itemsOrdered, user);
        }
        catch(BillException exc) {
            exc.getErrorMessage();
        }

        //Assert
        assertEquals(176, totale, 0.0);
    }

    @Test
    public void regaloArticoloMenoCaroTest2() {

        //Arrange
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 1", 19));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 2", 15));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 3", 29));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 1", 40));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 2", 19));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 3", 69));
        itemsOrdered.add(new EItem(EItem.items.MotherBoard, "Motherboard 1", 10));
        itemsOrdered.add(new EItem(EItem.items.Processor, "Processore 1", 8));
        bill = new BillImpl();
        User user = new User("Riccardo", "Smanio", LocalDate.of(1997,6,15), "ZPFRDM65A05C567D");

        //Act
        try {
            totale += bill.getOrderPrice(itemsOrdered, user);
        }
        catch(BillException exc) {
            exc.getErrorMessage();
        }

        //Assert
        assertEquals(201, totale, 0.0);
    }

    @Test
    public void regaloArticoloMenoCaroTest3() {

        //Arrange
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 1", 8));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 2", 12));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 3", 5));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 4", 13));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 5", 10));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 6", 6));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 7", 4));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 8", 14));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 9", 11));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 10", 9));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 11", 7));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 1", 15));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 2", 16));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 3", 17));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 4", 18));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 5", 19));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 6", 20));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 7", 21));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 8", 22));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 9", 23));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 10", 24));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 11", 25));
        itemsOrdered.add(new EItem(EItem.items.MotherBoard, "Motherboard 1", 150));
        itemsOrdered.add(new EItem(EItem.items.Processor, "Processore 1", 200));
        bill = new BillImpl();
        User user = new User("Riccardo", "Smanio", LocalDate.of(1997,6,15), "ZPFRDM65A05C567D");

        //Act
        try {
            totale += bill.getOrderPrice(itemsOrdered, user);
        }
        catch(BillException exc) {
            exc.getErrorMessage();
        }

        //Assert
        assertEquals(660, totale, 0.0);
    }

    @Test
    public void regaloArticoloMenoCaroTest4() {

        //Arrange
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 1", 8));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 2", 12));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 3", 5));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 4", 13));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 5", 10));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 6", 6));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 7", 4));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 8", 14));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 9", 11));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 10", 9));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 11", 7));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 1", 15));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 2", 16));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 3", 17));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 4", 18));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 5", 19));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 6", 20));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 7", 21));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 8", 22));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 9", 23));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 10", 24));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 11", 25));
        itemsOrdered.add(new EItem(EItem.items.MotherBoard, "Motherboard 1", 3));
        itemsOrdered.add(new EItem(EItem.items.Processor, "Processore 1", 200));
        bill = new BillImpl();
        User user = new User("Riccardo", "Smanio", LocalDate.of(1997,6,15), "ZPFRDM65A05C567D");

        //Act
        try {
            totale = bill.getOrderPrice(itemsOrdered, user);
        }
        catch(BillException exc) {
            exc.getErrorMessage();
        }
        
        //Assert
        assertEquals(515, totale, 0.0);
    }

    @Test
    public void totaleSopraMilleEuroTest() {
        
        //Arrange
        itemsOrdered.add(new EItem(EItem.items.Processor, "Processore", 499));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera", 199));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse", 129));
        itemsOrdered.add(new EItem(EItem.items.MotherBoard, "Scheda Madre", 399));
        bill = new BillImpl();
        User user = new User("Riccardo", "Smanio", LocalDate.of(1997,6,15),"ZPFRDM65A05C567D");

        //Act
        try{
            totale += bill.getOrderPrice(itemsOrdered, user);
        }
        catch(BillException exc) {
            exc.getErrorMessage();
        }

        //Assert
        assertEquals(987.3, totale, 0.0);
    }

    @Test(expected = BillException.class)
    public void piùDi30ArticoliTest() throws BillException{

        for(int i=0; i<40; ++i)
            itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera", 199));

        User user = new User("Riccardo", "Smanio", LocalDate.of(1997,6,15), "ZPFRDM65A05C567D");
        bill = new BillImpl();

        bill.getOrderPrice(itemsOrdered, user);       
    } 

    @Test
    public void commissione2euro() {

        //Arrange
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse", 5));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera", 4.9));
        bill = new BillImpl();
        User user = new User("Riccardo", "Smanio", LocalDate.of(1997,6,15), "ZPFRDM65A05C567D");

        //Act
        try{
            totale += bill.getOrderPrice(itemsOrdered, user);
        }
        catch(BillException exc) {
            exc.getErrorMessage();
        }

        //Assert
        assertEquals(7, totale, 0.0);
    }

    @Test
    public void allDiscountsTest() {

        //Arrange
        itemsOrdered.add(new EItem(items.Processor, " ", 79));
        itemsOrdered.add(new EItem(items.Processor, " ", 189));
        itemsOrdered.add(new EItem(items.Processor, " ", 249));
        itemsOrdered.add(new EItem(items.Processor, " ", 99));
        itemsOrdered.add(new EItem(items.Processor, " ", 129));
        itemsOrdered.add(new EItem(items.Processor, " ", 199));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 1", 8));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 2", 12));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 3", 5));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 4", 13));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 5", 10));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 6", 6));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 7", 4));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 8", 14));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 9", 11));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 10", 9));
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse 11", 7));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 1", 15));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 2", 16));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 3", 17));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 4", 18));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 5", 19));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 6", 20));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 7", 21));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 8", 22));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 9", 23));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 10", 24));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera 11", 25));
        bill = new BillImpl();
        User user = new User("Riccardo", "Smanio", LocalDate.of(1997,6,15), "ZPFRDM65A05C567D");

        //Act
        try{
            totale += bill.getOrderPrice(itemsOrdered, user);
        }
        catch(BillException exc) {
            exc.getErrorMessage();
        }

        //Assert
        assertEquals(1093.05, totale, 0.0);
    }
}