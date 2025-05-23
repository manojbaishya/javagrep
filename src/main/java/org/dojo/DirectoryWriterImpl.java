package org.dojo;

import java.nio.file.Path;
import java.util.List;

public class DirectoryWriterImpl implements DirectoryWriter {
    private Path dirPath;
    public void setDirPath(Path dirPath) { this.dirPath = dirPath; }
    private Path filePath;
    public void setFilePath(Path filePath) { this.filePath = filePath; }

    public DirectoryWriterImpl() { }
    @Override
    public void processData(List<String> lines) {
        if (dirPath == null || filePath == null) return;
        for (String line : lines)
            System.out.printf("%s:%s%n", dirPath.relativize(filePath), line);
    }
}
