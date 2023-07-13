package fr.bgoodes.uhc.files.lang;

import fr.bgoodes.uhc.files.MCFile;

import java.text.MessageFormat;

public enum Lang {

    ;
    private static MCFile.PropertiesFile LANG_FILE;

    private final String key;

    Lang () {
        this.key = name().toLowerCase().replace("_", "-");
    }

    public String get(Object... args) {
        String format = LANG_FILE.getProperty(this.key);
        return MessageFormat.format(format, args);
    }
}
