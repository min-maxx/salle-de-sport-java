package sds.notification_email.concept_metier;

import java.time.LocalDate;

public class Abonnement {
    private final LocalDate jourDeFin;

    public Abonnement(LocalDate jourDeFin) {
        this.jourDeFin = jourDeFin;
    }

    public LocalDate getJourDeFin() {
        return jourDeFin;
    }
}
