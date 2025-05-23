package org.dojo.grep;

import java.nio.file.Path;

public class Validations {
    public static boolean run(Path path) { return Validator.Factory.getValidators().stream().allMatch(v -> v.validate(path.toString())); }
}
