package reader;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.*;
import java.util.stream.Stream;

public abstract class ObjectReaderBase<T> implements ObjectReader<T> {

    public List<T> readAllObjects() throws IOException {
        List<T> list = new ArrayList<>();
        T obj;

        while ((obj = readObject()) != null) {
            list.add(obj);
        }

        return list;
    }

    public Stream<T> objects() {
        //return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator(), Spliterator.ORDERED | Spliterator.NONNULL), false);
        return Stream.generate(() -> {
                    try {
                        return readObject();
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                })
                .takeWhile(Objects::nonNull);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            private T next = null;

            @Override
            public boolean hasNext() {
                if (next != null) {
                    return true;
                } else {
                    try {
                        next = readObject();
                        return next != null;
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                }
            }

            @Override
            public T next() {
                if (hasNext()) {
                    return next;
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}
