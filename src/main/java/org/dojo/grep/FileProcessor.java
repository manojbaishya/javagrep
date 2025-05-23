package org.dojo.grep;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileProcessor implements Processor {
    private final Path path;
    private final FileFilterAndWriter fileFilterAndWriter;

    public FileProcessor(Path path, LineFilter filter) {
        this.path = path;
        fileFilterAndWriter = new LineFilterAndWriter(filter, new LineWriter());
    }

    @Override
    public void process() {
        if (!Validations.run(path)) return;
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            fileFilterAndWriter.setReader(reader);
            fileFilterAndWriter.execute();
        } catch (IOException e) {
            System.err.printf("Error: %s%n", e.getMessage());
        }
    }
}
