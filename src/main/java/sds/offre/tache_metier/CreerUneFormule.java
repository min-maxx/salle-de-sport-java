package sds.offre.tache_metier;

import sds.offre.concept_metier.*;

import java.util.Optional;

public class CreerUneFormule {

    private IdFormuleGenerateur idFormuleGenerateur;
    private FormuleRepository formuleRepository;

    public CreerUneFormule(IdFormuleGenerateur idFormuleGenerateur, FormuleRepository formuleRepository) {
        this.idFormuleGenerateur = idFormuleGenerateur;
        this.formuleRepository = formuleRepository;
    }

    public Optional<FormuleCreee> crée(Prix prix, Durée durée) {
        Formule formule = new Formule();

        Optional<FormuleCreee> formuleCreee = formule.créé(idFormuleGenerateur.nouveauId(), prix, durée);

        formuleRepository.addOrReplace(formule);

        return formuleCreee;
    }
}
