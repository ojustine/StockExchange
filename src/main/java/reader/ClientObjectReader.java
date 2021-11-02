package reader;

import model.Client;
import model.Stock;
import util.ObjectUtils;

import java.io.IOException;
import java.util.Map;

import static util.ObjectUtils.cast;

public class ClientObjectReader extends ObjectReaderBase<Client> {

    private final ObjectReader<Map<String, Object>> mapReader;

    public ClientObjectReader(ObjectReader<Map<String, Object>> mapReader) {
        this.mapReader = mapReader;
    }

    @Override
    public Client readObject() throws IOException {
        Map<String, Object> map = mapReader.readObject();

        if (map == null) {
            return null;
        }

        String name = cast(map.get("name"), String.class);
        String balance = cast(map.get("balance"), String.class);
        String balanceA = cast(map.get("A"), String.class);
        String balanceB = cast(map.get("B"), String.class);
        String balanceC = cast(map.get("C"), String.class);
        String balanceD = cast(map.get("D"), String.class);

        ObjectUtils.nonNull(name, balance, balanceA, balanceB, balanceC, balanceD);

        return Client.builder()
                .withName(name)
                .withBalance(Long.parseLong(balance))
                .withStockBalance(Stock.A, Long.parseLong(balanceA))
                .withStockBalance(Stock.B, Long.parseLong(balanceB))
                .withStockBalance(Stock.C, Long.parseLong(balanceC))
                .withStockBalance(Stock.D, Long.parseLong(balanceD))
                .build();
    }

    @Override
    public void close() throws IOException {
        mapReader.close();
    }
}
