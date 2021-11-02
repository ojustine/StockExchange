package reader;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LinedMapReader extends ObjectReaderBase<Map<String, Object>> {

    private final BufferedReader reader;
    private final String[] pattern;
    private final String delimiter;

    public LinedMapReader(Reader reader, String pattern, String delimiter) {
        this.pattern = pattern.split(delimiter);

        if (this.pattern.length == 0) {
            throw new IllegalArgumentException("Invalid pattern: pattern is empty");
        }

        this.reader = new BufferedReader(reader);
        this.delimiter = delimiter;
    }

    @Override
    public Map<String, Object> readObject() throws IOException {
        var line = reader.readLine();

        if (line == null) {
            return null;
        }

        var splitLine = line.split(delimiter);

        if (splitLine.length == 0) {
            return Collections.emptyMap();
        }

        return IntStream.range(0, Math.min(pattern.length, splitLine.length))
                .filter(i -> !"_".equals(pattern[i]))
                .boxed()
                .collect(Collectors.toMap(i -> pattern[i], i -> splitLine[i], (a, b) -> b));
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
