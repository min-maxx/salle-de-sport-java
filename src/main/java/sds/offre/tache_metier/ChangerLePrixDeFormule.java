package sds.offre.tache_metier;

import sds.offre.concept_metier.*;
import sds.utils.concept_metier.Event;

import java.util.List;
import java.util.Optional;

public class ChangerLePrixDeFormule {
    private FormuleRepository formuleRepository;

    public ChangerLePrixDeFormule(FormuleRepository formuleRepository) {
        this.formuleRepository = formuleRepository;
    }

    public Optional<PrixFormuleChangee> change(IdFormule id, Prix nouveauPrix) {
        Formule formule = formuleRepository.get(id);

        formule.changePrix(nouveauPrix);

        List<Event> events = formuleRepository.addOrReplace(formule);

        return events.size() > 0 ?
                Optional.of((PrixFormuleChangee) events.get(0)) :
                Optional.empty();


    }
}
