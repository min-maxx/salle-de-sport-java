package sds.offre.concept_metier;

import java.util.Optional;

public class Formule {

    private IdFormule id;
    private Durée durée;
    private Prix prix;

    public Formule() {

    }

    public Optional<FormuleCreee> créé(IdFormule id, Prix prix, Durée durée) {
        if (estCréée()) return Optional.empty();

        this.id = id;
        this.prix = prix;
        this.durée = durée;
        return Optional.of(FormuleCreee.de(id, prix, durée));

    }

    public Optional<PrixFormuleChangee> changePrix(Prix nouveauPrix) {
        if (!estCréée()) return Optional.empty();
        if (prix.equals(nouveauPrix)) return Optional.empty();

        prix = nouveauPrix;
        return Optional.of(PrixFormuleChangee.de(id, prix));
    }

    private boolean estCréée() {
        return this.id != null;
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
}
