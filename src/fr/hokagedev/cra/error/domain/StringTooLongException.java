package fr.hokagedev.cra.error.domain;

public class StringTooLongException extends CraException {

    private StringTooLongException(StringTooLongExceptionBuilder builder) {
        super(
                CraException
                        .builder(AssertErrorKey.STRING_TOO_LONG)
                        .message("Value in " + builder.field + " was " + builder.maxLength + " but max length is " + builder.length)
                        .argument("field", builder.field)
                        .argument("length", builder.length)
                        .argument("maxLength", builder.maxLength)
        );
    }

    public static StringTooLongExceptionBuilder builder() {
        return new StringTooLongExceptionBuilder();
    }

    public static class StringTooLongExceptionBuilder {

        private String field;
        private int maxLength;
        private int length;

        public StringTooLongExceptionBuilder field(String field) {
            this.field = field;

            return this;
        }

        public StringTooLongExceptionBuilder maxLength(int maxLength) {
            this.maxLength = maxLength;

            return this;
        }

        public StringTooLongExceptionBuilder length(int length) {
            this.length = length;

            return this;
        }

        public StringTooLongException build() {
            return new StringTooLongException(this);
        }
    }
}

