////////////////////////////////////////////////////////////////////
// Riccardo Smanio 1126491
// Davide Sgrazzutti 1127436
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class BillImpl implements Bill {

    private static LocalDate lastOrderDate = null;
    private static final ArrayList<User> giftedOrders = new ArrayList<User>();
    private LocalTime time;
    private LocalDate date;

    public BillImpl(LocalTime time, LocalDate date) {
        this.time = time;
        this.date = date;
    }

    @Override
    public double getOrderPrice(List<EItem> itemsOrdered, User user) throws BillException{

        if(giveAway(user)) {
            return 0;
        }

        if(itemsOrdered.size()>30){
            throw new BillException("Too many items");
        }

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
        else if(totale < 10) {
            totale += 2;
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

    private boolean giveAway(User user) {

        if(this.date.equals(lastOrderDate) == false){
            lastOrderDate = date;
            giftedOrders.clear();
        }

        if(time.getHour() >= 18 && time.getHour()<= 19){
            if(!user.isMaggiorenne() && time.getSecond() % 2 == 0){
                if(BillImpl.giftedOrders.size() < 10 && giftedOrders.contains(user) == false){
                    BillImpl.giftedOrders.add(user);
                    return true;
                }
            }
        }
        return false;
    }
}