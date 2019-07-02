package sds.souscriptions.tache_metier;

import sds.souscriptions.concept_metier.Prospect;

public interface ServiceDeNotification {
    void envoieRecapitulatif(Prospect prospect, AbonnementSouscrit abonnementSouscrit);
}
