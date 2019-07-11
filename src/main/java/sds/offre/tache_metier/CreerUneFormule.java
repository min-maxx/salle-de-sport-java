package sds.offre.tache_metier;

import sds.offre.concept_metier.*;

public class CreerUneFormule {

    private IdFormuleGenerateur idFormuleGenerateur;
    private FormuleRepository formuleRepository;

    public CreerUneFormule(IdFormuleGenerateur idFormuleGenerateur, FormuleRepository formuleRepository) {
        this.idFormuleGenerateur = idFormuleGenerateur;
        this.formuleRepository = formuleRepository;
    }

    public FormuleCreee crée(Prix prix, Durée durée) {
        Formule formule = new Formule(idFormuleGenerateur.nouveauId(), durée, prix);
        formuleRepository.addOrReplace(formule);
        return FormuleCreee.de(formule);
    }
}
