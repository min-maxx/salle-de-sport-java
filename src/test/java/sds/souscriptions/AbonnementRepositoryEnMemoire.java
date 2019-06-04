package sds.souscriptions;

import sds.souscriptions.concept_metier.Abonnement;
import sds.souscriptions.concept_metier.AbonnementRepository;
import sds.souscriptions.concept_metier.IdAbonnement;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

public class AbonnementRepositoryEnMemoire implements AbonnementRepository {

    private HashMap<IdAbonnement, Abonnement> abonnementMap = new HashMap<>();

    @Override
    public Abonnement get(IdAbonnement id) {
        return abonnementMap.get(id);
    }

    @Override
    public void addOrReplace(Abonnement abonnement) {
        abonnementMap.put(abonnement.Id(), abonnement);
    }

    @Override
    public void addOrReplaceAll(Collection<Abonnement> abonnements) {
        abonnements.forEach(this::addOrReplace);
    }

    @Override
    public Collection<Abonnement> trouveAbonnementsAvec(LocalDate jourDeFin) {
        return abonnementMap.values().stream()
                .filter(abonnement -> abonnement.finisLe(jourDeFin))
                .collect(Collectors.toList());
    }
}
