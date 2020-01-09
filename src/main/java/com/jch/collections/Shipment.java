package main.java.com.jch.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Shipment implements Iterable<Product> {

    private static final int LIGHT_VAN_MAX_WEIGHT = 20;

    private final List<Product> products = new ArrayList<>();


    public void add (Product product) {

    }
    public void replace (Product oldProduct, Product newProduct) {

    }


    @Override
    public Iterator<Product> iterator() {
        return products.iterator();
    }
}
