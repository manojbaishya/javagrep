package org.dojo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public interface LineFilter {
    List<String> filter(BufferedReader reader) throws IOException;
}
