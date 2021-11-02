package util;

import org.junit.Assert;
import org.junit.Test;

public class ObjectUtilsTest {

    @Test
    public void nonNull() {
        ObjectUtils.nonNull(new Object(), 1, 2L, 3.0f, 4.0, "test");
    }

    @Test(expected = NullPointerException.class)
    public void nonNullNegative() {
        ObjectUtils.nonNull(new Object(), 1, 2L, 3.0f, null, 4.0, "test");
    }

    @Test
    public void isNonNull() {
        Assert.assertTrue(ObjectUtils.isNonNull(new Object(), 1, 2L, 3.0f, 4.0, "test"));
        Assert.assertFalse(ObjectUtils.isNonNull(new Object(), 1, 2L, 3.0f, null, 4.0, "test"));
    }

    @Test
    public void cast() {
        Object s = "Test";
        Object l = 1L;
        Object i = 1;
        Object f = 1.0f;
        Object d = 1.0;
        Object b = true;

        ObjectUtils.cast(s, String.class);
        ObjectUtils.cast(s, CharSequence.class);
        ObjectUtils.cast(l, Long.class);
        ObjectUtils.cast(l, Number.class);
        ObjectUtils.cast(i, Integer.class);
        ObjectUtils.cast(i, Comparable.class);
        ObjectUtils.cast(f, Float.class);
        ObjectUtils.cast(d, Double.class);
        ObjectUtils.cast(b, Boolean.class);
    }

    @Test(expected = ClassCastException.class)
    public void castNegative() {
        ObjectUtils.cast("Test", Long.class);
    }
}