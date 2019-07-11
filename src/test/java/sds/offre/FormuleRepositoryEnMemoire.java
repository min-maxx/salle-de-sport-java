package sds.offre;

import sds.offre.concept_metier.Formule;
import sds.offre.concept_metier.FormuleRepository;
import sds.offre.concept_metier.IdFormule;

import java.util.Collection;
import java.util.HashMap;

public class FormuleRepositoryEnMemoire implements FormuleRepository {

    private HashMap<IdFormule, Formule> formuleMap = new HashMap<>();

    @Override
    public Formule get(IdFormule id) {
        return formuleMap.get(id);
    }

    @Override
    public void addOrReplace(Formule formule) {
        formuleMap.put(formule.getId(), formule);
    }

    @Override
    public Collection<Formule> getToutesLesFormules() {
        return formuleMap.values();
    }
}
