package fr.hokagedev.cra.error.domain;

public enum StandardErrorKey implements ErrorKey {
    TECHNICAL_ERROR("server.technical-error"),
    BAD_REQUEST("user.bad-request");

    private final String key;

    StandardErrorKey(String key) {
        this.key = key;
    }

    @Override
    public String key() {
        return key;
    }
}

