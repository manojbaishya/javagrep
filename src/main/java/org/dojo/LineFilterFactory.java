package org.dojo;

public class LineFilterFactory {
    public static LineFilter create(String regex, boolean ignoreCase, boolean invertMatch) {
        return regex == null || regex.isBlank() ? new MatchAllLineFilter() : new RegexLineFilter(regex, ignoreCase, invertMatch);
    }
}
