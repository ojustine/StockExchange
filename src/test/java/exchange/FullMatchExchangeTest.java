package exchange;

import model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class FullMatchExchangeTest {

    private Client testClient1;
    private Client testClient2;
    private Order testOrder1;
    private Order testOrder2;
    private Map<String, Client> clientMap;
    private List<Order> orderList;
    private Exchange exchange;

    @Before
    public void setup() {
        testClient1 = Client.builder()
                .withName("TestClient1")
                .withBalance(1)
                .withStockBalance(Stock.A, 1)
                .build();

        testClient2 = Client.builder()
                .withName("TestClient2")
                .withBalance(0)
                .withStockBalance(Stock.A, 0)
                .build();

        clientMap = Map.of(testClient1.getName(), testClient1, testClient2.getName(), testClient2);

        testOrder1 = Order.builder()
                .withClient(testClient1)
                .withOrderType(OrderType.SALE)
                .withStock(Stock.A)
                .withSize(1)
                .withPrice(1)
                .withStatus(OrderStatus.NEW)
                .build();

        testOrder2 = Order.builder()
                .withClient(testClient2)
                .withOrderType(OrderType.BUY)
                .withStock(Stock.A)
                .withSize(1)
                .withPrice(1)
                .withStatus(OrderStatus.NEW)
                .build();

        orderList = List.of(testOrder1, testOrder2);
        exchange = new FullMatchExchange(orderList);
    }

    @Test
    public void exchange() {

    }

    @Test
    public void run() {
    }

    @Test
    public void match() {
        Assert.assertTrue(exchange.match(testOrder1, testOrder2));
    }

    @Test
    public void deal() {
    }

    @Test
    public void valid() {
    }

    @Test
    public void cancel() {
    }
}