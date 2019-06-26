package sds.souscriptions.tache_metier;


import sds.souscriptions.concept_metier.*;

import java.time.LocalDate;

public class AbonnerProspectAFormule {

    private IdAbonnementGenerateur idAbonnementGenerateur;
    private DateGenerateur dateGenerateur;
    private FormuleGateway formuleGateway;
    private AbonnementRepository abonnementRepository;

    public AbonnerProspectAFormule(IdAbonnementGenerateur idAbonnementGenerateur, FormuleGateway formuleGateway, DateGenerateur dateGenerateur, AbonnementRepository abonnementRepository) {
        this.idAbonnementGenerateur = idAbonnementGenerateur;
        this.formuleGateway = formuleGateway;
        this.dateGenerateur = dateGenerateur;
        this.abonnementRepository = abonnementRepository;
    }


    public AbonnementSouscrit abonne(Prospect prospect, IdFormule idFormule) {
        FormuleChoisie formuleChoisie = formuleGateway.trouveFormuleChoisie(idFormule);
        LocalDate jourDeSouscription = dateGenerateur.aujourdhui();

        Abonnement abonnement = new Abonnement();
        AbonnementSouscrit abonnementSouscrit = abonnement.créé(idAbonnementGenerateur.nouveauId(), formuleChoisie, prospect, jourDeSouscription);

        abonnementRepository.addOrReplace(abonnement);

        return abonnementSouscrit;
    }
}
