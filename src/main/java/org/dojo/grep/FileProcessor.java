package org.dojo.grep;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class FileProcessor implements Processor {
    private final InputProvider inputProvider;
    private final LineFilter filter;
    private final Writer writer;

    public FileProcessor(InputProvider inputProvider, LineFilter filter) {
        this.inputProvider = inputProvider;
        this.filter = filter;
        this.writer = new FileWriterImpl();
    }

    @Override
    public void process() {
        try (BufferedReader reader = inputProvider.getReader()) {
            List<String> lines = filter.filter(reader);
            writer.processData(lines);
        } catch (IOException e) {
            System.err.printf("Error: %s%n", e.getMessage());
        }
    }
}
