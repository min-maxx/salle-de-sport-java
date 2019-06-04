package sds.souscriptions.concept_metier;

import java.util.Objects;

public class IdAbonnement {
    private String valeur;

    public IdAbonnement(String valeur) {
        this.valeur = valeur;
    }

    public static IdAbonnement de(String valeur) {
        return new IdAbonnement(valeur);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdAbonnement that = (IdAbonnement) o;
        return Objects.equals(valeur, that.valeur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valeur);
    }

    @Override
    public String toString() {
        return "IdAbonnement{" +
                "valeur='" + valeur + '\'' +
                '}';
    }
}
