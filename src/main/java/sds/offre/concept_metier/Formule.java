package sds.offre.concept_metier;

import java.util.Objects;
import java.util.Optional;

public class Formule {

    private IdFormule id;
    private Durée durée;
    private Prix prix;

    public Formule(IdFormule idFormule, Prix prix, Durée durée) {
        this.id = idFormule;
        this.prix = prix;
        this.durée = durée;
    }

    public Optional<PrixFormuleChangee> changePrix(Prix nouveauPrix) {
        if (prix.equals(nouveauPrix)) return Optional.empty();

        prix = nouveauPrix;
        return Optional.of(PrixFormuleChangee.de(id, prix));
    }

    public IdFormule id() {
        return id;
    }

    public Prix prixDeBase() {
        return prix;
    }

    public Durée durée() {
        return durée;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Formule formule = (Formule) o;
        return Objects.equals(id, formule.id) &&
                durée == formule.durée &&
                Objects.equals(prix, formule.prix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, durée, prix);
    }

    @Override
    public String toString() {
        return "Formule{" +
                "id=" + id +
                ", durée=" + durée +
                ", prix=" + prix +
                '}';
    }
}
