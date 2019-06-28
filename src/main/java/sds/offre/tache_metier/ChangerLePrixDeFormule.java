package sds.offre.tache_metier;

import sds.offre.concept_metier.*;

import java.util.Optional;

public class ChangerLePrixDeFormule {
    private FormuleRepository formuleRepository;

    public ChangerLePrixDeFormule(FormuleRepository formuleRepository) {
        this.formuleRepository = formuleRepository;
    }

    public Optional<PrixFormuleChangee> change(IdFormule id, Prix nouveauPrix) {
        Formule formule = formuleRepository.get(id);
        if (formule.lePrixEstIdentique(nouveauPrix)) return Optional.empty();

        formule.changePrix(nouveauPrix);
        formuleRepository.addOrReplace(formule);
        return Optional.of(PrixFormuleChangee.de(id, formule.prixDeBase()));
    }

}
