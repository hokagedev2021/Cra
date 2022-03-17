package fr.hokagedev.cra.error.domain;

public class MissingMandatoryValueException extends CraException {

    public MissingMandatoryValueException(String field) {
        super(
                CraException.builder(AssertErrorKey.MISSING_MANDATORY_VALUE).message("Missing mandatory value in " + field).argument("field", field)
        );
    }
}
