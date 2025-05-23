package org.dojo.grep;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProcessorFactory {
    public static Processor create(GrepParameters params) {
        LineFilter filter = LineFilterFactory.create(params.regex(), params.ignoreCase(), params.invertMatch());

        if (params.path().equals("-")) return getStandardInputProcessor(params, filter);

        Path inputPath = Paths.get(params.path());
        if (!Files.exists(inputPath)) throw new IllegalArgumentException("Path does not exist: " + inputPath);

        return getPathProcessor(params, inputPath, filter);
    }

    private static Processor getStandardInputProcessor(GrepParameters params, LineFilter filter) {
        if (params.recursive()) return new StandardInputDirectoryProcessor(filter);
        return new StandardInputProcessor(filter);
    }

    private static Processor getPathProcessor(GrepParameters params, Path inputPath, LineFilter filter) {
        if (params.recursive()) {
            if (!Files.isDirectory(inputPath))
                throw new IllegalArgumentException("Recursive mode requires a directory: " + inputPath);
            return new RecursiveDirectoryProcessor(inputPath, filter);
        } else {
            if (!Files.isRegularFile(inputPath))
                throw new IllegalArgumentException("Non-recursive mode requires a file: " + inputPath);
            return new FileProcessor(inputPath, filter);
        }
    }
}
