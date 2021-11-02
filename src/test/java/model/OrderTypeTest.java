package model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderTypeTest {

    @Test
    public void forName() {
        Assert.assertEquals(OrderType.SALE, OrderType.forName("s"));
        Assert.assertEquals(OrderType.SALE, OrderType.forName("S"));
        Assert.assertEquals(OrderType.SALE, OrderType.forName("Sale"));
        Assert.assertEquals(OrderType.SALE, OrderType.forName("sale"));
        Assert.assertEquals(OrderType.SALE, OrderType.forName("SALE"));
        Assert.assertEquals(OrderType.BUY, OrderType.forName("b"));
        Assert.assertEquals(OrderType.BUY, OrderType.forName("B"));
        Assert.assertEquals(OrderType.BUY, OrderType.forName("Buy"));
        Assert.assertEquals(OrderType.BUY, OrderType.forName("buy"));
        Assert.assertEquals(OrderType.BUY, OrderType.forName("BUY"));
    }
}