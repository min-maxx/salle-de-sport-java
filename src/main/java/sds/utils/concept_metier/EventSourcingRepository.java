package sds.utils.concept_metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class EventSourcingRepository<ID> {

    protected HashMap<ID, List<Event>> eventsStreams = new HashMap<>();

    protected List<Event> appendStream(ID id, List<Event> changements) {
        List<Event> events = eventsStreams.getOrDefault(id, new ArrayList<>());
        events.addAll(changements);
        eventsStreams.put(id, events);
        return changements;
    }
}
