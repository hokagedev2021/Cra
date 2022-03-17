package fr.hokagedev.cra.valuesoject;

import fr.hokagedev.cra.error.domain.Assert;

public record FirstName(String name) {

    public FirstName {
        Assert.notBlank("name", name);
    }
}
