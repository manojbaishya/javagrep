package org.dojo;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileInputProvider implements InputProvider {
    private final Path path;
    public FileInputProvider(Path path) { this.path = path; }
    @Override
    public BufferedReader getReader() throws IOException { return Files.newBufferedReader(path); }
}
