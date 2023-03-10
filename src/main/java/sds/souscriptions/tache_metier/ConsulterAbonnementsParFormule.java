package sds.souscriptions.tache_metier;

import sds.souscriptions.concept_metier.AbonnementRepository;
import sds.souscriptions.concept_metier.IdFormule;

import java.util.List;
import java.util.Map;

public class ConsulterAbonnementsParFormule {

    private final AbonnementRepository abonnementRepository;

    public ConsulterAbonnementsParFormule(AbonnementRepository abonnementRepository) {
        this.abonnementRepository = abonnementRepository;
    }

    public Map<IdFormule, Long> consulte(List<IdFormule> idFormules) {
        return abonnementRepository.compteAbonnementsParIdFormule(idFormules);
    }
}
