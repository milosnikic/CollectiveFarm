/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.product;

import domain.Product;
import so.AbstractGenericOperation;

/**
 *
 * @author kicnilec
 */
public class DeleteProductSO extends AbstractGenericOperation {

    private boolean success;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Long)) {
            throw new Exception("Invalid object (Long)");
        }
    }

    @Override
    protected void execute(Object entity, String keyword) throws Exception {
        Product p = new Product();
        p.setId((Long) entity);
        Product product = (Product) databaseBroker.getById(p, keyword);
        if (product != null) {
            success = true;
            databaseBroker.delete(product);
        } else {
            success = false;
        }
        
    }

    public boolean isSuccess() {
        return success;
    }

}
