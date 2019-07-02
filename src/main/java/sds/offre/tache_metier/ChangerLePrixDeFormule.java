package sds.offre.tache_metier;

import sds.offre.concept_metier.*;

public class ChangerLePrixDeFormule {
    private FormuleRepository formuleRepository;
    private GérantGateway gérantGateway;

    public ChangerLePrixDeFormule(FormuleRepository formuleRepository, GérantGateway gérantGateway) {
        this.formuleRepository = formuleRepository;
        this.gérantGateway = gérantGateway;
    }

    public void change(IdFormule id, Prix nouveauPrix) {
        Formule formule = formuleRepository.get(id);
        if (formule.lePrixEstIdentique(nouveauPrix)) return;

        formule.changePrix(nouveauPrix);
        formuleRepository.addOrReplace(formule);

        PrixFormuleChangee prixFormuleChangee = PrixFormuleChangee.de(formule);
        gérantGateway.faitProjection(prixFormuleChangee);
    }

}
