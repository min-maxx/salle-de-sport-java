package sds.souscriptions.concept_metier;

import java.time.LocalDate;
import java.util.Objects;

public class AbonnementSouscrit {

    final IdAbonnement id;
    final IdFormule idFormule;
    final Prix prix;
    final LocalDate jourDeSouscription;
    final LocalDate jourDeFin;

    private AbonnementSouscrit(IdAbonnement id, IdFormule idFormule, Prix prix, LocalDate jourDeSouscription, LocalDate jourDeFin) {
        this.id = id;
        this.idFormule = idFormule;
        this.prix = prix;
        this.jourDeSouscription = jourDeSouscription;
        this.jourDeFin = jourDeFin;
    }

    public static AbonnementSouscrit avec(IdAbonnement id, IdFormule idFormule, Prix prix, LocalDate jourDeSouscription, LocalDate jourDeFin) {
        return new AbonnementSouscrit(id, idFormule, prix, jourDeSouscription, jourDeFin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbonnementSouscrit that = (AbonnementSouscrit) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(idFormule, that.idFormule) &&
                Objects.equals(prix, that.prix) &&
                Objects.equals(jourDeSouscription, that.jourDeSouscription) &&
                Objects.equals(jourDeFin, that.jourDeFin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idFormule, prix, jourDeSouscription, jourDeFin);
    }

    @Override
    public String toString() {
        return "AbonnementSouscrit{" +
                "id=" + id +
                ", idFormule=" + idFormule +
                ", prix=" + prix +
                ", jourDeSouscription=" + jourDeSouscription +
                ", jourDeFin=" + jourDeFin +
                '}';
    }
}
