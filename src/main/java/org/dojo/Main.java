package org.dojo;

import picocli.CommandLine;

public class Main {
    public static void main(String[] args) {
        var grep = new Grep();
        int exitCode = new CommandLine(grep).execute(args);
        System.exit(exitCode);
    }
}
