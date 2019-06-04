package sds.souscriptions.infra;

import sds.souscriptions.concept_metier.Abonnement;
import sds.souscriptions.concept_metier.AbonnementRepository;
import sds.souscriptions.concept_metier.IdAbonnement;

import java.time.LocalDate;
import java.util.Collection;

public class AbonnementRepositoryEnPostgreSQL implements AbonnementRepository {


    @Override
    public Abonnement get(IdAbonnement id) {
        //SELECT * FROM ...
        throw new UnsupportedOperationException("Pas encore implémenté");
    }

    @Override
    public void addOrReplace(Abonnement abonnement) {
        //INSERT || UPDATE
        throw new UnsupportedOperationException("Pas encore implémenté");
    }

    @Override
    public void addOrReplaceAll(Collection<Abonnement> abonnements) {
        //INSERT || UPDATE
        throw new UnsupportedOperationException("Pas encore implémenté");
    }

    @Override
    public Collection<Abonnement> trouveAbonnementsAvec(LocalDate jourDeFin) {
        //SELECT * FROM ...
        throw new UnsupportedOperationException("Pas encore implémenté");
    }
}
