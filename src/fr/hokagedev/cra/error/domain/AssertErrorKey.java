package fr.hokagedev.cra.error.domain;

public enum AssertErrorKey implements ErrorKey {
    MISSING_MANDATORY_VALUE("missing-mandatory-value"),
    VALUE_UNDER_MIN("value-under-min"),
    TOO_MANY_DIGITS("too-many-digits"),
    STRING_TOO_LONG("string-too-long");

    private final String key;

    AssertErrorKey(String key) {
        this.key = key;
    }

    @Override
    public String key() {
        return key;
    }
}

