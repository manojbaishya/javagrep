package org.dojo.grep;

import java.io.BufferedReader;
import java.io.IOException;

public interface InputProvider {
    BufferedReader getReader() throws IOException;
}
