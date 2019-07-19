/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.redemptionbill;

import domain.GeneralEntity;
import domain.Manufacturer;
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
public class GetAllRedemptionBillsSO extends AbstractGenericOperation {

    private LinkedList<GeneralEntity> list;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof RedemptionBill)) {
            throw new Exception("Invalid object (RedemptionBill)");
        }
    }

    @Override
    protected void execute(Object entity, String keyword) throws Exception {
        LinkedList<GeneralEntity> redemptionBills = (LinkedList<GeneralEntity>) databaseBroker.getAll((RedemptionBill) entity, null);
        list = new LinkedList<>();
        for (GeneralEntity redemptionBill : redemptionBills) {
            RedemptionBill r = (RedemptionBill) redemptionBill;

            List items = null;
            items = (LinkedList<GeneralEntity>) databaseBroker.getByCondition(new RedemptionBillItem(), null, " redemption_bill_id=" + r.getId());
            User user = (User) databaseBroker.getById(r.getUserCreated(), null);
            Manufacturer manufacturer = (Manufacturer) databaseBroker.getById(r.getManufacturer(), null);
            r.setManufacturer(manufacturer);
            r.setUserCreated(user);
            r.setItems(items);
            System.out.println(r);
            list.add(r);
        }
    }

    public LinkedList<GeneralEntity> getList() {
        return list;
    }

}
