package fr.hokagedev.cra.error.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CraException extends RuntimeException {

    private static final String TECHNICAL_ERROR_MESSAGE = "A technical error occured";

    private final ErrorKey key;
    private final Map<String, Object> arguments;
    private final ErrorStatus status;

    public CraException(CraExceptionBuilder builder) {
        super(buildMessage(builder), buildCause(builder));
        Assert.notNull("builder", builder);

        key = buildKey(builder.key);
        arguments = builder.arguments;
        status = buildStatus(builder.status);
    }

    private static String buildMessage(CraExceptionBuilder builder) {
//        if (builder == null || StringUtils.isBlank(builder.message)) {
//            return TECHNICAL_ERROR_MESSAGE;
//        }

        if (builder == null) {
            return TECHNICAL_ERROR_MESSAGE;
        }

        return builder.message;
    }

    private static Throwable buildCause(CraExceptionBuilder builder) {
        if (builder == null) {
            return null;
        }

        return builder.cause;
    }

    private ErrorKey buildKey(ErrorKey key) {
        if (key == null) {
            return StandardErrorKey.TECHNICAL_ERROR;
        }

        return key;
    }

    private ErrorStatus buildStatus(ErrorStatus status) {
        if (status == null) {
            return defaultStatus();
        }

        return status;
    }

    private ErrorStatus defaultStatus() {
        return Arrays
                .stream(Thread.currentThread().getStackTrace())
                .map(StackTraceElement::getClassName)
                .filter(name -> name.contains(".primary."))
                .findFirst()
                .map(stack -> ErrorStatus.BAD_REQUEST)
                .orElse(ErrorStatus.INTERNAL_SERVER_ERROR);
    }

    public static CraExceptionBuilder builder(ErrorKey key) {
        return new CraExceptionBuilder(key);
    }

    public ErrorKey key() {
        return key;
    }

    public Throwable cause() {
        return getCause();
    }

    public String message() {
        return getMessage();
    }

    public Map<String, Object> arguments() {
        return arguments;
    }

    public ErrorStatus status() {
        return status;
    }

    public static class CraExceptionBuilder {

        private final ErrorKey key;
        private final Map<String, Object> arguments = new ConcurrentHashMap<>();

        private String message;
        private Throwable cause;
        private ErrorStatus status;

        public CraExceptionBuilder(ErrorKey key) {
            this.key = key;
        }

        public CraExceptionBuilder message(String message) {
            this.message = message;

            return this;
        }

        public CraExceptionBuilder cause(Throwable cause) {
            this.cause = cause;

            return this;
        }

        public CraExceptionBuilder status(ErrorStatus status) {
            this.status = status;

            return this;
        }

        public CraExceptionBuilder argument(String key, Object value) {
            if (key == null) {
                return this;
            }

            arguments.put(key, value);

            return this;
        }

        public CraException build() {
            return new CraException(this);
        }
    }
}
