package main.java.com.jch.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollectionConcepts {

    public static void main(String[] args) {
        Product door = new Product("Wooden door", 35);
        Product floorPanel = new Product("Floor panel", 25);
        Product window = new Product("Window", 10);

        Collection<Product> products = new ArrayList<>();
        products.add(door);
        products.add(floorPanel);
        products.add(window);

        Iterator<Product> productIterator = products.iterator();
        while (productIterator.hasNext()) {
            Product product = productIterator.next();
            System.out.println(product);
        }
        // Sugar. Will transpile to the above.
        for (Product product: products) {
            System.out.println(product);
        }
        System.out.println("=========");
        productIterator = products.iterator();
        while (productIterator.hasNext()) {
            Product product = productIterator.next();
            if (product.getWeight() > 20) {

                // Generates concurrent modification exception when next hasNext is executed
                // Will also fail in a for () loop, and with any modification on the list
                // products.remove(product);
                productIterator.remove();
            }
            System.out.println(product);
            System.out.println(products);
            System.out.println(productIterator);
        }
        System.out.println("=========");

        String testingContainsBehavior = new String ("a");
        // Containts does not check for generic type
        System.out.println(products.contains(testingContainsBehavior));

    }
}
