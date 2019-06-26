package sds.notification_email.concept_metier;

import java.time.LocalDate;

public interface EnvoyeurDeEmail {
    LocalDate envoieRemerciement(Abonné abonné);

    LocalDate envoieRecapitulatif(Abonné abonné, AbonnementDetail abonnementDetail);
}
