package fr.hokagedev.cra.entities;

import fr.hokagedev.cra.valuesoject.Activities;
import fr.hokagedev.cra.valuesoject.Collaborateur;

public record CompteRenduActivite(Collaborateur collaborateur, Activities activities) {
}
