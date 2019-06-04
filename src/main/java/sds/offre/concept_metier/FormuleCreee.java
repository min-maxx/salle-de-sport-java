package sds.offre.concept_metier;

import java.util.Objects;

public class FormuleCreee {
    final IdFormule id;
    final Prix prix;
    final Durée durée;

    private FormuleCreee(IdFormule id, Prix prix, Durée durée) {
        this.id = id;
        this.prix = prix;
        this.durée = durée;
    }

    public static FormuleCreee de(IdFormule id, Prix prix, Durée durée) {
        return new FormuleCreee(id, prix, durée);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormuleCreee that = (FormuleCreee) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(prix, that.prix) &&
                durée == that.durée;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, prix, durée);
    }

    @Override
    public String toString() {
        return "FormuleCreee{" +
                "id=" + id +
                ", prix=" + prix +
                ", durée=" + durée +
                '}';
    }
}
