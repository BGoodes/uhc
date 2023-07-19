package fr.bgoodes.uhc.files;

import java.io.IOException;

public class FileHandler {

    public final MCFile.YamlFile serverConfig;
    public final MCFile.YamlFile defaultGameConfig;
    public final MCFile.YamlFile langFr;

    public FileHandler() throws IOException {
        this.serverConfig = new MCFile.YamlFile("server-config.yml");
        this.defaultGameConfig = new MCFile.YamlFile("default-game-config.yml");
        this.langFr = new MCFile.YamlFile("fr.yml", "lang");
    }
}