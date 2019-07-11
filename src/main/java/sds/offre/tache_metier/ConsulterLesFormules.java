package sds.offre.tache_metier;

import sds.offre.concept_metier.Formule;
import sds.offre.concept_metier.FormuleRepository;

import java.util.Collection;

public class ConsulterLesFormules {
    private final FormuleRepository formuleRepository;

    public ConsulterLesFormules(FormuleRepository formuleRepository) {
        this.formuleRepository = formuleRepository;
    }

    public Collection<Formule> consulte() {
        return formuleRepository.toutesLesFormules();
    }
}
