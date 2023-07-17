package fr.bgoodes.uhc.exceptions;

import java.io.File;

public class MissingOptionException  extends RuntimeException {
    private final File sourceFile;
    public MissingOptionException (String message, File sourceFile) {
        super(message);
        this.sourceFile = sourceFile;
    }
    public File getSourceFile() {
        return sourceFile;
    }

}
