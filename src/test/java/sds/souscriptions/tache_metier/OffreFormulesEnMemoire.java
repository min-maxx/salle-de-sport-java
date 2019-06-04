package sds.souscriptions.tache_metier;

import sds.souscriptions.concept_metier.FormuleChoisie;
import sds.souscriptions.concept_metier.IdFormule;
import sds.souscriptions.concept_metier.OffreFormules;

import java.util.Collection;
import java.util.HashMap;

public class OffreFormulesEnMemoire implements OffreFormules {
    private HashMap<IdFormule, FormuleChoisie> formuleMap = new HashMap<>();

    public OffreFormulesEnMemoire(Collection<FormuleChoisie> formuleChoisies) {
        formuleChoisies.forEach(formule -> formuleMap.put(formule.id, formule));
    }


    @Override
    public FormuleChoisie trouveFormuleChoisie(IdFormule idFormule) {
        return formuleMap.get(idFormule);
    }
}
