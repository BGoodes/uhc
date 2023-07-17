package fr.bgoodes.uhc.files;

import fr.bgoodes.uhc.UHC;
import fr.bgoodes.uhc.utils.FileUtils;
import fr.bgoodes.uhc.utils.LogUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Properties;
import java.util.logging.Level;

/**
 * Abstract class representing a file used by the UHC plugin.
 * This class serves as a base for different types of files, such as YAML and properties files.
 *
 * @author B. Goodes
 */
public class MCFile {

    protected final File file;

    /**
     * Constructs a new MCFile.
     *
     * @param filename The name of the file.
     * @throws IOException if an I/O error occurs.
     */
    public MCFile(String filename) throws IOException {
        this(filename, "");
    }

    /**
     * Constructs a new MCFile with a specific path.
     *
     * @param fileName The name of the file.
     * @param path The path to the file.
     * @throws IOException if an I/O error occurs.
     */
    public MCFile(String fileName, String path) throws IOException {
        this.file = new File(UHC.getInstance().getDataFolder().getPath() + (path.isEmpty() ? "" : File.separator + path), fileName);

        if (!file.exists()) {
            createFile(fileName);
        }
    }

    public File getFile() {
        return file;
    }

    private void createFile(String fileName) throws IOException {
        LogUtils.info("The file " + fileName + " does not exist. Creating it now.");
        FileUtils.copy(UHC.getInstance().getResource(fileName), file);

        if (!file.exists()) {
            throw new IOException("The file " + fileName + " could not be created.");
        } else {
            LogUtils.info("The file " + fileName + " was successfully created.");
        }
    }

    /**
     * Subclass representing a YAML file.
     */
    public static class YamlFile extends MCFile {

        public YamlFile(String filename) throws IOException {
            this(filename, "");
        }

        public YamlFile(String filename, String path) throws IOException {
            super(filename, path);
        }

        public YamlConfiguration getConfig() {
            return YamlConfiguration.loadConfiguration(file);
        }

        public void saveConfig(FileConfiguration config) {
            try {
                config.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Subclass representing a properties file.
     */
    public static class PropertiesFile extends MCFile {
        private final Properties properties;

        public PropertiesFile(String filename) throws IOException {
            this(filename, "");
        }

        public PropertiesFile(String filename, String path) throws IOException {
            super(filename, path);
            properties = new Properties();
            try (InputStream in = Files.newInputStream(file.toPath())) {
                properties.load(in);
            } catch (IOException e) {
                LogUtils.severe("Could not load properties from file " + file.getName(), e);
            }
        }

        public Properties getProperties() {
            return properties;
        }

        public void setProperty(String key, String value) {
            properties.setProperty(key, value);
        }

        public String getProperty(String key) {
            return properties.getProperty(key);
        }

        public void saveProperties() {
            try (OutputStream out = Files.newOutputStream(file.toPath())) {
                properties.store(out, null);
            } catch (IOException e) {
                LogUtils.severe("Could not save properties to file " + file.getName(), e);
            }
        }
    }
}
