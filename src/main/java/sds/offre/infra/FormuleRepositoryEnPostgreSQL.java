package sds.offre.infra;

import sds.offre.concept_metier.Formule;
import sds.offre.concept_metier.FormuleRepository;
import sds.offre.concept_metier.IdFormule;

import java.util.Collection;

public class FormuleRepositoryEnPostgreSQL implements FormuleRepository {


    @Override
    public Formule get(IdFormule id) {
        //SELECT * FROM ...
        throw new UnsupportedOperationException("Pas encore implémenté");
    }

    @Override
    public void addOrReplace(Formule formule) {
        //INSERT || UPDATE
        throw new UnsupportedOperationException("Pas encore implémenté");
    }

    @Override
    public Collection<Formule> getToutesLesFormules() {
        //SELECT * FROM ...
        throw new UnsupportedOperationException("Pas encore implémenté");
    }
}
