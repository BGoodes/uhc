package fr.bgoodes.uhc.exceptions;

import java.io.File;

/**
 * This class represents an exception that is thrown when an expected configuration option
 * is missing in a configuration file. It extends from the {@link java.lang.RuntimeException}
 * class, so it's an unchecked exception.
 */
public class MissingOptionException  extends RuntimeException {
    private final File sourceFile;

    /**
     * Constructs a new MissingOptionException with a specified detail message and the source file.
     *
     * @param message   the detail message. The detail message is saved for later retrieval by the
     *                  {@link #getMessage()} method.
     * @param sourceFile  the file that caused this exception. It's saved for later retrieval by the
     *                    {@link #getSourceFile()} method.
     */
    public MissingOptionException (String message, File sourceFile) {
        super(message);
        this.sourceFile = sourceFile;
    }

    /**
     * Returns the file that caused this exception.
     *
     * @return the source file of this exception.
     */
    public File getSourceFile() {
        return sourceFile;
    }

}
