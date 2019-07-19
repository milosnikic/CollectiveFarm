/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.redemptionbill;

import domain.GeneralEntity;
import domain.Product;
import domain.RedemptionBill;
import domain.RedemptionBillItem;
import java.util.LinkedList;
import so.AbstractGenericOperation;

/**
 *
 * @author kicnilec
 */
public class GetStorageInfoSO extends AbstractGenericOperation {

    private LinkedList<GeneralEntity> list;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof RedemptionBillItem)) {
            throw new Exception("Invalid object (RedemptionBillItem)");
        }
    }

    @Override
    protected void execute(Object entity, String keyword) throws Exception {
//        GeneralEntity entity, String attrs, String join, String condition, String groupBy, String orderBy
        list = (LinkedList<GeneralEntity>) databaseBroker.getAll((RedemptionBillItem) entity,
                "redemption_bill_id,product_id,item_no,amount,name,description,p.price,p.category,p.measurement_unit,SUM(quantity) AS quantity",
                 " rb JOIN product p ON rb.product_id = p.id", null, "name,description,p.category ", "name,description,p.category ");
        for (GeneralEntity generalEntity : list) {
            RedemptionBillItem item = (RedemptionBillItem)generalEntity;
            Product p = (Product) databaseBroker.getById(item.getProduct(), null);
            item.setProduct(p);
        }
    }

    public LinkedList<GeneralEntity> getList() {
        return list;
    }

}
