package sds.notification_email.tache_metier;

import sds.notification_email.concept_metier.Abonnement;
import sds.notification_email.concept_metier.Abonné;
import sds.notification_email.concept_metier.EmailRecapitulatifEnvoyé;
import sds.notification_email.concept_metier.EnvoyeurDeEmail;

/**
 * Pour voir un exemple de tests, se rendre à la classe
 *
 * @see sds.souscriptions.tache_metier.RenouvellerAbonnementsAutomatiquement
 */
public class EnvoyerEmailRecapitulatif {


    private final EnvoyeurDeEmail envoyeurDeEmailMailChimp;

    public EnvoyerEmailRecapitulatif(EnvoyeurDeEmail envoyeurDeEmailMailChimp) {
        this.envoyeurDeEmailMailChimp = envoyeurDeEmailMailChimp;
    }

    public EmailRecapitulatifEnvoyé envoie(Abonné abonné, Abonnement abonnement) {
        return envoyeurDeEmailMailChimp.envoieRecapitulatif(abonné, abonnement);
    }
}
