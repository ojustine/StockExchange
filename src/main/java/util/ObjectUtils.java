package util;

import java.util.Arrays;
import java.util.Objects;

public class ObjectUtils {
    public static void nonNull(Object... objects) {
        Arrays.stream(objects).forEach(Objects::requireNonNull);
    }

    public static boolean isNonNull(Object... objects) {
        for (Object obj : objects) {
            if (obj == null)
                return false;
        }

        return true;
    }

    public static <T> T cast(Object obj, Class<T> clazz) {
        nonNull(obj, clazz);

        if (clazz.isInstance(obj)) {
            return clazz.cast(obj);
        }

        throw new ClassCastException("Instance of " + obj.getClass() + " cannot be casted to " + clazz.getName());
    }
}
