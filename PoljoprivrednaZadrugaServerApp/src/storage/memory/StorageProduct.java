/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage.memory;

import domain.Product;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author kicnilec
 */
public class StorageProduct {

    private List<Product> products;
    private int counter;

    public StorageProduct() {
        products = new LinkedList<>();
        counter = 0;
    }

    public void add(Product product) {
        this.products.add(product);
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public int getCounter(){
        return this.counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
