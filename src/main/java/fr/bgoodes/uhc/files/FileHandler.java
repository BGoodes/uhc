package fr.bgoodes.uhc.files;

import java.io.IOException;

public class FileHandler {

    public final MCFile.YamlFile serverConfig;
    public final MCFile.YamlFile langFr;

    public FileHandler() throws IOException {
        this.langFr = new MCFile.YamlFile("fr.yml", "lang");
        this.serverConfig = new MCFile.YamlFile("server-config.yml");
    }
}