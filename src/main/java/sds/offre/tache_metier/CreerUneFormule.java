package sds.offre.tache_metier;

import sds.offre.concept_metier.*;

public class CreerUneFormule {

    private IdFormuleGenerateur idFormuleGenerateur;
    private FormuleRepository formuleRepository;
    private GérantGateway gérantGateway;

    public CreerUneFormule(IdFormuleGenerateur idFormuleGenerateur, FormuleRepository formuleRepository, GérantGateway gérantGateway) {
        this.idFormuleGenerateur = idFormuleGenerateur;
        this.formuleRepository = formuleRepository;
        this.gérantGateway = gérantGateway;
    }

    public void crée(Prix prix, Durée durée) {
        Formule formule = new Formule(idFormuleGenerateur.nouveauId(), prix, durée);
        formuleRepository.addOrReplace(formule);
        FormuleCreee formuleCreee = FormuleCreee.de(formule);
        gérantGateway.faitProjection(formuleCreee);
    }
}
