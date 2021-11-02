package model;

public class Order {
    private Client client;
    private OrderType orderType;
    private Stock stock;
    private long size;
    private long price;
    private OrderStatus status;

    private Order(Client client, OrderType orderType, Stock stock, long size, long price, OrderStatus status) {
        this.client = client;
        this.orderType = orderType;
        this.stock = stock;
        this.size = size;
        this.price = price;
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public Stock getStock() {
        return stock;
    }

    public long getSize() {
        return size;
    }

    public long getPrice() {
        return price;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "client=" + client.getName() +
                ", orderType=" + orderType +
                ", stock=" + stock +
                ", number=" + size +
                ", price=" + price +
                ", status=" + status +
                '}';
    }

    public static OrderBuilder builder() {
        return new OrderBuilder();
    }

    public static class OrderBuilder {
        private Client client;
        private OrderType orderType;
        private Stock stock;
        private long size;
        private long price;
        private OrderStatus status;

        public OrderBuilder withClient(Client client) {
            this.client = client;
            return this;
        }

        public OrderBuilder withOrderType(OrderType orderType) {
            this.orderType = orderType;
            return this;
        }

        public OrderBuilder withStock(Stock stock) {
            this.stock = stock;
            return this;
        }

        public OrderBuilder withSize(long size) {
            this.size = size;
            return this;
        }

        public OrderBuilder withPrice(long price) {
            this.price = price;
            return this;
        }

        public OrderBuilder withStatus(OrderStatus status) {
            this.status = status;
            return this;
        }

        public Order build() {
            return new Order(client, orderType, stock, size, price, status);
        }
    }
}
