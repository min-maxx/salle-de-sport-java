package sds.offre.tache_metier;

import sds.offre.concept_metier.*;

public class CreerUneFormule {

    private IdFormuleGenerateur idFormuleGenerateur;
    private FormuleRepository formuleRepository;
    private ServiceDeProjectionDesDonnées serviceDeProjectionDesDonnées;

    public CreerUneFormule(IdFormuleGenerateur idFormuleGenerateur, FormuleRepository formuleRepository, ServiceDeProjectionDesDonnées serviceDeProjectionDesDonnées) {
        this.idFormuleGenerateur = idFormuleGenerateur;
        this.formuleRepository = formuleRepository;
        this.serviceDeProjectionDesDonnées = serviceDeProjectionDesDonnées;
    }

    public void crée(Prix prix, Durée durée) {
        Formule formule = new Formule(idFormuleGenerateur.nouveauId(), prix, durée);
        formuleRepository.addOrReplace(formule);
        FormuleCreee formuleCreee = FormuleCreee.de(formule);
        serviceDeProjectionDesDonnées.faitProjection(formuleCreee);
    }
}
