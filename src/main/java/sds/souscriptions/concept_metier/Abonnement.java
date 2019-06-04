package sds.souscriptions.concept_metier;

import java.time.LocalDate;

public class Abonnement {
    private IdAbonnement id;
    private IdFormule idFormule;
    private Prix prix;
    private LocalDate jourDeSouscription;
    private LocalDate jourDeFin;

    public AbonnementSouscrit créé(IdAbonnement nouveauId, FormuleChoisie formuleChoisie, Prospect prospect, LocalDate jourDeSouscription) {
        this.id = nouveauId;
        this.idFormule = formuleChoisie.id;
        this.prix = formuleChoisie.prixAbonnementPour(prospect);
        this.jourDeSouscription = jourDeSouscription;
        this.jourDeFin = jourDeSouscription.plusMonths(formuleChoisie.durée.nombreDeMois());
        return AbonnementSouscrit.avec(id, idFormule, prix, jourDeSouscription, jourDeFin);
    }

    public IdAbonnement Id() {
        return id;
    }

    public AbonnementRenouvelle renouvelle() {
        return null;
    }

    public boolean finisLe(LocalDate jourDeFin) {
        return this.jourDeFin.equals(jourDeFin);
    }
}
