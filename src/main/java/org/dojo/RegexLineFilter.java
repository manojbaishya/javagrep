package org.dojo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class RegexLineFilter implements LineFilter {
    private final Pattern pattern;
    private final boolean invert;

    public RegexLineFilter(String regex, boolean ignoreCase, boolean invert) {
        this.pattern = Pattern.compile(regex, ignoreCase ? Pattern.CASE_INSENSITIVE : 0);
        this.invert = invert;
    }

    @Override
    public List<String> filter(BufferedReader reader) throws IOException {
        List<String> results = new ArrayList<>();
        String line;
        while((line = reader.readLine()) != null) {
            boolean matches = pattern.matcher(line).find();
            if (invert != matches) results.add(line);
        }
        return results;
    }
}
