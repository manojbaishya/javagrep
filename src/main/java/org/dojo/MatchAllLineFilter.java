package org.dojo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class MatchAllLineFilter implements LineFilter {
    @Override
    public List<String> filter(BufferedReader reader) throws IOException { return reader.lines().toList(); }
}
