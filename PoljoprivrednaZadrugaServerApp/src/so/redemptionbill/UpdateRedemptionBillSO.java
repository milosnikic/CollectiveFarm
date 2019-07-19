/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.redemptionbill;

import domain.GeneralEntity;
import domain.RedemptionBill;
import domain.RedemptionBillItem;
import java.util.LinkedList;
import java.util.List;
import so.AbstractGenericOperation;

/**
 *
 * @author kicnilec
 */
public class UpdateRedemptionBillSO extends AbstractGenericOperation {
    
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof RedemptionBill)) {
            throw new Exception("Invalid object (RedemptionBill)");
        }
    }
    
    @Override
    protected void execute(Object entity, String keyword) throws Exception {
        RedemptionBill rb = (RedemptionBill) entity;
        System.out.println(rb.getDate() + " " + rb.getManufacturer().getName());
        //Prvo cemo naci sve stavke koje pripadaju otkupnom listu
        List<GeneralEntity> dbItems = databaseBroker.getByCondition(new RedemptionBillItem(), null, "redemption_bill_id=" + rb.getId());
        for (GeneralEntity dbItem : dbItems) {
            RedemptionBillItem rbi = (RedemptionBillItem) dbItem;
            System.out.println("BRISANJE:");
            databaseBroker.delete(rbi);
        }
        LinkedList<RedemptionBillItem> items = (LinkedList<RedemptionBillItem>) rb.getItems();
        for (RedemptionBillItem item : items) {
            databaseBroker.insert(item);
        }
        databaseBroker.update(rb);
    }
    
}
