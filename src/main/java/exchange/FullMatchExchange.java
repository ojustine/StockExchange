package exchange;

import model.Client;
import model.Order;
import model.OrderStatus;
import model.OrderType;

import java.util.List;

public class FullMatchExchange extends Exchange {

    public FullMatchExchange(List<? extends Order> orders) {
        super(orders);
    }

    @Override
    public boolean match(Order order1, Order order2) {
        boolean result;

        result = order1.getOrderType() != order2.getOrderType();
        result &= order1.getClient() != order2.getClient();
        result &= order1.getStock() == order2.getStock();
        result &= order1.getPrice() == order2.getPrice();
        result &= order1.getSize() == order2.getSize();
        result &= order1.getClient().getStockBalances().get(order2.getStock()) != null;
        result &= order2.getClient().getStockBalances().get(order1.getStock()) != null;

        return result;
    }

    @Override
    public void deal(Order order1, Order order2) {
        order1.getClient().apply(order2);
        order2.getClient().apply(order1);
        order1.setStatus(OrderStatus.COMPLETED);
        order2.setStatus(OrderStatus.COMPLETED);
    }

    @Override
    public boolean valid(Order order) {
        Client client = order.getClient();
        boolean result = false;

        if (order.getStatus() == OrderStatus.NEW) {
            if (order.getOrderType() == OrderType.BUY) {
                result = client.getBalance() >= order.getSize() * order.getPrice();
            } else if (order.getOrderType() == OrderType.SALE) {
                result = client.getStockBalance(order.getStock()) >= order.getSize();
            }
        }

        return result;
    }

    @Override
    public void cancel(Order order) {
        order.setStatus(OrderStatus.CANCELLED);
    }
}
