package org.dojo.grep;

import java.io.BufferedReader;

public interface FileFilterAndWriter extends FilterAndWriter {
    void setReader(BufferedReader reader);
}
