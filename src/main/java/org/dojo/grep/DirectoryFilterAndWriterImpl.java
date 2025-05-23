package org.dojo.grep;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class DirectoryFilterAndWriterImpl implements DirectoryFilterAndWriter {
    private final DirectoryWriter writer;
    private final LineFilter filter;
    private Path rootPath;
    public void setRootPath(Path rootPath) { this.rootPath = rootPath; }
    public DirectoryFilterAndWriterImpl(LineFilter filter, DirectoryWriter writer) {
        this.writer = writer;
        this.filter = filter;
    }

    public void execute() {
        try (Stream<Path> files = Files.walk(rootPath)) {
            List<Path> regularFiles = files.filter(Files::isRegularFile).toList();
            for (Path path : regularFiles) {
                try (BufferedReader reader = Files.newBufferedReader(path)) {
                    List<String> lines = filter.filter(reader);
                    writeResult(path, lines);
                } catch (IOException lineReadError) {
                    System.err.printf("Read error [%s]: %s%n", path, lineReadError.getMessage());
                }
            }
        } catch (IOException fileReadError) {
            System.err.printf("Directory walk error: %s%n", fileReadError.getMessage());
        }
    }

    private void writeResult(Path path, List<String> lines) {
        writer.setDirPath(rootPath);
        writer.setFilePath(path);
        writer.processData(lines);
    }
}
