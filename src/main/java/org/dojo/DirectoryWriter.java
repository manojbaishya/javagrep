package org.dojo;

import java.nio.file.Path;

public interface DirectoryWriter extends Writer {
    void setFilePath(Path filePath);
    void setDirPath(Path dirPath);
}
