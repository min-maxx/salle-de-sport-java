package sds.offre;

import sds.offre.concept_metier.Formule;
import sds.offre.concept_metier.FormuleRepository;
import sds.offre.concept_metier.IdFormule;

import java.util.HashMap;

public class FormuleRepositoryEnMemoire implements FormuleRepository {

    private HashMap<IdFormule, Formule> formuleMap = new HashMap<>();

    @Override
    public Formule get(IdFormule id) {
        return formuleMap.get(id);
    }

    @Override
    public void addOrReplace(Formule formule) {
        formuleMap.put(formule.Id(), formule);
    }
}
