////////////////////////////////////////////////////////////////////
// Riccardo Smanio 1126491
// Davide Sgrazzutti 1127436
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import java.util.List;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;

public interface Bill {
    double getOrderPrice(List<EItem> itemsOrdered, User user) throws BillException;
}