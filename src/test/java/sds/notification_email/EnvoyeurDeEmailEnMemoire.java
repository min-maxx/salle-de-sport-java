package sds.notification_email;

import sds.notification_email.concept_metier.AbonnementDetail;
import sds.notification_email.concept_metier.Abonné;
import sds.notification_email.concept_metier.EnvoyeurDeEmail;

import java.time.LocalDate;

public class EnvoyeurDeEmailEnMemoire implements EnvoyeurDeEmail {
    private final LocalDate dateEnvoi;

    public EnvoyeurDeEmailEnMemoire(LocalDate dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    @Override
    public LocalDate envoieRemerciement(Abonné abonné) {
        return dateEnvoi;
    }

    @Override
    public LocalDate envoieRecapitulatif(Abonné abonné, AbonnementDetail abonnementDetail) {
        return dateEnvoi;
    }
}
