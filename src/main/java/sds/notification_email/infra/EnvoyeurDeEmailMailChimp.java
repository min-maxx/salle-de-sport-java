package sds.notification_email.infra;

import sds.notification_email.concept_metier.Abonné;
import sds.notification_email.concept_metier.EmailRemerciementEnvoyé;
import sds.notification_email.concept_metier.EnvoyeurDeEmail;

public class EnvoyeurDeEmailMailChimp implements EnvoyeurDeEmail {

    @Override
    public EmailRemerciementEnvoyé envoieRemerciement(Abonné abonné) {
        throw new UnsupportedOperationException("Pas encore implémenté");
    }
}
