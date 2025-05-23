package org.dojo.grep;

import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(name = "Grep", version = "2.0.0", mixinStandardHelpOptions = true)
public class Grep implements Runnable {
    @Override
    public void run() {
        for (String path : paths) {
            var params = new GrepParameters(pattern, path, isRecursive, ignoreCase, isInvertMatch);
            Processor processor = ProcessorFactory.create(params);
            processor.process();
        }
    }

    // region: Command Line Parameters
    @CommandLine.Parameters(index = "0", hidden = true)
    private String programName;

    @CommandLine.Option(names = {"-i", "--ignore-case"}, description = "Ignore case sensitivity")
    private boolean ignoreCase;

    @CommandLine.Option(names = {"-r", "--recursive"}, description = "Recurse a directory")
    private boolean isRecursive;

    @CommandLine.Option(names = {"-v", "--invert-match"}, description = "Invert the search")
    private boolean isInvertMatch;

    @CommandLine.Parameters(index = "1", paramLabel = "<regex>", defaultValue = "", description = "The regex pattern to search in file")
    private String pattern;

    @CommandLine.Parameters(index = "2..*", paramLabel = "<file>", description = "The file to search in")
    private List<String> paths;
    // endregion
}
