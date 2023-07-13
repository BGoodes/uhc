package fr.bgoodes.uhc.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Utility class for file operations.
 *
 * @author B. Goodes
 */
public class FileUtils {

    // CREATE ---------------------------------------------------
    /**
     * Creates a new file at the given path, including any necessary but nonexistent parent directories.
     *
     * @param file the file to create
     * @throws IOException if an I/O error occurs
     */
    public static void create(File file) throws IOException {
        create(file.toPath());
    }

    /**
     * Creates a new file at the given path, including any necessary but nonexistent parent directories.
     *
     * @param path the path of the file to create
     * @throws IOException if an I/O error occurs
     */
    public static void create(Path path) throws IOException {
        Files.createDirectories(path.getParent());
        Files.createFile(path);
    }

    // DELETE ---------------------------------------------------

    /**
     * Deletes the given file.
     *
     * @param file the file to delete
     * @throws IOException if an I/O error occurs
     */
    public static void delete(File file) throws IOException {
        delete(file.toPath());
    }

    /**
     * Deletes the file at the given path.
     *
     * @param path the path of the file to delete
     * @throws IOException if an I/O error occurs
     */
    public static void delete(Path path) throws IOException {
        Files.delete(path);
    }

    // COPY -----------------------------------------------------

    /**
     * Copies the source file to the target file, creating any necessary directories. If the target file exists, it will be overwritten.
     *
     * @param source the source file
     * @param copy the target file
     * @throws IOException if an I/O error occurs
     */
    public void copy(File source, File copy) throws IOException {
        copy(source.toPath(), copy.toPath());
    }

    /**
     * Copies the file from the source path to the target path, creating any necessary directories. If the target file exists, it will be overwritten.
     *
     * @param source the source path
     * @param target the target path
     * @throws IOException if an I/O error occurs
     */
    public static void copy(Path source, Path target) throws IOException {
        Files.createDirectories(target.getParent());
        Files.copy(source, target);
    }

    /**
     * Copies data from the input stream to the output file, creating any necessary directories, and optionally closes the stream when done.
     *
     * @param in the input stream
     * @param copy the output file
     * @throws IOException if an I/O error occurs
     */
    public static void copy(InputStream in, File copy) throws IOException {
        copy(in, copy, true);
    }

    /**
     * Copies data from the input stream to the output file, creating any necessary directories, and optionally closes the stream when done.
     *
     * @param in the input stream
     * @param copy the output file
     * @param close true to close the stream after copying
     * @throws IOException if an I/O error occurs
     */
    public static void copy(InputStream in, File copy, Boolean close) throws IOException {
        Files.createDirectories(copy.toPath().getParent());
        try (OutputStream out = Files.newOutputStream(copy.toPath())) {
            copy(in, out, close);
        }
    }

    /**
     * Copies data from the input stream to the output stream. Optionally closes the streams when done.
     *
     * @param in the input stream
     * @param out the output stream
     * @param close true to close the streams after copying
     * @throws IOException if an I/O error occurs
     */
    public static void copy(InputStream in, OutputStream out, boolean close) throws IOException {
        try {
            byte[] buffer = new byte[1024];
            int lengthRead;

            while ((lengthRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, lengthRead);
            }
        } finally {
            if (close) {
                in.close();
                out.close();
            }
        }
    }
}