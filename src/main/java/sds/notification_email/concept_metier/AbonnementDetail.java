package sds.notification_email.concept_metier;

import java.time.LocalDate;
import java.util.Objects;

public class AbonnementDetail {
    private final LocalDate jourDeFin;

    private AbonnementDetail(LocalDate jourDeFin) {
        this.jourDeFin = jourDeFin;
    }

    public static AbonnementDetail avec(LocalDate jourDeFin) {
        return new AbonnementDetail(jourDeFin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbonnementDetail that = (AbonnementDetail) o;
        return Objects.equals(jourDeFin, that.jourDeFin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jourDeFin);
    }

    @Override
    public String toString() {
        return "AbonnementDetail{" +
                "jourDeFin=" + jourDeFin +
                '}';
    }
}
