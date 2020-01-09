package main.java.com.jch.collections;

import org.junit.jupiter.api.*;

class ShipmentTest {

    private Shipment shipment = new Shipment();

    @Test
    void shouldAddItems() {
        shipment.add(ProductLine.door);
        shipment.add(ProductLine.floorPanel);

        Assertions.(shipment.iterator().);
    }

    @Test
    void shouldReplaceItem() {
    }

}