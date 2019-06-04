package sds.souscriptions.concept_metier;

import java.util.Objects;

public class Prix {
    public static Prix de(double valeur) {
        return new Prix(valeur);
    }

    private double valeur;

    private Prix(double valeur) {
        this.valeur = valeur;
    }

    public Prix applique(TauxReduction taux) {
        double prixReduit = this.valeur - (this.valeur) * taux.valeur / 100;
        return Prix.de(arrondi(prixReduit, 2));
    }

    private double arrondi(double nombre, int nbChiffreApresVirgule) {
        return (double) ((int) (nombre * Math.pow(10, nbChiffreApresVirgule) + .5)) / Math.pow(10, nbChiffreApresVirgule);
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
