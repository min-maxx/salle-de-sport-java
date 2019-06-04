package sds.offre.concept_metier;

import java.util.Objects;

public class Prix {
    public static Prix de(int valeur) {
        return new Prix(valeur);
    }

    private int valeur;

    private Prix(int valeur) {
        this.valeur = valeur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prix prix = (Prix) o;
        return valeur == prix.valeur;
    }

    @Override
    public int hashCode() {
        return Objects.hash(valeur);
    }

    @Override
    public String toString() {
        return "Prix{" +
                "valeur=" + valeur +
                '}';
    }


}
