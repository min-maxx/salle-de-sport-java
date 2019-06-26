package sds.notification_email.tache_metier;

import sds.notification_email.concept_metier.AdresseEmail;

import java.time.LocalDate;

public class EmailRecapitulatifEnvoyé {
    private final AdresseEmail email;
    private final LocalDate dateEnvoi;

    public static EmailRecapitulatifEnvoyé avec(AdresseEmail email, LocalDate dateEnvoi) {
        return new EmailRecapitulatifEnvoyé(email, dateEnvoi);
    }

    private EmailRecapitulatifEnvoyé(AdresseEmail email, LocalDate dateEnvoi) {
        this.email = email;
        this.dateEnvoi = dateEnvoi;
    }
}
