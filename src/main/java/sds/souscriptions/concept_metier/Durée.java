package sds.souscriptions.concept_metier;

public enum Durée {
    ANNUELLE(12), MENSUELLE(1);

    private long nombreDeMois;

    Durée(long nombreDeMois) {
        this.nombreDeMois = nombreDeMois;
    }

    public long nombreDeMois() {
        return nombreDeMois;
    }
}
