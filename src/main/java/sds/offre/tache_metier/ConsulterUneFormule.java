package sds.offre.tache_metier;

import sds.offre.concept_metier.Formule;
import sds.offre.concept_metier.FormuleRepository;
import sds.offre.concept_metier.IdFormule;

public class ConsulterUneFormule {

    private FormuleRepository formuleRepository;

    public ConsulterUneFormule(FormuleRepository formuleRepository) {
        this.formuleRepository = formuleRepository;
    }

    public Formule consulte(IdFormule idFormule) {
        return formuleRepository.get(idFormule);
    }

}
