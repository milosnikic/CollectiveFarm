/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.redemptionbill;

import domain.GeneralEntity;
import domain.Manufacturer;
import domain.Product;
import domain.RedemptionBill;
import domain.RedemptionBillItem;
import domain.User;
import java.util.LinkedList;
import java.util.List;
import so.AbstractGenericOperation;

/**
 *
 * @author kicnilec
 */
public class GetRedemptionBillItemsSO extends AbstractGenericOperation {
    
    private LinkedList<GeneralEntity> list;
    
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof RedemptionBill)) {
            throw new Exception("Invalid object (RedemptionBill)");
        }
    }
    
    @Override
    protected void execute(Object entity, String keyword) throws Exception {
        RedemptionBill rb = (RedemptionBill) entity;
        
        String condition = " redemption_bill_id=" + rb.getId();
        List<GeneralEntity> items = databaseBroker.getByCondition(new RedemptionBillItem(), null, condition);
        for (GeneralEntity i : items) {
            RedemptionBillItem item = (RedemptionBillItem) i;
            item.setRedemptionBill(rb);
            Product product = (Product) databaseBroker.getById(item.getProduct(), null);
            item.setProduct(product);
        }
        list = (LinkedList<GeneralEntity>) items;
    }
    
    public LinkedList<GeneralEntity> getList() {
        return list;
    }
    
}
