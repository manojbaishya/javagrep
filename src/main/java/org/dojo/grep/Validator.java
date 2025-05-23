package org.dojo.grep;

import java.util.List;

public interface Validator {
    boolean validate(String path);

    class Factory {
        public static List<Validator> getValidators() {
            return List.of(new ClassFileValidator());
        }
    }
}
