package org.dojo.grep;

import java.nio.file.Path;

public interface DirectoryFilterAndWriter extends FilterAndWriter {
    void setRootPath(Path rootPath);
}
