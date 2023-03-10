package sds.souscriptions.concept_metier;

import sds.utils.concept_metier.AggregateRoot;
import sds.utils.concept_metier.Event;

import java.time.LocalDate;
import java.util.List;

public class Abonnement extends AggregateRoot {
    private IdAbonnement id;
    private IdFormule idFormule;
    private LocalDate jourDeSouscription;
    private LocalDate jourDeFin;
    private Durée durée;

    public Abonnement(List<Event> historiqueEvents) {
        super(historiqueEvents);
    }

    public Abonnement(IdAbonnement nouveauId, FormuleChoisie formuleChoisie, Prospect prospect, LocalDate jourDeSouscription) {
        Prix prix = formuleChoisie.prixAbonnementPour(prospect);
        LocalDate jourDeFin = jourDeSouscription.plusMonths(formuleChoisie.durée.nombreDeMois());
        applique(AbonnementSouscrit.avec(
                nouveauId,
                formuleChoisie.id,
                prix,
                formuleChoisie.durée,
                jourDeSouscription,
                jourDeFin));

    }

    public IdAbonnement id() {
        return id;
    }

    public IdFormule idFormule() {
        return idFormule;
    }

    public void renouvelle(LocalDate jourDeRenouvellement) {
        if (!jourDeFin.equals(jourDeRenouvellement)) return;

        applique(AbonnementRenouvellé.avec(id, jourDeFin.plusMonths(durée.nombreDeMois())));
    }

    public LocalDate jourDeFin() {
        return jourDeFin;
    }

    @Override
    protected void dispatchEvent(Event event) {
        if (event instanceof AbonnementSouscrit)
            appliqueEvent((AbonnementSouscrit) event);
        else if (event instanceof AbonnementRenouvellé)
            appliqueEvent((AbonnementRenouvellé) event);
    }

    private void appliqueEvent(AbonnementSouscrit event) {
        this.id = event.id;
        this.idFormule = event.idFormule;
        this.jourDeSouscription = event.jourDeSouscription;
        this.durée = event.durée;
        this.jourDeFin = event.jourDeFin;
    }

    private void appliqueEvent(AbonnementRenouvellé event) {
        this.jourDeFin = event.jourDeFin;
    }
}
