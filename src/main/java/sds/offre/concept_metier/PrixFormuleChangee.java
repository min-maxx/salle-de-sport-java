package sds.offre.concept_metier;

import java.util.Objects;

public class PrixFormuleChangee {
    public static PrixFormuleChangee de(IdFormule id, Prix prix) {
        return new PrixFormuleChangee(id, prix);
    }

    final IdFormule id;
    final Prix prix;

    PrixFormuleChangee(IdFormule id, Prix prix) {
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