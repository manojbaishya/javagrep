package org.dojo.grep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StandardInputProcessor implements Processor {
    private final FileFilterAndWriter fileFilterAndWriter;
    public StandardInputProcessor(LineFilter filter) { fileFilterAndWriter = new LineFilterAndWriter(filter, new LineWriter()); }

    @Override
    public void process() {
        try (var reader = new BufferedReader(new InputStreamReader(System.in))) {
            fileFilterAndWriter.setReader(reader);
            fileFilterAndWriter.execute();
        } catch (IOException e) {
            System.err.printf("Error: %s%n", e.getMessage());
        }
    }
}
