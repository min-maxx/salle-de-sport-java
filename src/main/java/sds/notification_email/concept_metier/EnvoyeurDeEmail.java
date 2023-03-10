package sds.notification_email.concept_metier;

public interface EnvoyeurDeEmail {
    EmailRemerciementEnvoyé envoieRemerciement(Abonné abonné);

    EmailRecapitulatifEnvoyé envoieRecapitulatif(Abonné abonné, AbonnementDetail abonnementDetail);
}
