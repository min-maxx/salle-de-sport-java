package sds.souscriptions.tache_metier;


import sds.souscriptions.concept_metier.*;

import java.time.LocalDate;

public class AbonnerProspectAFormule {

    private IdAbonnementGenerateur idAbonnementGenerateur;
    private DateGenerateur dateGenerateur;
    private FormuleGateway formuleGateway;
    private AbonnementRepository abonnementRepository;
    private ServiceDeProjectionDesDonnées serviceDeProjectionDesDonnées;
    private ServiceDeNotification serviceDeNotification;

    public AbonnerProspectAFormule(IdAbonnementGenerateur idAbonnementGenerateur, FormuleGateway formuleGateway, DateGenerateur dateGenerateur, AbonnementRepository abonnementRepository, ServiceDeProjectionDesDonnées serviceDeProjectionDesDonnées, ServiceDeNotification serviceDeNotification) {
        this.idAbonnementGenerateur = idAbonnementGenerateur;
        this.formuleGateway = formuleGateway;
        this.dateGenerateur = dateGenerateur;
        this.abonnementRepository = abonnementRepository;
        this.serviceDeProjectionDesDonnées = serviceDeProjectionDesDonnées;
        this.serviceDeNotification = serviceDeNotification;
    }


    public void abonne(Prospect prospect, IdFormule idFormule) {
        FormuleChoisie formuleChoisie = formuleGateway.trouveFormuleChoisie(idFormule);
        LocalDate jourDeSouscription = dateGenerateur.aujourdhui();

        Abonnement abonnement = new Abonnement(idAbonnementGenerateur.nouveauId(), formuleChoisie, prospect, jourDeSouscription);
        AbonnementSouscrit abonnementSouscrit = AbonnementSouscrit.de(abonnement);

        abonnementRepository.addOrReplace(abonnement);

        serviceDeProjectionDesDonnées.faitProjection(abonnementSouscrit);
        serviceDeNotification.envoieRecapitulatif(prospect, abonnementSouscrit);
    }
}
