package sds.offre;

import sds.offre.concept_metier.Formule;
import sds.offre.concept_metier.FormuleRepository;
import sds.offre.concept_metier.IdFormule;
import sds.utils.concept_metier.Event;
import sds.utils.concept_metier.EventSourcingRepository;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class FormuleRepositoryEnMemoire extends EventSourcingRepository<IdFormule> implements FormuleRepository {

    @Override
    public Formule get(IdFormule id) {
        return new Formule(eventsStreams.get(id));
    }

    @Override
    public List<Event> addOrReplace(Formule formule) {
        return appendStream(formule.id(), formule.changements());
    }

    @Override
    public Collection<Formule> getToutesLesFormules() {
        return eventsStreams.keySet().stream()
                .map(id -> new Formule(eventsStreams.get(id)))
                .collect(toList());
    }

}
