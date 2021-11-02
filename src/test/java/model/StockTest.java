package model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class StockTest {

    @Test
    public void forName() {
        Assert.assertEquals(Stock.A, Stock.forName("A"));
        Assert.assertEquals(Stock.B, Stock.forName("B"));
        Assert.assertEquals(Stock.C, Stock.forName("C"));
        Assert.assertEquals(Stock.D, Stock.forName("D"));
    }
}