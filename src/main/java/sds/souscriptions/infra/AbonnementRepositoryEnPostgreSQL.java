package sds.souscriptions.infra;

import sds.souscriptions.concept_metier.Abonnement;
import sds.souscriptions.concept_metier.AbonnementRepository;
import sds.souscriptions.concept_metier.IdAbonnement;
import sds.souscriptions.concept_metier.IdFormule;
import sds.utils.concept_metier.Event;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class AbonnementRepositoryEnPostgreSQL implements AbonnementRepository {


    @Override
    public Abonnement get(IdAbonnement id) {
        //SELECT * FROM ...
        throw new UnsupportedOperationException("Pas encore implémenté");
    }

    @Override
    public List<Event> addOrReplace(Abonnement abonnement) {
        //INSERT || UPDATE
        throw new UnsupportedOperationException("Pas encore implémenté");
    }

    @Override
    public Map<IdAbonnement, List<Event>> addOrReplaceAll(Collection<Abonnement> abonnements) {
        //INSERT || UPDATE
        throw new UnsupportedOperationException("Pas encore implémenté");
    }

    @Override
    public Collection<Abonnement> trouveAbonnementsFinissantLe(LocalDate jourDeFin) {
        //SELECT * FROM ...
        throw new UnsupportedOperationException("Pas encore implémenté");
    }

    @Override
    public Collection<Abonnement> trouveAbonnementsEnCoursSouscritsEntre(LocalDate début, LocalDate fin) {
        //SELECT * FROM ...
        throw new UnsupportedOperationException("Pas encore implémenté");
    }

    @Override
    public Map<IdFormule, Long> compteAbonnementsParIdFormule(List<IdFormule> idFormules) {
        //SELECT * FROM ...
        throw new UnsupportedOperationException("Pas encore implémenté");
    }
}
