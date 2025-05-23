package org.dojo.grep;

import java.nio.file.Path;

public class RecursiveDirectoryProcessor implements Processor {
    private final DirectoryFilterAndWriter directoryFilterAndWriter;
    public RecursiveDirectoryProcessor(Path rootPath, LineFilter filter) {
        this.directoryFilterAndWriter = new DirectoryFilterAndWriterImpl(filter, new DirectoryWriterImpl());
        directoryFilterAndWriter.setRootPath(rootPath);
    }

    @Override
    public void process() { directoryFilterAndWriter.execute(); }
}
