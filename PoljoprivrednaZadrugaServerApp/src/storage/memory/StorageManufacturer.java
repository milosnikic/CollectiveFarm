/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage.memory;

import domain.Manufacturer;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author kicnilec
 */
public class StorageManufacturer {

    private List<Manufacturer> manufactures;

    public StorageManufacturer() {
        manufactures = new LinkedList<>();
        initManufactures();
    }

    private void initManufactures() {
//        manufactures.add(new Manufacturer(1L, "Breskvik"));
//        manufactures.add(new Manufacturer(2L, "Krčedin"));
//        manufactures.add(new Manufacturer(3L, "Beška"));
//        manufactures.add(new Manufacturer(4L, "Stari Banovci - Kablar"));
    }

    public List<Manufacturer> getManufactures() {
        return manufactures;
    }
}
