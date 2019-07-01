package sds.offre.concept_metier;

import sds.utils.concept_metier.Event;

import java.util.Collection;
import java.util.List;

public interface FormuleRepository {
    Formule get(IdFormule id);

    List<Event> addOrReplace(Formule formule);

    Collection<Formule> getToutesLesFormules();
}
