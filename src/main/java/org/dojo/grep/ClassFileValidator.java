package org.dojo.grep;

public class ClassFileValidator implements Validator {
    @Override
    public boolean validate(String path) { return !path.toLowerCase().endsWith(".class"); }
}
