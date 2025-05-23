package org.dojo;

import java.io.BufferedReader;
import java.util.List;

public class MatchAllLineFilter implements LineFilter {
    @Override
    public List<String> filter(BufferedReader reader) { return reader.lines().toList(); }
}
