package reader;

import model.*;
import util.ObjectUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static util.ObjectUtils.cast;

public class OrderObjectReader extends ObjectReaderBase<Order> {

    private final ObjectReader<Map<String, Object>> mapReader;
    private final Map<String, Client> clientMap;

    public OrderObjectReader(ObjectReader<Map<String, Object>> mapReader, Map<String, Client> clientMap) {
        this.mapReader = mapReader;
        this.clientMap = clientMap;
    }

    @Override
    public Order readObject() throws IOException {
        Map<String, Object> map = mapReader.readObject();

        if (map == null) {
            return null;
        }

        String clientName = cast(map.get("client"), String.class);
        String orderType = cast(map.get("type"), String.class);
        String stock = cast(map.get("stock"), String.class);
        String price = cast(map.get("price"), String.class);
        String size = cast(map.get("size"), String.class);

        ObjectUtils.nonNull(clientName, orderType, stock, price, size);

        Client client = clientMap.get(clientName);
        Objects.requireNonNull(client, "Client with name " + clientName + " is not exist");

        return Order.builder()
                .withClient(client)
                .withOrderType(OrderType.forName(orderType))
                .withStock(Stock.forName(stock))
                .withPrice(Long.parseLong(price))
                .withSize(Long.parseLong(size))
                .withStatus(OrderStatus.NEW)
                .build();
    }

    @Override
    public void close() throws IOException {
        mapReader.close();
    }
}
