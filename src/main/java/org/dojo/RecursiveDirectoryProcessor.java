package org.dojo;

import java.nio.file.Path;

public class RecursiveDirectoryProcessor implements Processor {
    private final DirectoryFilterAndWriter directoryFilterAndWriter;
    public RecursiveDirectoryProcessor(Path rootPath, LineFilter filter, DirectoryWriter writer) {
        this.directoryFilterAndWriter = new DirectoryFilterAndWriterImpl(filter, writer);
        directoryFilterAndWriter.setRootPath(rootPath);
    }

    @Override
    public void process() {
        directoryFilterAndWriter.execute();
    }
}
