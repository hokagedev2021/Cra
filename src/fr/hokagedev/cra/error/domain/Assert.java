package fr.hokagedev.cra.error.domain;

import java.math.BigDecimal;
import java.util.Collection;

public final class Assert {

    private Assert() {
    }

    public static void notBlank(String field, String value) {
        Assert.notNull(field, value);

        if (value.isBlank()) {
            throw new MissingMandatoryValueException(field);
        }
    }

    public static void notEmpty(String field, Collection<?> value) {
        Assert.notNull(field, value);

        if (value.isEmpty()) {
            throw new MissingMandatoryValueException(field);
        }
    }

    public static void notNull(String field, Object value) {
        if (value == null) {
            throw new MissingMandatoryValueException(field);
        }
    }

    public static StringAsserter field(String field, String value) {
        return new StringAsserter(field, value);
    }

    public static NumberAsserter field(String field, double value) {
        return new NumberAsserter(field, value);
    }

    public static class StringAsserter {

        private final String field;
        private final String value;

        private StringAsserter(String field, String value) {
            this.field = field;
            this.value = value;
        }

        public StringAsserter notBlank() {
            Assert.notBlank(field, value);

            return this;
        }

        public StringAsserter maxLength(int maxLength) {
            if (value == null) {
                return this;
            }

            if (value.length() > maxLength) {
                throw StringTooLongException.builder().field(field).maxLength(maxLength).length(value.length()).build();
            }

            return this;
        }
    }

    public static class NumberAsserter {

        private final String field;
        private final double value;

        public NumberAsserter(String field, double value) {
            this.field = field;
            this.value = value;
        }

        public NumberAsserter min(double min) {
            if (value < min) {
                throw InvalidNumberValueException.underMin(field).value(value).min(min).build();
            }

            return this;
        }

        public NumberAsserter maxDigits(int digits) {
            if (BigDecimal.valueOf(value).stripTrailingZeros().scale() > digits) {
                throw InvalidNumberValueException.tooManyDigits(field, digits);
            }

            return this;
        }
    }
}

