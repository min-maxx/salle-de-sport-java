package sds.notification_email.infra;

import sds.notification_email.concept_metier.*;

public class EnvoyeurDeEmailMailChimp implements EnvoyeurDeEmail {

    @Override
    public EmailRemerciementEnvoyé envoieRemerciement(Abonné abonné) {
        throw new UnsupportedOperationException("Pas encore implémenté");
    }

    @Override
    public EmailRecapitulatifEnvoyé envoieRecapitulatif(Abonné abonné, AbonnementDetail abonnementDetail) {
        throw new UnsupportedOperationException("Pas encore implémenté");
    }
}
