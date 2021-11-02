package model;

import java.util.*;

public class Client {

    private final String name;
    private long balance;
    private final Map<Stock, Long> stockBalances;
    private final List<Order> history;

    private Client(String name, long balance, Map<Stock, Long> stockBalances) {
        this.name = name;
        this.balance = balance;
        this.stockBalances = stockBalances;
        this.history = new LinkedList<>();
    }

    public void apply(Order order) {
        Stock stock = order.getStock();
        long size = order.getSize();
        long amount = order.getSize() * order.getPrice();

        if (order.getOrderType() == OrderType.BUY) {
            balance += amount;
            stockBalances.compute(stock, (k, v) -> v - size);
        } else if (order.getOrderType() == OrderType.SALE) {
            balance -= amount;
            stockBalances.compute(stock, (k, v) -> v + size);
        }

        history.add(order);
    }

    public String getName() {
        return name;
    }

    public long getBalance() {
        return balance;
    }

    public Map<Stock, Long> getStockBalances() {
        return stockBalances;
    }

    public long getStockBalance(Stock stock) {
        return stockBalances.get(stock);
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", balance=" + balance +
                ", stockBalances=" + stockBalances +
                ", history=" + history +
                '}';
    }

    public static ClientBuilder builder() {
        return new ClientBuilder();
    }

    public static class ClientBuilder {
        private String name;
        private long balance;
        private final Map<Stock, Long> stockBalances;

        public ClientBuilder() {
            stockBalances = new HashMap<>();
        }

        public ClientBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ClientBuilder withBalance(long balance) {
            this.balance = balance;
            return this;
        }

        public ClientBuilder withStockBalance(Stock stock, long balance) {
            stockBalances.put(Objects.requireNonNull(stock), balance);
            return this;
        }

        public Client build() {
            return new Client(name, balance, stockBalances);
        }
    }
}
