package fr.hokagedev.cra.error.domain;

public class InvalidNumberValueException extends CraException {

    private InvalidNumberValueException(InvalidNumberValueExceptionBuilder builder) {
        this(
                CraException
                        .builder(AssertErrorKey.VALUE_UNDER_MIN)
                        .message("Value of field " + builder.field + " can't be over " + builder.min + " but was " + builder.value)
                        .argument("field", builder.field)
                        .argument("value", builder.value)
                        .argument("min", builder.min)
        );
    }

    private InvalidNumberValueException(CraExceptionBuilder builder) {
        super(builder);
    }

    public static InvalidNumberValueExceptionBuilder underMin(String field) {
        return new InvalidNumberValueExceptionBuilder(field);
    }

    public static InvalidNumberValueException tooManyDigits(String field, int digits) {
        return new InvalidNumberValueException(
                CraException
                        .builder(AssertErrorKey.TOO_MANY_DIGITS)
                        .message("Field " + field + " must have no more than " + digits + " digits")
                        .argument("field", field)
                        .argument("digits", digits)
        );
    }

    public static class InvalidNumberValueExceptionBuilder {

        private final String field;
        private double value;
        private double min;

        public InvalidNumberValueExceptionBuilder(String field) {
            this.field = field;
        }

        public InvalidNumberValueExceptionBuilder value(double value) {
            this.value = value;

            return this;
        }

        public InvalidNumberValueExceptionBuilder min(double min) {
            this.min = min;

            return this;
        }

        public InvalidNumberValueException build() {
            return new InvalidNumberValueException(this);
        }
    }
}
