package org.dojo.grep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StandardInputDirectoryProcessor implements Processor {
    private final DirectoryFilterAndWriter directoryFilterAndWriter;
    public StandardInputDirectoryProcessor(LineFilter filter) { this.directoryFilterAndWriter = new DirectoryFilterAndWriterImpl(filter, new DirectoryWriterImpl()); }

    @Override
    public void process() {
        try (var stdinReader = new BufferedReader(new InputStreamReader(System.in))) {
            String dirLine;
            while ((dirLine = stdinReader.readLine()) != null) {
                Path rootPath = Paths.get(dirLine.trim());
                if (!Files.isDirectory(rootPath)) {
                    System.err.printf("Not a directory: %s%n", dirLine);
                    continue;
                }
                directoryFilterAndWriter.setRootPath(rootPath);
                directoryFilterAndWriter.execute();
            }
        } catch (IOException e) {
            System.err.printf("Stdin read error: %s%n", e.getMessage());
        }
    }
}
