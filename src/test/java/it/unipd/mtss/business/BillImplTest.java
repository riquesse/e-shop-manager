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
import static org.junit.Assert.fail;



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
        bill = new BillImpl(LocalTime.now(), LocalDate.now());
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
        bill = new BillImpl(LocalTime.now(), LocalDate.now());
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
        bill = new BillImpl(LocalTime.now(), LocalDate.now());
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
        bill = new BillImpl(LocalTime.now(), LocalDate.now());
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
        bill = new BillImpl(LocalTime.now(), LocalDate.now());
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
        bill = new BillImpl(LocalTime.now(), LocalDate.now());
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
        bill = new BillImpl(LocalTime.now(), LocalDate.now());
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
        bill = new BillImpl(LocalTime.now(), LocalDate.now());
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
        bill = new BillImpl(LocalTime.now(), LocalDate.now());

        bill.getOrderPrice(itemsOrdered, user);       
    } 

    @Test
    public void commissione2euro() {

        //Arrange
        itemsOrdered.add(new EItem(EItem.items.Mouse, "Mouse", 5));
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "Tastiera", 4.9));
        bill = new BillImpl(LocalTime.now(), LocalDate.now());
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
        bill = new BillImpl(LocalTime.now(), LocalDate.now());
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

    @Test
    public void orderGiftedTest() {

        //Arrange
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "prova", 5));
        User user = new User("Riccardo", "Rossi", LocalDate.of(2006, 5, 20), "ZPFRDM65A05C567D");
        bill = new BillImpl(LocalTime.of(18, 20, 10), LocalDate.of(2022, 5, 16));

        //Act
        try {
            totale += bill.getOrderPrice(itemsOrdered, user);
        }
        catch(BillException exc) {
            exc.getErrorMessage();
        }

        //Assert
        assertEquals(0, totale, 0.0);
    }

    @Test
    public void orderGiftedSameUserTest() {

        //Arrange
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "prova", 5));
        User user = new User("Riccardo", "Rossi", LocalDate.of(2006, 5, 20), "ZPFRDM65A05C567D");
        bill = new BillImpl(LocalTime.of(18, 20, 10), LocalDate.of(2022, 4, 21));
        BillImpl secondBill = new BillImpl(LocalTime.of(18, 59, 40), LocalDate.of(2022, 4, 21));

        //Act
        try {
            totale += bill.getOrderPrice(itemsOrdered, user) + secondBill.getOrderPrice(itemsOrdered, user);
        }
        catch(BillException exc) {
            exc.getErrorMessage();
        }

        //Assert
        assertEquals(7, totale, 0.0);
    }

    @Test
    public void orderGiftedSameUserDifferentDayTest() {

        //Arrange
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "prova", 5));
        User user = new User("Riccardo", "Rossi", LocalDate.of(2006, 5, 20), "ZPFRDM65A05C567D");
        bill = new BillImpl(LocalTime.of(18, 20, 10), LocalDate.of(2022, 4, 30));
        BillImpl secondBill = new BillImpl(LocalTime.of(18, 20, 10), LocalDate.of(2022, 5, 30));

        //Act
        try {
            totale += bill.getOrderPrice(itemsOrdered, user);
            totale += secondBill.getOrderPrice(itemsOrdered, user);
        }
        catch(BillException exc) {
            exc.getErrorMessage();
        }

        //Assert
        assertEquals(0, totale, 0.0);
    }

    @Test
    public void orderNotGiftedFrom18To19() {
        
        //Arrange
        User user = new User("Riccardo", "Rossi", LocalDate.of(2006, 5, 20), "ZPFRDM65A05C567D");
        itemsOrdered.add(new EItem(EItem.items.Keyboard, "prova", 5));
        bill = new BillImpl(LocalTime.of(18, 20, 15), LocalDate.of(2020, 05, 22));

        //Act
        try {
            totale += bill.getOrderPrice(itemsOrdered, user);
        }
        catch(BillException exc) {
            exc.getErrorMessage();
        }

        //Assert
        assertEquals(7, totale, 0.0);
    }


    @Test
    public void ordersNotFreeAfter10GiftedOrders() {

        //Arrange
        User user1 = new User("Riccardo", "Rossi", LocalDate.of(2006, 5, 20), "SIWZHBYNA2UJ1K2Y");
        User user2 = new User("Paolo", "Rossi", LocalDate.of(2006, 5, 20), "R06U6S2O4ITVS9WW");
        User user3 = new User("Mario", "Rossi", LocalDate.of(2006, 5, 20), "QBQJ2RFF48IUSE9S");
        User user4 = new User("Luca", "Rossi", LocalDate.of(2006, 5, 20), "UJ25UBDYW5XEPRZI");
        User user5 = new User("Francesco", "Rossi", LocalDate.of(2006, 5, 20), "PH0PARYLQRVKUCZQ");
        User user6 = new User("Matteo", "Rossi", LocalDate.of(2006, 5, 20), "IN0PEMDHSDE4ICR9");
        User user7 = new User("Mattia", "Rossi", LocalDate.of(2006, 5, 20), "N04SD56KPFMT7P5E");
        User user8 = new User("Franco", "Rossi", LocalDate.of(2006, 5, 20), "OTAGBIS9GA6DFK4J");
        User user9 = new User("Roberto", "Rossi", LocalDate.of(2006, 5, 20), "O6LWK9MM57RFGOK0");
        User user10 = new User("Leonardo", "Rossi", LocalDate.of(2006, 5, 20), "D19ZBPLB46D3FM8E");
        User user11 = new User("Antonio", "Rossi", LocalDate.of(2006, 5, 20), "DVI9KW9B27K8DTFF");
        User user12 = new User("Andrea", "Rossi", LocalDate.of(2006, 5, 20), "MP32PYYOV6GLW7OO");
        itemsOrdered.add(new EItem(EItem.items.Processor, "CPU", 10));
        bill = new BillImpl(LocalTime.of(18, 20, 20), LocalDate.of(2023, 5, 22));

        //Act
        try {
            totale += bill.getOrderPrice(itemsOrdered, user1);
            totale += bill.getOrderPrice(itemsOrdered, user2);
            totale += bill.getOrderPrice(itemsOrdered, user3);
            totale += bill.getOrderPrice(itemsOrdered, user4);
            totale += bill.getOrderPrice(itemsOrdered, user5);
            totale += bill.getOrderPrice(itemsOrdered, user6);
            totale += bill.getOrderPrice(itemsOrdered, user7);
            totale += bill.getOrderPrice(itemsOrdered, user8);
            totale += bill.getOrderPrice(itemsOrdered, user9);
            totale += bill.getOrderPrice(itemsOrdered, user10);
            totale += bill.getOrderPrice(itemsOrdered, user11);
            totale += bill.getOrderPrice(itemsOrdered, user12);
        }
        catch(BillException exc) {
            exc.getErrorMessage();
        }

        //Assert
        assertEquals(20, totale, 0);
    }

    @Test
    public void freeOrdersListUpdateAfterDateChangedTest() {

        //Arrange
        User user1 = new User("Riccardo", "Rossi", LocalDate.of(2006, 5, 20), "SIWZHBYNA2UJ1K2Y");
        User user2 = new User("Paolo", "Rossi", LocalDate.of(2006, 5, 20), "R06U6S2O4ITVS9WW");
        User user3 = new User("Mario", "Rossi", LocalDate.of(2006, 5, 20), "QBQJ2RFF48IUSE9S");
        User user4 = new User("Luca", "Rossi", LocalDate.of(2006, 5, 20), "UJ25UBDYW5XEPRZI");
        User user5 = new User("Francesco", "Rossi", LocalDate.of(2006, 5, 20), "PH0PARYLQRVKUCZQ");
        User user6 = new User("Matteo", "Rossi", LocalDate.of(2006, 5, 20), "IN0PEMDHSDE4ICR9");
        User user7 = new User("Mattia", "Rossi", LocalDate.of(2006, 5, 20), "N04SD56KPFMT7P5E");
        User user8 = new User("Franco", "Rossi", LocalDate.of(2006, 5, 20), "OTAGBIS9GA6DFK4J");
        User user9 = new User("Roberto", "Rossi", LocalDate.of(2006, 5, 20), "O6LWK9MM57RFGOK0");
        User user10 = new User("Leonardo", "Rossi", LocalDate.of(2006, 5, 20), "D19ZBPLB46D3FM8E");
        User user11 = new User("Antonio", "Rossi", LocalDate.of(2006, 5, 20), "DVI9KW9B27K8DTFF");
        User user12 = new User("Andrea", "Rossi", LocalDate.of(2006, 5, 20), "MP32PYYOV6GLW7OO");
        itemsOrdered.add(new EItem(EItem.items.Processor, "CPU", 10));
        bill = new BillImpl(LocalTime.of(18, 20, 20), LocalDate.of(2023, 1, 2));
        BillImpl secondBill = new BillImpl(LocalTime.of(18, 20, 20), LocalDate.of(2023, 1, 3));
        double nextDayTotal = 0;

        //Act
        try {
            totale += bill.getOrderPrice(itemsOrdered, user1);
            totale += bill.getOrderPrice(itemsOrdered, user2);
            totale += bill.getOrderPrice(itemsOrdered, user3);
            totale += bill.getOrderPrice(itemsOrdered, user4);
            totale += bill.getOrderPrice(itemsOrdered, user5);
            totale += bill.getOrderPrice(itemsOrdered, user6);
            totale += bill.getOrderPrice(itemsOrdered, user7);
            totale += bill.getOrderPrice(itemsOrdered, user8);
            totale += bill.getOrderPrice(itemsOrdered, user9);
            totale += bill.getOrderPrice(itemsOrdered, user10);
            totale += bill.getOrderPrice(itemsOrdered, user11);
            totale += bill.getOrderPrice(itemsOrdered, user12);

            nextDayTotal += secondBill.getOrderPrice(itemsOrdered, user12);
            nextDayTotal += secondBill.getOrderPrice(itemsOrdered, user11);
            nextDayTotal += secondBill.getOrderPrice(itemsOrdered, user10);
            nextDayTotal += secondBill.getOrderPrice(itemsOrdered, user9);
            nextDayTotal += secondBill.getOrderPrice(itemsOrdered, user8);
            nextDayTotal += secondBill.getOrderPrice(itemsOrdered, user7);

        }
        catch(BillException exc) {
            exc.getErrorMessage();
        }

        //Assert
        assertEquals(20, totale, 0);
        assertEquals(0, nextDayTotal, 0);
    }
}