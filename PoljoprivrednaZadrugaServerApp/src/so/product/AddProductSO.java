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
public class AddProductSO extends AbstractGenericOperation {
    
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Product)) {
            throw new Exception("Invalid object (Product)");
        }
    }
    
    @Override
    protected void execute(Object entity, String keyword) throws Exception {
        databaseBroker.insert((Product) entity);
    }
    
}
