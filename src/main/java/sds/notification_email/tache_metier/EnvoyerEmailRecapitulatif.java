package sds.notification_email.tache_metier;

import sds.notification_email.concept_metier.AbonnementDetail;
import sds.notification_email.concept_metier.Abonné;
import sds.notification_email.concept_metier.AbonnéRepository;
import sds.notification_email.concept_metier.EnvoyeurDeEmail;

import java.time.LocalDate;

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
        LocalDate dateEnvoi = envoyeurDeEmailMailChimp.envoieRecapitulatif(abonné, abonnementDetail);
        return EmailRecapitulatifEnvoyé.avec(abonné.email(), dateEnvoi);
    }
}
