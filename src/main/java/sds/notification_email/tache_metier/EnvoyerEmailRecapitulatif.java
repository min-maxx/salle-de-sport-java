package sds.notification_email.tache_metier;

import sds.notification_email.concept_metier.*;

/**
 * Pour voir un exemple de tests, se rendre à la classe
 *
 * @see sds.souscriptions.tache_metier.RenouvellerAbonnementsAutomatiquement
 */
public class EnvoyerEmailRecapitulatif {


    private final AbonnéRepository abonnéRepository;
    private final EnvoyeurDeEmail envoyeurDeEmailMailChimp;

    public EnvoyerEmailRecapitulatif(AbonnéRepository abonnéRepository, EnvoyeurDeEmail envoyeurDeEmailMailChimp) {
        this.abonnéRepository = abonnéRepository;
        this.envoyeurDeEmailMailChimp = envoyeurDeEmailMailChimp;
    }

    public EmailRecapitulatifEnvoyé envoie(Abonné abonné, AbonnementDetail abonnementDetail) {
        abonnéRepository.addOrReplace(abonné);
        return envoyeurDeEmailMailChimp.envoieRecapitulatif(abonné, abonnementDetail);
    }
}
