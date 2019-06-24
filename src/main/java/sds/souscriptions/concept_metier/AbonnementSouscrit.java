package sds.souscriptions.concept_metier;

import java.time.LocalDate;
import java.util.Objects;

public class AbonnementSouscrit {

    public final IdAbonnement id;
    public final IdFormule idFormule;
    public final Prix prix;
    public final LocalDate jourDeSouscription;
    public final LocalDate jourDeFin;
    public final String email;

    private AbonnementSouscrit(IdAbonnement id, IdFormule idFormule, Prix prix, LocalDate jourDeSouscription, LocalDate jourDeFin, String email) {
        this.id = id;
        this.idFormule = idFormule;
        this.prix = prix;
        this.jourDeSouscription = jourDeSouscription;
        this.jourDeFin = jourDeFin;
        this.email = email;
    }

    public static AbonnementSouscrit avec(IdAbonnement id, IdFormule idFormule, Prix prix, LocalDate jourDeSouscription, LocalDate jourDeFin, String email) {
        return new AbonnementSouscrit(id, idFormule, prix, jourDeSouscription, jourDeFin, email);
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
