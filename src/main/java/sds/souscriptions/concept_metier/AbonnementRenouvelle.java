package sds.souscriptions.concept_metier;

import java.time.LocalDate;
import java.util.Objects;

public class AbonnementRenouvelle {
    public static AbonnementRenouvelle avec(IdAbonnement idAbonnement, LocalDate jourDeFin) {
        return new AbonnementRenouvelle(idAbonnement, jourDeFin);
    }

    private final IdAbonnement idAbonnement;
    private final LocalDate jourDeFin;

    private AbonnementRenouvelle(IdAbonnement idAbonnement, LocalDate jourDeFin) {
        this.idAbonnement = idAbonnement;
        this.jourDeFin = jourDeFin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbonnementRenouvelle that = (AbonnementRenouvelle) o;
        return Objects.equals(idAbonnement, that.idAbonnement) &&
                Objects.equals(jourDeFin, that.jourDeFin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAbonnement, jourDeFin);
    }

    @Override
    public String toString() {
        return "AbonnementRenouvelle{" +
                "idAbonnement=" + idAbonnement +
                ", jourDeFin=" + jourDeFin +
                '}';
    }
}
