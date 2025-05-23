package org.dojo.grep;

public record GrepParameters(String regex, String path, boolean recursive, boolean ignoreCase, boolean invertMatch) {
}
