package sds.souscriptions.concept_metier;

public class TauxReduction {
    public static TauxReduction de(int valeur) {
        return new TauxReduction(valeur);
    }

    double valeur;

    private TauxReduction(double valeur) {
        if (valeur < 0 || valeur > 100)
            throw new ValeurTauxErroné("La valeur du taux doit être comprise entre 0 et 100.");

        this.valeur = valeur;
    }

    public class ValeurTauxErroné extends RuntimeException {
        public ValeurTauxErroné(String message) {
            super(message);
        }
    }
}
