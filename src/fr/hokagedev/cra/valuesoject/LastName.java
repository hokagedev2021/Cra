package fr.hokagedev.cra.valuesoject;

import fr.hokagedev.cra.error.domain.Assert;

public record LastName(String lastName) {

    public LastName {
        Assert.notBlank("lastName", lastName);
    }
}
