import model.Client;
import model.Order;
import model.Stock;
import reader.ClientObjectReader;
import reader.LinedMapReader;
import reader.ObjectReader;
import reader.OrderObjectReader;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    private static final String CLIENT_PATT = "name\tbalance\tA\tB\tC\tD";
    private static final String OFFER_PATT = "client\ttype\tstock\tprice\tsize";
    private static final String DELIM = "\t";

    public static void main(String[] args) throws IOException, InterruptedException {

        //StringReader r = new StringReader("NAME\t1200\t2\t2\t2\t2\r\nNAME2\t1000\t2\t2\t2\t2");
        Reader r = new FileReader("src/main/resources/clients.txt");
        ObjectReader<Map<String, Object>> or = new LinedMapReader(r, CLIENT_PATT, DELIM);
        ObjectReader<Client> cr = new ClientObjectReader(or);
        Map<String, Client> s = cr.objects().collect(Collectors.toMap(Client::getName, c -> c));

        //StringReader r1 = new StringReader("NAME\tb\tA\t1\t1\r\nNAME\tb\tC\t1\t1\r\nNAME2\ts\tC\t1\t1\r\nNAME2\ts\tA\t1\t1");
        Reader r1 = new FileReader("src/main/resources/orders.txt");
        ObjectReader<Map<String, Object>> or1 = new LinedMapReader(r1, OFFER_PATT, DELIM);
        ObjectReader<Order> orders = new OrderObjectReader(or1, s);

        var res = orders.readAllObjects();

//        long st = System.currentTimeMillis();
//        for (int i = 0; i < 2; i++) {
//            ExchangeBase exchange = new FullMatchExchange(res);
//            exchange.start();
//            exchange.join();
//        }
//        long en = System.currentTimeMillis();
//        System.out.println(en - st);

        Stock ss = Stock.valueOf("A");
        System.out.println(ss);

        ObjectReader
    }
}
