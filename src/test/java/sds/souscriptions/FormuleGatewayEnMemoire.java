package sds.souscriptions;

import sds.souscriptions.concept_metier.FormuleChoisie;
import sds.souscriptions.concept_metier.FormuleGateway;
import sds.souscriptions.concept_metier.IdFormule;

import java.util.Collection;
import java.util.HashMap;

public class FormuleGatewayEnMemoire implements FormuleGateway {
    private HashMap<IdFormule, FormuleChoisie> formuleMap = new HashMap<>();

    public FormuleGatewayEnMemoire(Collection<FormuleChoisie> formuleChoisies) {
        formuleChoisies.forEach(formule -> formuleMap.put(formule.id, formule));
    }


    @Override
    public FormuleChoisie trouveFormuleChoisie(IdFormule idFormule) {
        return formuleMap.get(idFormule);
    }
}
