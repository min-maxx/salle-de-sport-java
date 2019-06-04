package sds.souscriptions.infra;

import sds.offre.concept_metier.Formule;
import sds.offre.concept_metier.FormuleRepository;
import sds.offre.concept_metier.IdFormule;

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
}
