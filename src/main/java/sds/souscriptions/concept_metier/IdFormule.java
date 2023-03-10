package sds.souscriptions.concept_metier;

import java.util.Objects;

public class IdFormule {
    public static IdFormule de(String valeur) {
        return new IdFormule(valeur);
    }

    private String valeur;

    private IdFormule(String valeur) {
        this.valeur = valeur;
    }

    public String valeur() {
        return valeur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdFormule idFormule = (IdFormule) o;
        return Objects.equals(valeur, idFormule.valeur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valeur);
    }

    @Override
    public String toString() {
        return "IdFormule{" +
                "valeur='" + valeur + '\'' +
                '}';
    }
}
