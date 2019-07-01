package sds.utils.concept_metier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AggregateRoot {
    private Collection<Event> events = new ArrayList<>();

    protected AggregateRoot(List<Event> historiqueEvents) {
        for (Event event : historiqueEvents) {
            appliqueEvent(event, false);
        }
    }

    protected AggregateRoot() {
    }

    protected void applique(Event event) {
        appliqueEvent(event, true);

    }

    private void appliqueEvent(Event event, boolean ajouter) {
        dispatchEvent(event);
        if (ajouter) events.add(event);
    }

    protected abstract void dispatchEvent(Event event);

    public List<Event> changements() {
        List<Event> events = new ArrayList<>(this.events);
        this.events.clear();
        return events;
    }
}
