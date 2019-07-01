package sds.offre;

import sds.offre.concept_metier.Formule;
import sds.offre.concept_metier.FormuleRepository;
import sds.offre.concept_metier.IdFormule;
import sds.utils.concept_metier.Event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class FormuleRepositoryEnMemoire implements FormuleRepository {

    private HashMap<IdFormule, List<Event>> eventsStreams = new HashMap<>();

    @Override
    public Formule get(IdFormule id) {
        return new Formule(eventsStreams.get(id));
    }

    @Override
    public List<Event> addOrReplace(Formule formule) {
        List<Event> events = eventsStreams.getOrDefault(formule.Id(), new ArrayList<>());
        List<Event> changements = formule.changements();
        events.addAll(changements);
        eventsStreams.put(formule.Id(), events);
        return changements;
    }

    @Override
    public Collection<Formule> getToutesLesFormules() {
        return eventsStreams.keySet().stream()
                .map(id -> new Formule(eventsStreams.get(id)))
                .collect(toList());
    }
}
