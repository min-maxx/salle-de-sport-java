package sds.souscriptions.concept_metier;

import java.time.LocalDate;

public class Abonnement {
    private IdAbonnement id;


    private IdFormule idFormule;
    private Prix prix;
    private LocalDate jourDeSouscription;
    private LocalDate jourDeFin;
    private Durée durée;

    public Abonnement(IdAbonnement nouveauId, FormuleChoisie formuleChoisie, Prospect prospect, LocalDate jourDeSouscription) {
        this.id = nouveauId;
        this.idFormule = formuleChoisie.id;
        this.prix = formuleChoisie.prixAbonnementPour(prospect);
        this.jourDeSouscription = jourDeSouscription;
        this.durée = formuleChoisie.durée;
        this.jourDeFin = jourDeSouscription.plusMonths(durée.nombreDeMois());
    }

    public IdAbonnement id() {
        return id;
    }

    public IdFormule idFormule() {
        return idFormule;
    }

    public Prix prix() {
        return prix;
    }

    public void renouvelle(LocalDate jourDeRenouvellement) {
        if (jourDeFin.equals(jourDeRenouvellement)) {
            this.jourDeFin = this.jourDeFin.plusMonths(durée.nombreDeMois());
        }
    }

    public LocalDate jourDeFin() {
        return jourDeFin;
    }

    public LocalDate jourDeSouscription() {
        return jourDeSouscription;
    }
}
