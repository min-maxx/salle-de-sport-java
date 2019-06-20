package sds.souscriptions.tache_metier;

import sds.souscriptions.concept_metier.Abonnement;
import sds.souscriptions.concept_metier.AbonnementRepository;

import java.time.LocalDate;
import java.util.Collection;

public class ChercherAbonnementsParPeriodeDeSouscription {

    private final AbonnementRepository abonnementRepository;

    public ChercherAbonnementsParPeriodeDeSouscription(AbonnementRepository abonnementRepository) {
        this.abonnementRepository = abonnementRepository;
    }

    public Collection<Abonnement> cherche(LocalDate début, LocalDate fin) {
        return abonnementRepository.trouveAbonnementsEnCoursSouscritsEntre(début, fin);
    }
}
