package org.dojo;

import picocli.CommandLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CommandLine.Command(name = "Grep", version = "1.0.0", mixinStandardHelpOptions = true)
public class Grep implements Runnable {
    @CommandLine.Parameters(index = "0", hidden = true)
    private String programName;

    @CommandLine.Option(names = {"-i", "--ignore-case"}, description = "Ignore case sensitivity")
    private boolean ignoreCase;

    @CommandLine.Option(names = {"-r", "--recursive"}, description = "Recurse a directory")
    private boolean isRecurse;

    @CommandLine.Option(names = {"-v", "--invert-match"}, description = "Invert the search")
    private boolean isInvertMatch;

    @CommandLine.Parameters(index = "1", paramLabel = "<regex>", defaultValue = "", description = "The regex pattern to search in file")
    private String pattern;

    @CommandLine.Parameters(index = "2", paramLabel = "<file>", description = "The file to search in")
    private String path;

    @Override
    public void run() {
        if (isRecurse) {
            Path rootPath = Paths.get(path);
            if (!Files.isDirectory(rootPath)) throw new IllegalArgumentException("Path '%s' is not a directory.".formatted(rootPath.toUri()));
            List<Path> paths = getAllFilePaths(rootPath);
            for (Path filepath : paths) {
                List<String> lines = filterLines(filepath, false);
                processData(lines, rootPath.toAbsolutePath().relativize(filepath.toAbsolutePath()).toString());
            }
        } else {
            if ("-".equals(path)) {
                List<String> lines = filterLines(null, true);
                processData(lines);
            } else {
                Path filepath = Paths.get(path);
                if (!Files.isRegularFile(filepath))
                    throw new IllegalArgumentException("Path '%s' is not a file. You have to specify the '--recursive' option.".formatted(filepath.toUri()));
                List<String> lines = filterLines(filepath, false);
                if (!pattern.isBlank() && lines.isEmpty())
                    throw new RuntimeException("No lines found for the pattern '%s'".formatted(pattern));
                processData(lines);
            }
        }
    }

    public List<String> filterLines(Path file, boolean isStandardInput) {
        List<String> lines = new ArrayList<>();
        try (var reader = isStandardInput ? new BufferedReader(new InputStreamReader(System.in)) : Files.newBufferedReader(file)) {
            String line;
            if (pattern.isBlank()) {
                while ((line = reader.readLine()) != null) lines.add(line);
            } else {
                var regex = ignoreCase ? Pattern.compile(pattern, Pattern.CASE_INSENSITIVE) : Pattern.compile(pattern);
                if (isInvertMatch) {
                    while ((line = reader.readLine()) != null) {
                        Matcher matcher = regex.matcher(line);
                        if (!matcher.find()) lines.add(line);
                    }
                } else {
                    while ((line = reader.readLine()) != null) {
                        Matcher matcher = regex.matcher(line);
                        if (matcher.find()) lines.add(line);
                    }
                }

            }
        } catch (IOException error) {
            System.err.printf("Error reading file '%s'%n", error.getMessage());
        }
        return lines;
    }
    private List<Path> getAllFilePaths(Path rootPath) {
        try(var entries = Files.walk(rootPath)) {
                return entries.filter(Files::isRegularFile).toList();
        } catch (IOException error) {
            System.err.printf("Error reading path '%s'%n", error.getMessage());
        }
        return List.of();
    }

    private void processData(List<String> lines, String filePath) {
        for (String line : lines) System.out.printf("%s:%s%n", filePath, line);
    }

    private void processData(List<String> lines) {
        for (String line : lines) System.out.println(line);
    }

    public static void main(String[] args) {
        var grep = new Grep();
        int exitCode = new CommandLine(grep).execute(args);
        System.exit(exitCode);
    }
}
