package sds.notification_email.tache_metier;

import sds.notification_email.concept_metier.*;

import java.time.LocalDate;

public class EnvoyeurDeEmailEnMemoire implements EnvoyeurDeEmail {
    private final LocalDate dateEnvoi;

    EnvoyeurDeEmailEnMemoire(LocalDate dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    @Override
    public EmailRemerciementEnvoyé envoieRemerciement(Abonné abonné) {
        return EmailRemerciementEnvoyé.avec(abonné.idAbonnement(), dateEnvoi, abonné.email());
    }

    @Override
    public EmailRecapitulatifEnvoyé envoieRecapitulatif(Abonné abonné, AbonnementDetail abonnementDetail) {
        return new EmailRecapitulatifEnvoyé();
    }
}
