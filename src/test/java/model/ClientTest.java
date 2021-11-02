package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClientTest {
    private Order testOrder;
    private Client testClient;

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
        Assert.assertEquals(testClient.getBalance(), 1000L);
        Assert.assertEquals(testClient.getName(), "TestClient");
        Assert.assertEquals(testClient.getStockBalance(Stock.A), 100L);
    }

    @Test
    public void apply() {
        testClient.apply(testOrder);
    }
}