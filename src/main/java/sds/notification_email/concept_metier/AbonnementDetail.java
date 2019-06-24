package sds.notification_email.concept_metier;

import java.time.LocalDate;

public class AbonnementDetail {
    private final LocalDate jourDeFin;

    public AbonnementDetail(LocalDate jourDeFin) {
        this.jourDeFin = jourDeFin;
    }

    public LocalDate getJourDeFin() {
        return jourDeFin;
    }
}
