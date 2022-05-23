////////////////////////////////////////////////////////////////////
// Riccardo Smanio 1126491
// Davide Sgrazzutti 1127436
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import java.util.List;
import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;

public class BillImpl implements Bill {

    @Override
    public double getOrderPrice(List<EItem> itemsOrdered, User user) throws BillException{

        double totale = 0;
        int cpuCounter = 0;
        EItem cheapestCpu=null;

        for(EItem item : itemsOrdered){
        
            totale += item.getPrezzo();
        
            if(item.getCategoriaItem() == EItem.items.Processor) {
                cheapestCpu = updateCheapestCpu(cheapestCpu, item);
                cpuCounter += 1;
            }
        }

        totale -= getCpuDiscount(cpuCounter, cheapestCpu);
        
        return totale;

    }

    private double getCpuDiscount(int count, EItem cheapestCpu) {
        if(count > 5) {
            return cheapestCpu.getPrezzo()/2;
        }
        return 0; 
    }

    private EItem updateCheapestCpu(EItem cheapestCpu, EItem item) {
        if(cheapestCpu == null || item.getPrezzo() < cheapestCpu.getPrezzo()) {
            cheapestCpu = item;
        }
        return cheapestCpu;
    }
    
}