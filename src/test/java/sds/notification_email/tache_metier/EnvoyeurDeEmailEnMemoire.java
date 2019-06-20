package sds.notification_email.tache_metier;

import sds.notification_email.concept_metier.Abonné;
import sds.notification_email.concept_metier.EmailRemerciementEnvoyé;
import sds.notification_email.concept_metier.EnvoyeurDeEmail;

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
}
