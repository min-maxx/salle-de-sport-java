package sds.souscriptions.concept_metier;

import sds.utils.concept_metier.Event;

import java.time.LocalDate;
import java.util.Objects;

public class AbonnementRenouvellé implements Event {
    public static AbonnementRenouvellé avec(IdAbonnement idAbonnement, LocalDate jourDeFin) {
        return new AbonnementRenouvellé(idAbonnement, jourDeFin);
    }

    final IdAbonnement idAbonnement;
    final LocalDate jourDeFin;

    private AbonnementRenouvellé(IdAbonnement idAbonnement, LocalDate jourDeFin) {
        this.idAbonnement = idAbonnement;
        this.jourDeFin = jourDeFin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbonnementRenouvellé that = (AbonnementRenouvellé) o;
        return Objects.equals(idAbonnement, that.idAbonnement) &&
                Objects.equals(jourDeFin, that.jourDeFin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAbonnement, jourDeFin);
    }

    @Override
    public String toString() {
        return "AbonnementRenouvellé{" +
                "idAbonnement=" + idAbonnement +
                ", jourDeFin=" + jourDeFin +
                '}';
    }
}
