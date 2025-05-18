package org.dojo;

import picocli.CommandLine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CommandLine.Command(name = "Grep", version = "1.0", mixinStandardHelpOptions = true)
public class Grep implements Runnable {
    @CommandLine.Parameters(index = "0")
    private String programName;

    @CommandLine.Parameters(index = "1", paramLabel = "<regex>", defaultValue = "", description = "The regex pattern to search in file")
    private String pattern;

    @CommandLine.Parameters(index = "2", paramLabel = "<file>", description = "The file to search in")
    private String filePath;

    @Override
    public void run() {
        List<String> lines = filterLines(filePath);
        processData(lines);
    }

    public List<String> filterLines(String filePath) {
        List<String> lines = new ArrayList<>();

        var file = new File(filePath);
        if (!file.exists()) throw new IllegalArgumentException("File '%s' not found!".formatted(filePath));

        try (var reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;

                if (pattern.isBlank()) lines.add(line);
                else if (line.matches(pattern)) lines.add(line);
            }
        } catch (IOException error) {
            System.err.printf("Error reading file '%s'%n", error.getMessage());
        }
        return lines;
    }

    private void processData(List<String> lines) {
        for (var line : lines) System.out.println(line);
    }

    public static void main(String[] args) {
        var grep = new Grep();
        int exitCode = new CommandLine(grep).execute(args);
        System.exit(exitCode);
    }
}
