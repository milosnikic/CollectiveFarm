/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.redemptionbill;

import domain.RedemptionBill;
import domain.RedemptionBillItem;
import so.AbstractGenericOperation;

/**
 *
 * @author kicnilec
 */
public class SaveRedemptionBillSO extends AbstractGenericOperation {

    private boolean success;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof RedemptionBill)) {
            throw new Exception("Invalid object (RedemptionBill)");
        }
    }

    @Override
    protected void execute(Object entity, String keyword) throws Exception {
        databaseBroker.insert((RedemptionBill) entity);
        for (Object i : ((RedemptionBill) entity).getItems()) {
            RedemptionBillItem item = (RedemptionBillItem) i;
            RedemptionBill rb = (RedemptionBill) entity;
            Long id = databaseBroker.getLastId(rb);
            System.out.println(id);
            rb.setId(id);
            System.out.println(rb.getId() + " " + rb.getDate());
            item.setRedemptionBill(rb);
            databaseBroker.insert(item);
        }
        success = true;
    }

    public boolean isSuccess() {
        return success;
    }
}
