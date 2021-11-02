package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderTest {

    private Client testClient;
    private Order testOrder;

    @Before
    public void setup() {
        testClient = Client.builder()
                .withName("TestClient")
                .withBalance(1000)
                .withStockBalance(Stock.A, 100)
                .build();

        testOrder = Order.builder()
                .withClient(testClient)
                .withOrderType(OrderType.BUY)
                .withStock(Stock.A)
                .withSize(1)
                .withPrice(1)
                .withStatus(OrderStatus.NEW)
                .build();
    }

    @Test
    public void builder() {
        Assert.assertEquals(testOrder.getClient(), testClient);
        Assert.assertEquals(testOrder.getOrderType(), OrderType.BUY);
        Assert.assertEquals(testOrder.getStock(), Stock.A);
        Assert.assertEquals(testOrder.getSize(), 1L);
        Assert.assertEquals(testOrder.getPrice(), 1L);
        Assert.assertEquals(testOrder.getStatus(), OrderStatus.NEW);
    }
}