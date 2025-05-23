package org.dojo.grep;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class LineFilterAndWriter implements FileFilterAndWriter {
    private BufferedReader reader;
    public void setReader(BufferedReader reader) { this.reader = reader; }
    private final LineFilter filter;
    private final Writer writer;

    public LineFilterAndWriter(LineFilter filter, Writer writer) {
        this.filter = filter;
        this.writer = writer;
    }
    @Override
    public void execute() {
        try {
            List<String> lines = filter.filter(reader);
            writer.processData(lines);
        } catch (IOException e) {
            System.err.printf("Error: %s%n", e.getMessage());
        }
    }

}
