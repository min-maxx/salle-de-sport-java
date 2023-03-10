package sds.offre.tache_metier;

import sds.offre.concept_metier.*;
import sds.utils.concept_metier.Event;

import java.util.List;
import java.util.Optional;

public class CreerUneFormule {

    private IdFormuleGenerateur idFormuleGenerateur;
    private FormuleRepository formuleRepository;

    public CreerUneFormule(IdFormuleGenerateur idFormuleGenerateur, FormuleRepository formuleRepository) {
        this.idFormuleGenerateur = idFormuleGenerateur;
        this.formuleRepository = formuleRepository;
    }

    public Optional<FormuleCreee> crée(Prix prix, Durée durée) {
        Formule formule = new Formule(idFormuleGenerateur.nouveauId(), prix, durée);

        List<Event> events = formuleRepository.addOrReplace(formule);

        return events.size() > 0 ?
                Optional.of((FormuleCreee) events.get(0)) :
                Optional.empty();
    }
}
