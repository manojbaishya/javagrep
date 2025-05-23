package org.dojo.grep;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StdinInputProvider implements InputProvider {
    @Override
    public BufferedReader getReader() { return new BufferedReader(new InputStreamReader(System.in)); }
}
