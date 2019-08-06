package sds.souscriptions.tache_metier;


import sds.souscriptions.concept_metier.*;
import sds.utils.concept_metier.Event;

import java.time.LocalDate;
import java.util.List;

public class AbonnerProspectAFormule {

    private IdAbonnementGenerateur idAbonnementGenerateur;
    private DateGenerateur dateGenerateur;
    private FormuleGateway formuleGateway;
    private AbonnementRepository abonnementRepository;
    private ServiceDeNotification serviceDeNotification;

    public AbonnerProspectAFormule(IdAbonnementGenerateur idAbonnementGenerateur, FormuleGateway formuleGateway, DateGenerateur dateGenerateur, AbonnementRepository abonnementRepository, ServiceDeNotification serviceDeNotification) {
        this.idAbonnementGenerateur = idAbonnementGenerateur;
        this.formuleGateway = formuleGateway;
        this.dateGenerateur = dateGenerateur;
        this.abonnementRepository = abonnementRepository;
        this.serviceDeNotification = serviceDeNotification;
    }


    public AbonnementSouscrit abonne(Prospect prospect, IdFormule idFormule) {
        FormuleChoisie formuleChoisie = formuleGateway.trouveFormuleChoisie(idFormule);
        LocalDate jourDeSouscription = dateGenerateur.aujourdhui();

        Abonnement abonnement = new Abonnement(idAbonnementGenerateur.nouveauId(), formuleChoisie, prospect, jourDeSouscription);

        List<Event> events = abonnementRepository.addOrReplace(abonnement);

        AbonnementSouscrit abonnementSouscrit =(AbonnementSouscrit) events.get(0);
                serviceDeNotification.envoieRecapitulatif(prospect, abonnementSouscrit);

        return abonnementSouscrit;
    }
}
