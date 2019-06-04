package sds.offre.concept_metier;

import java.util.Collection;

public interface FormuleRepository {
    Formule get(IdFormule id);

    void addOrReplace(Formule formule);

    Collection<Formule> getToutesLesFormules();
}
