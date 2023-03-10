package sds.souscriptions.concept_metier;

import sds.utils.concept_metier.Event;

import java.time.LocalDate;
import java.util.Objects;

public class AbonnementSouscrit implements Event {

    public final IdAbonnement id;
    public final IdFormule idFormule;
    public final Prix prix;
    public final Durée durée;
    public final LocalDate jourDeSouscription;
    public final LocalDate jourDeFin;

    private AbonnementSouscrit(IdAbonnement id, IdFormule idFormule, Prix prix, Durée durée, LocalDate jourDeSouscription, LocalDate jourDeFin) {
        this.id = id;
        this.idFormule = idFormule;
        this.prix = prix;
        this.durée = durée;
        this.jourDeSouscription = jourDeSouscription;
        this.jourDeFin = jourDeFin;
    }

    public static AbonnementSouscrit avec(IdAbonnement id, IdFormule idFormule, Prix prix, Durée durée, LocalDate jourDeSouscription, LocalDate jourDeFin) {
        return new AbonnementSouscrit(id, idFormule, prix, durée, jourDeSouscription, jourDeFin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbonnementSouscrit that = (AbonnementSouscrit) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(idFormule, that.idFormule) &&
                Objects.equals(prix, that.prix) &&
                durée == that.durée &&
                Objects.equals(jourDeSouscription, that.jourDeSouscription) &&
                Objects.equals(jourDeFin, that.jourDeFin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idFormule, prix, durée, jourDeSouscription, jourDeFin);
    }

    @Override
    public String toString() {
        return "AbonnementSouscrit{" +
                "id=" + id +
                ", idFormule=" + idFormule +
                ", prix=" + prix +
                ", durée=" + durée +
                ", jourDeSouscription=" + jourDeSouscription +
                ", jourDeFin=" + jourDeFin +
                '}';
    }
}
