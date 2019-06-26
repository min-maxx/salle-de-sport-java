package sds.notification_email.infra;

import sds.notification_email.concept_metier.AbonnementDetail;
import sds.notification_email.concept_metier.Abonné;
import sds.notification_email.concept_metier.EnvoyeurDeEmail;

import java.time.LocalDate;

public class EnvoyeurDeEmailMailChimp implements EnvoyeurDeEmail {

    @Override
    public LocalDate envoieRemerciement(Abonné abonné) {
        throw new UnsupportedOperationException("Pas encore implémenté");
    }

    @Override
    public LocalDate envoieRecapitulatif(Abonné abonné, AbonnementDetail abonnementDetail) {
        throw new UnsupportedOperationException("Pas encore implémenté");
    }
}
