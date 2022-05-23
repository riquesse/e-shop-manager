package it.unipd.mtss.business.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BillExceptionTest {

    private BillException exception; 

    @Before
    public void initExcpetion() {
        
        //Arrange
        exception = new BillException("eccezione di prova");
    }
    
    @Test
    public void getErrorMessageTest() {

        //Act
        String error = exception.getErrorMessage();
        
        //Assert
        assertEquals("eccezione di prova", error);
    }
    
}