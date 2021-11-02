package reader;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public interface ObjectReader<T> extends Closeable, Iterable<T> {

    T readObject() throws IOException;

    List<T> readAllObjects() throws IOException;

    Stream<T> objects();

}
