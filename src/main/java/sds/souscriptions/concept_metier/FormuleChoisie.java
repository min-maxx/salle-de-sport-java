package sds.souscriptions.concept_metier;


public class FormuleChoisie {

    private static final TauxReduction TAUX_ETUDIANT = TauxReduction.de(30);
    private static final TauxReduction TAUX_ANNUEL = TauxReduction.de(20);
    private static final TauxReduction TAUX_CUMULE = TauxReduction.de(50);
    private static final TauxReduction PAS_DE_REDUCTION = TauxReduction.de(0);


    public static FormuleChoisie avec(IdFormule id, Prix prix, Durée durée) {
        return new FormuleChoisie(id, prix, durée);
    }

    public final IdFormule id;
    public final Durée durée;
    public final Prix prix;

    private FormuleChoisie(IdFormule id, Prix prix, Durée durée) {
        this.id = id;
        this.prix = prix;
        this.durée = durée;
    }

    Prix prixAbonnementPour(Prospect prospect) {
        TauxReduction tauxReduction = taux(durée, prospect.etudiant);
        return prix.applique(tauxReduction);
    }


    private TauxReduction taux(Durée durée, Etudiant etudiant) {
        if (durée.equals(Durée.ANNUELLE) && etudiant.equals(Etudiant.OUI))
            return TAUX_CUMULE;
        if (durée.equals(Durée.MENSUELLE) && etudiant.equals(Etudiant.OUI))
            return TAUX_ETUDIANT;
        if (durée.equals(Durée.ANNUELLE))
            return TAUX_ANNUEL;
        return PAS_DE_REDUCTION;
    }


}
