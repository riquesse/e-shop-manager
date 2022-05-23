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
        int cpuCounter = 0, mouseCounter = 0, keyboardCounter = 0;
        EItem cheapestMouse = null, cheapestCpu = null;
        EItem cheapestItem = null, secondCheapestItem = null;

        for(EItem item : itemsOrdered){
        
            totale += item.getPrezzo();
        
            if(item.getCategoriaItem() == EItem.items.Processor) {
                cheapestCpu = updateCheapestCpu(cheapestCpu, item);
                cpuCounter += 1;
            }

            if(item.getCategoriaItem() == EItem.items.Mouse) {
                cheapestMouse = updateCheapestMouse(cheapestMouse, item);
                mouseCounter += 1;
            }

            if(item.getCategoriaItem() == EItem.items.Keyboard){
                keyboardCounter += 1;
            }

            if(cheapestItem == null || item.getPrezzo() < cheapestItem.getPrezzo()) {
                secondCheapestItem = cheapestItem;
                cheapestItem = item;
            }
            else if(secondCheapestItem == null || item.getPrezzo() < secondCheapestItem.getPrezzo()){
                secondCheapestItem = item;
            }
        }

        totale -= getCpuDiscount(cpuCounter, cheapestCpu);

        double mouseDiscount = getMouseDiscount(mouseCounter, cheapestMouse);

        totale -= mouseDiscount;

        if(mouseDiscount > 0 && cheapestItem == cheapestMouse){
            totale -= getCheaperItemDiscount(mouseCounter, keyboardCounter, secondCheapestItem);
        }
        else {
            totale -= getCheaperItemDiscount(mouseCounter, keyboardCounter, cheapestItem);
        }
        
        if(totale > 1000) {
            totale -= (totale/100)*10;
        }

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

    private double getMouseDiscount(int count, EItem cheapestMouse) {
        if(count > 10) {
            return cheapestMouse.getPrezzo();
        }
        return 0;
    }   

    private EItem updateCheapestMouse(EItem cheapestMouse, EItem item) {
        if(cheapestMouse == null || item.getPrezzo() < cheapestMouse.getPrezzo()) {
            cheapestMouse = item;
        }
        return cheapestMouse;
    }

    private double getCheaperItemDiscount(int mouseCount, int keyboardCount, EItem cheapestItem) {
        if(mouseCount > 0 && mouseCount == keyboardCount) {
            return cheapestItem.getPrezzo();
        }
        return 0;
    }
}