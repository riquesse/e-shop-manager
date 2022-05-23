////////////////////////////////////////////////////////////////////
// Riccardo Smanio 1126491
// Davide Sgrazzutti 1127436
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business.exception;

public class BillException extends Throwable {
    private String errore;

    public BillException(String errore) {
        this.errore = errore;
    }

    public String getErrorMessage() {
        return this.errore;
    }
}