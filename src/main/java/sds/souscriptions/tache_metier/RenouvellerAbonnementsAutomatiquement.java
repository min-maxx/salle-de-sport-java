package sds.souscriptions.tache_metier;

import sds.souscriptions.concept_metier.Abonnement;
import sds.souscriptions.concept_metier.AbonnementRenouvellé;
import sds.souscriptions.concept_metier.AbonnementRepository;
import sds.souscriptions.concept_metier.IdAbonnement;
import sds.utils.concept_metier.Event;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class RenouvellerAbonnementsAutomatiquement {

    private AbonnementRepository abonnementRepository;

    public RenouvellerAbonnementsAutomatiquement(AbonnementRepository abonnementRepository) {
        this.abonnementRepository = abonnementRepository;
    }

    public Collection<AbonnementRenouvellé> renouvelle(LocalDate jourDeFin) {
        Collection<Abonnement> abonnementsARenouveller = abonnementRepository.trouveAbonnementsFinissantLe(jourDeFin);

        for (Abonnement abonnement : abonnementsARenouveller) {
            abonnement.renouvelle(jourDeFin);
        }
        Map<IdAbonnement, List<Event>> idAbonnementListMap = abonnementRepository.addOrReplaceAll(abonnementsARenouveller);

        return idAbonnementListMap.values().stream()
                .flatMap(Collection::stream)
                .filter(event -> event instanceof AbonnementRenouvellé)
                .map(event -> (AbonnementRenouvellé) event)
                .collect(toList());
    }
}
