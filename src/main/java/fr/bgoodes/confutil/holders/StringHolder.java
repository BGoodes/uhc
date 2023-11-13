package fr.bgoodes.confutil.holders;

public class StringHolder extends OptionHolder<String> {

    protected StringHolder() {}
    protected StringHolder(String key, String defaultValue) {
        super(key, defaultValue);
    }

    @Override
    public String serialize(String o) {
        return o;
    }

    @Override
    public String deserialize(String s) {
        return s;
    }

}
