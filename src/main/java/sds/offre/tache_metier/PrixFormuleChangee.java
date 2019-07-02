package sds.offre.tache_metier;

import sds.offre.concept_metier.Formule;
import sds.offre.concept_metier.IdFormule;
import sds.offre.concept_metier.Prix;

import java.util.Objects;

public class PrixFormuleChangee {
    public static PrixFormuleChangee de(IdFormule id, Prix prix) {
        return new PrixFormuleChangee(id, prix);
    }

    public static PrixFormuleChangee de(Formule formule) {
        return new PrixFormuleChangee(formule.id(), formule.prixDeBase());
    }

    public final IdFormule id;
    public final Prix prix;

    private PrixFormuleChangee(IdFormule id, Prix prix) {
        this.id = id;
        this.prix = prix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrixFormuleChangee that = (PrixFormuleChangee) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(prix, that.prix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, prix);
    }

    @Override
    public String toString() {
        return "PrixFormuleChangee{" +
                "id=" + id +
                ", prix=" + prix +
                '}';
    }
}
