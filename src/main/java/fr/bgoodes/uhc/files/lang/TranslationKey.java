package fr.bgoodes.uhc.files.lang;

public enum TranslationKey {

    PREFIX,
    ERROR,

    //PLAYER CONNEXION
    BC_PLAYER_JOIN("broadcast"),
    BC_SPECTATOR_JOIN("broadcast"),
    BC_PLAYER_LEAVE("broadcast"),
    BC_SPECTATOR_LEAVE("broadcast"),

    BC_GAME_STARTING("broadcast.starting"),
    BC_GAME_STARTED("broadcast.starting"),
    BC_GAME_CANCELLED("broadcast.starting");

    private final String path;

    TranslationKey() {
        this.path = name().toLowerCase().replace("_", "-");
    }

    TranslationKey(String path) {
        this.path = path + "." + name().toLowerCase().replace("_", "-");;
    }

    TranslationKey(String key, String path) {
        this.path = path + "." + key;
    }

    public String getPath() {
        return path;
    }
}
