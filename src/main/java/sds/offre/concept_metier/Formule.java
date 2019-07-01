package sds.offre.concept_metier;

import sds.utils.concept_metier.AggregateRoot;
import sds.utils.concept_metier.Event;

import java.util.List;

public class Formule extends AggregateRoot {

    private IdFormule id;
    private Durée durée;
    private Prix prix;

    public Formule(IdFormule id, Prix prix, Durée durée) {
        applique(FormuleCreee.de(id, prix, durée));
    }

    public Formule(List<Event> events) {
        super(events);
    }


    public void changePrix(Prix nouveauPrix) {
        if (this.prix.equals(nouveauPrix)) return;

        applique(PrixFormuleChangee.de(id, nouveauPrix));
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
    protected void dispatchEvent(Event event) {
        if (event instanceof FormuleCreee)
            appliqueEvent((FormuleCreee) event);
        else if (event instanceof PrixFormuleChangee)
            appliqueEvent((PrixFormuleChangee) event);

    }

    private void appliqueEvent(FormuleCreee formuleCreee) {
        this.id = formuleCreee.id;
        this.prix = formuleCreee.prix;
        this.durée = formuleCreee.durée;
    }

    private void appliqueEvent(PrixFormuleChangee event) {
        prix = event.prix;
    }
}
