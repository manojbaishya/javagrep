package org.dojo.grep;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProcessorFactory {
    public static Processor create(GrepParameters params) {
        LineFilter filter = LineFilterFactory.create(params.regex(), params.ignoreCase(), params.invertMatch());

        if (params.path().equals("-")) {
            if (params.recursive()) return new StdinDirectoryProcessor(filter, new DirectoryWriterImpl());
            return new FileProcessor(new StdinInputProvider(), filter);
        }

        Path inputPath = Paths.get(params.path());
        if (!Files.exists(inputPath)) throw new IllegalArgumentException("Path does not exist: " + inputPath);

        if (params.recursive()) {
            if (!Files.isDirectory(inputPath))
                throw new IllegalArgumentException("Recursive mode requires a directory: " + inputPath);
            return new RecursiveDirectoryProcessor(inputPath, filter, new DirectoryWriterImpl());
        } else {
            if (!Files.isRegularFile(inputPath))
                throw new IllegalArgumentException("Non-recursive mode requires a file: " + inputPath);
            return new FileProcessor(new FileInputProvider(inputPath), filter);
        }
    }
}
