package sds.notification_email;

import sds.notification_email.concept_metier.*;

import java.time.LocalDate;

public class EnvoyeurDeEmailEnMemoire implements EnvoyeurDeEmail {
    private final LocalDate dateEnvoi;

    public EnvoyeurDeEmailEnMemoire(LocalDate dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    @Override
    public EmailRemerciementEnvoyé envoieRemerciement(Abonné abonné) {
        return EmailRemerciementEnvoyé.avec(abonné.id(), dateEnvoi, abonné.email());
    }

    @Override
    public EmailRecapitulatifEnvoyé envoieRecapitulatif(Abonné abonné, AbonnementDetail abonnementDetail) {
        return new EmailRecapitulatifEnvoyé();
    }
}
