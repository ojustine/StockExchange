package exchange;

import model.Order;
import model.OrderStatus;

import java.util.List;

public abstract class Exchange implements Runnable {

    private final List<? extends Order> orders;

    public Exchange(List<? extends Order> orders) {
        this.orders = orders;
    }

    public abstract boolean match(Order order1, Order order2);

    public abstract void deal(Order order1, Order order2);

    public abstract boolean valid(Order order);

    public abstract void cancel(Order order);

    public void exchange() {
        Order origin, match;

        while (true) {
            synchronized (orders) {
                origin = orders.stream()
                        .filter(this::valid)
                        .findFirst()
                        .orElse(null);

                if (origin == null) {
                    System.out.println(Thread.currentThread().getName() + ": stop works");//fixme
                    return;
                }

                final Order finalOrigin = origin;
                match = orders.stream()
                        .filter(this::valid)
                        .filter(o -> this.match(finalOrigin, o))
                        .findFirst()
                        .orElse(null);

                if (match == null) {
                    System.out.println(Thread.currentThread().getName() + ": no pair for " + origin);//fixme
                    cancel(origin);
                    continue;
                }

                origin.setStatus(OrderStatus.IN_PROGRESS);
                match.setStatus(OrderStatus.IN_PROGRESS);
            }

            System.out.println(Thread.currentThread().getName() + ": deal between " + origin + " and " + match);//fixme
            deal(origin, match);
        }
    }

    @Override
    public void run() {
        this.exchange();
    }
}
