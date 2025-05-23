package org.dojo.grep;

import java.util.List;

public class FileWriterImpl implements Writer {
    @Override
    public void processData(List<String> lines) {
        for (String line : lines) System.out.println(line);
    }
}
