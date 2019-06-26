package sds.souscriptions.concept_metier;

import java.time.LocalDate;
import java.util.Optional;

public class Abonnement {
    private IdAbonnement id;


    private IdFormule idFormule;
    private Prix prix;
    private LocalDate jourDeSouscription;
    private LocalDate jourDeFin;
    private Durée durée;

    public AbonnementSouscrit créé(IdAbonnement nouveauId, FormuleChoisie formuleChoisie, Prospect prospect, LocalDate jourDeSouscription) {
        this.id = nouveauId;
        this.idFormule = formuleChoisie.id;
        this.prix = formuleChoisie.prixAbonnementPour(prospect);
        this.jourDeSouscription = jourDeSouscription;
        this.durée = formuleChoisie.durée;
        this.jourDeFin = jourDeSouscription.plusMonths(durée.nombreDeMois());
        return AbonnementSouscrit.avec(id, idFormule, prix, jourDeSouscription, jourDeFin);
    }

    public IdAbonnement id() {
        return id;
    }

    public IdFormule idFormule() {
        return idFormule;
    }

    public Optional<AbonnementRenouvellé> renouvelle(LocalDate jourDeRenouvellement) {
        if (jourDeFin.equals(jourDeRenouvellement)) {
            return Optional.of(AbonnementRenouvellé.avec(id, jourDeFin.plusMonths(durée.nombreDeMois())));
        }
        return Optional.empty();
    }

    public LocalDate jourDeFin() {
        return jourDeFin;
    }

    public LocalDate jourDeSouscription() {
        return jourDeSouscription;
    }
}
