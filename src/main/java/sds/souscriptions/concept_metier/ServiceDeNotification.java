package sds.souscriptions.concept_metier;

import sds.souscriptions.tache_metier.AbonnementSouscrit;

public interface ServiceDeNotification {
    void envoieRecapitulatif(Prospect prospect, AbonnementSouscrit abonnementSouscrit);
}
