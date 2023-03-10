package sds.offre.concept_metier;

import java.util.Objects;

public class Prix {
    public static Prix de(long valeur) {
        return new Prix(valeur);
    }

    public long valeur() {
        return valeur;
    }

    private long valeur;

    private Prix(long valeur) {
        if (valeur < 0) throw new IllegalArgumentException("La valleur doit être positive.");

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
